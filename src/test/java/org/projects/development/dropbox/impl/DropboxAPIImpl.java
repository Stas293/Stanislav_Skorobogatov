package org.projects.development.dropbox.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.projects.development.dropbox.DropboxAPI;
import org.projects.development.model.Entries;
import org.projects.development.model.Entry;
import org.projects.development.request.BodyBuilder;
import org.projects.development.request.HttpRequestBuilder;

import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DropboxAPIImpl implements DropboxAPI {
    private final ObjectMapper objectMapper;
    private final HttpRequestBuilder httpRequestBuilder;
    private final BodyBuilder bodyBuilder;

    public DropboxAPIImpl(HttpRequestBuilder httpRequestBuilder,
                          ObjectMapper objectMapper,
                          BodyBuilder bodyBuilder){
        this.objectMapper = objectMapper;
        this.httpRequestBuilder = httpRequestBuilder;
        this.bodyBuilder = bodyBuilder;
    }

    @SneakyThrows
    public void upload(Path path){
        log.info("Uploading file: {}", path);
        HttpResponse<String> response = httpRequestBuilder.postFileRequest(
                "https://content.dropboxapi.com/2/files/upload", path);
        log.info("Response: {}", response);
        log.info("Body: {}", response.body());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.body().contains("id"));
    }

    @SneakyThrows
    public void download(Path mainFilePath, Path fileFromDropboxPath) {
        log.info("Downloading file: {}", mainFilePath);
        String body = bodyBuilder.createFileDownloadBody(mainFilePath);
        HttpResponse<String> response = httpRequestBuilder.postRequest(
                "https://content.dropboxapi.com/2/files/download",
                body);
        log.info("Response: {}", response);
        Assertions.assertEquals(200, response.statusCode());

        log.info("Writing file: {}", fileFromDropboxPath);
        Files.write(fileFromDropboxPath, response.body().getBytes());
    }

    @SneakyThrows
    public List<Entry> getFileList() {
        log.info("Getting file list");
        String body = bodyBuilder.createFileListBody();
        HttpResponse<String> response = httpRequestBuilder.postRequest(
                "https://api.dropboxapi.com/2/files/list_folder",
                body);
        log.info("Response: {}", response);
        Assertions.assertEquals(200, response.statusCode());

        return objectMapper.readValue(response.body(), Entries.class).getEntries();
    }

    @Override
    public void downloadNonExisting(Path mainFilePath) {
        log.info("Downloading non existing file: {}", mainFilePath);
        String body = bodyBuilder.createFileDownloadBody(mainFilePath);
        HttpResponse<String> response = httpRequestBuilder.postRequest(
                "https://content.dropboxapi.com/2/files/download",
                body);

        log.info("Response: {}", response);
        Assertions.assertEquals(400, response.statusCode());
    }

    @SneakyThrows
    public Optional<Entry> getFileMetadata(Path path) {
        log.info("Getting file metadata: {}", path);
        String body = bodyBuilder.createFileMetadataBody(path);
        log.info("Body: {}", body);
        HttpResponse<String> response = httpRequestBuilder.postRequest(
                "https://api.dropboxapi.com/2/files/get_metadata",
                body);
        log.info("Response: {}", response);
        Assertions.assertEquals(200, response.statusCode());

        return Optional.of(objectMapper.readValue(response.body(), Entry.class));
    }

    @SneakyThrows
    public void delete(Path path) {
        log.info("Deleting file: {}", path);
        String body = bodyBuilder.createFileDeleteBody(path);
        HttpResponse<String> response = httpRequestBuilder.postRequest(
                "https://api.dropboxapi.com/2/files/delete_v2",
                body);
        log.info("Response: {}", response);

        Assertions.assertEquals(200, response.statusCode());
    }
}
