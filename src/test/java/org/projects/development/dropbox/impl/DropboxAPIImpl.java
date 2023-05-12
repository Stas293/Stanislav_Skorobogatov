package org.projects.development.dropbox.impl;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.projects.development.dropbox.DropboxAPI;
import org.projects.development.model.Entries;
import org.projects.development.model.Entry;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DropboxAPIImpl implements DropboxAPI {
    private final HttpClient client;
    private final String ACCESS_TOKEN;
    private final ObjectMapper objectMapper;

    public DropboxAPIImpl(String accessToken){
        this.ACCESS_TOKEN = accessToken;
        this.objectMapper = new ObjectMapper();
        client = HttpClient.newBuilder().build();
    }

    @SneakyThrows
    public void upload(Path path){
        log.info("Uploading file: {}", path);
        log.info("Creating a request");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/upload"))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Dropbox-API-Arg",
                        "{\"autorename\":false," +
                                "\"mode\":\"add\"," +
                                "\"mute\":false," +
                                "\"path\":\"" + "/" + path + "\"," +
                                "\"strict_conflict\":false}")
                .header("Content-Type", "application/octet-stream")
                .POST(HttpRequest.BodyPublishers.ofFile(path))
                .build();
        log.info("Uploading file: {}", path);

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", send);
    }

    @SneakyThrows
    public boolean download(Path mainFilePath, Path fileFromDropboxPath) {
        log.info("Downloading file: {}", mainFilePath);
        log.info("Creating a request");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://content.dropboxapi.com/2/files/download"))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Dropbox-API-Arg", "{\"path\":\"" + "/" + mainFilePath + "\"}")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        log.info("Downloading file: {}", mainFilePath);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);

        if (response.statusCode() == 200) {
            log.info("Status code: {}", response.statusCode());
            log.info("Writing file: {}", fileFromDropboxPath);
            Files.write(fileFromDropboxPath, response.body().getBytes());
            return true;
        } else {
            log.info("Status code: {}", response.statusCode());
            return false;
        }
    }

    @SneakyThrows
    public List<Entry> getFileList() {
        log.info("Getting file list");
        log.info("Creating a request");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/list_folder"))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"path\":\"\",\"recursive\":false," +
                        "\"include_media_info\":false," +
                        "\"include_deleted\":false," +
                        "\"include_has_explicit_shared_members\":false," +
                        "\"include_mounted_folders\":true," +
                        "\"include_non_downloadable_files\":true}"))
                .build();
        log.info("Getting file list");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);

        String body = response.body();
        log.info("Body: {}", body);

        return objectMapper.readValue(body, Entries.class).getEntries();
    }

    @SneakyThrows
    public Optional<Entry> getFileMetadata(Path path) {
        log.info("Getting file metadata: {}", path);
        log.info("Creating a request");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/get_metadata"))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"include_deleted\":false," +
                        "\"include_has_explicit_shared_members\":false," +
                        "\"include_media_info\":false," +
                        "\"path\":\"" + "/" + path + "\"}"
                )).build();
        log.info("Getting file metadata: {}", path);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);

        return Optional.of(objectMapper.readValue(response.body(), Entry.class));
    }

    @SneakyThrows
    public void delete(Path path) {
        log.info("Deleting file: {}", path);
        log.info("Creating a request");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.dropboxapi.com/2/files/delete_v2"))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"path\":\"" + "/" + path + "\"}"))
                .build();
        log.info("Deleting file: {}", path);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);
        if (response.statusCode() != 200){
            throw new RuntimeException("File was not deleted");
        }
    }
}
