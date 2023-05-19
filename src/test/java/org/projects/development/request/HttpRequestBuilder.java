package org.projects.development.request;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

@Slf4j
public class HttpRequestBuilder {
    private final HttpClient client;
    private final String ACCESS_TOKEN;

    public HttpRequestBuilder(String accessToken) {
        this.ACCESS_TOKEN = accessToken;
        this.client = HttpClient.newBuilder().build();
    }

    @SneakyThrows
    private HttpResponse<String> sendRequest(HttpRequest request) {
        log.info("Creating a request: {}", request);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Response: {}", response);
        return response;
    }

    private HttpRequest.Builder createBaseRequestBuilder(String uri) {
        return createBaseRequestBuilder(uri, "application/json");
    }

    private HttpRequest.Builder createBaseRequestBuilder(String uri, String contentType) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", contentType);
    }

    @SneakyThrows
    public HttpResponse<String> postRequest(String uri, String body) {
        HttpRequest request = createBaseRequestBuilder(uri)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return sendRequest(request);
    }

    @SneakyThrows
    public HttpResponse<String> postRequest(String uri) {
        HttpRequest request = createBaseRequestBuilder(uri)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        return sendRequest(request);
    }

    @SneakyThrows
    public HttpResponse<String> postFileRequest(String uri, Path path) {
        HttpRequest request = createBaseRequestBuilder(uri, "application/octet-stream")
                .header("Dropbox-API-Arg", "{\"path\":\"" + "/" + path + "\"}")
                .POST(HttpRequest.BodyPublishers.ofFile(path))
                .build();
        return sendRequest(request);
    }
}

