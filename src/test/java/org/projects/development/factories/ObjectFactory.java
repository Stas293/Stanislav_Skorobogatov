package org.projects.development.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.projects.development.dropbox.DropboxAPI;
import org.projects.development.dropbox.impl.DropboxAPIImpl;
import org.projects.development.request.BodyBuilder;
import org.projects.development.request.HttpRequestBuilder;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ObjectFactory {
    private static class DropboxFactoryHolder {
        private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    }
    private final ConcurrentHashMap<Class<?>, Object> OBJECTS;

    private ObjectFactory() {
        this.OBJECTS = new ConcurrentHashMap<>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String accessToken = resourceBundle.getString("ACCESS_TOKEN");
        log.info("Creating a DropboxAPI instance");
        this.OBJECTS.put(HttpRequestBuilder.class, new HttpRequestBuilder(accessToken));
        this.OBJECTS.put(ObjectMapper.class, new ObjectMapper());
        this.OBJECTS.put(BodyBuilder.FileRequestBuilder.class, new BodyBuilder.FileRequestBuilder(
                (ObjectMapper) this.OBJECTS.get(ObjectMapper.class)));
        this.OBJECTS.put(BodyBuilder.class, new BodyBuilder(
                (BodyBuilder.FileRequestBuilder) this.OBJECTS.get(BodyBuilder.FileRequestBuilder.class)));
        this.OBJECTS.put(DropboxAPI.class, new DropboxAPIImpl(
                (HttpRequestBuilder) this.OBJECTS.get(HttpRequestBuilder.class),
                (ObjectMapper) this.OBJECTS.get(ObjectMapper.class),
                (BodyBuilder) this.OBJECTS.get(BodyBuilder.class)));
        log.info("DropboxAPI instance created");
    }

    public static <T> T getObject(Class<T> clazz) {
        return clazz.cast(DropboxFactoryHolder.OBJECT_FACTORY.OBJECTS.get(clazz));
    }
}
