package org.projects.development.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.projects.development.enums.RequestField.*;

public class BodyBuilder {
    private final FileRequestBuilder fileRequestBuilder;

    public BodyBuilder(FileRequestBuilder fileRequestBuilder) {
        this.fileRequestBuilder = fileRequestBuilder;
    }

    @SneakyThrows
    public String createFileDownloadBody(Path path) {
        return fileRequestBuilder
                .setPath("/" + path)
                .build();
    }

    @SneakyThrows
    public String createFileListBody() {
        return fileRequestBuilder.setPath("").setRecursive(false)
                .setIncludeMediaInfo(false)
                .setIncludeDeleted(false)
                .setIncludeHasExplicitSharedMembers(false)
                .setIncludeMountedFolders(true)
                .setIncludeNonDownloadableFiles(true)
                .build();
    }

    @SneakyThrows
    public String createFileMetadataBody(Path path) {
        return fileRequestBuilder.setIncludeDeleted(false)
                .setIncludeHasExplicitSharedMembers(false)
                .setIncludeMediaInfo(false)
                .setPath("/" + path)
                .build();
    }

    @SneakyThrows
    public String createFileDeleteBody(Path path) {
        return fileRequestBuilder.setPath("/" + path)
                .build();
    }

    public static class FileRequestBuilder {
        private final ObjectMapper objectMapper;
        private final Map<String, Object> body;

        public FileRequestBuilder(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            this.body = new HashMap<>();
        }

        public FileRequestBuilder setPath(String path) {
            body.put(PATH.getFieldName(), path);
            return this;
        }

        public FileRequestBuilder setRecursive(boolean recursive) {
            body.put(RECURSIVE.getFieldName(), recursive);
            return this;
        }

        public FileRequestBuilder setIncludeMediaInfo(boolean includeMediaInfo) {
            body.put(INCLUDE_MEDIA_INFO.getFieldName(), includeMediaInfo);
            return this;
        }

        public FileRequestBuilder setIncludeDeleted(boolean includeDeleted) {
            body.put(INCLUDE_DELETED.getFieldName(), includeDeleted);
            return this;
        }

        public FileRequestBuilder setIncludeHasExplicitSharedMembers(boolean includeHasExplicitSharedMembers) {
            body.put(INCLUDE_HAS_EXPLICIT_SHARED_MEMBERS.getFieldName(), includeHasExplicitSharedMembers);
            return this;
        }

        public FileRequestBuilder setIncludeMountedFolders(boolean includeMountedFolders) {
            body.put(INCLUDE_MOUNTED_FOLDERS.getFieldName(), includeMountedFolders);
            return this;
        }

        public FileRequestBuilder setIncludeNonDownloadableFiles(boolean includeNonDownloadableFiles) {
            body.put(INCLUDE_NON_DOWNLOADABLE_FILES.getFieldName(), includeNonDownloadableFiles);
            return this;
        }

        @SneakyThrows
        public String build() {
            try {
                return objectMapper.writeValueAsString(body);
            } finally {
                body.clear();
            }
        }
    }
}


