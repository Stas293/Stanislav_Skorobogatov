package org.projects.development.dropbox.factories;

import lombok.extern.slf4j.Slf4j;
import org.projects.development.dropbox.DropboxAPI;
import org.projects.development.dropbox.impl.DropboxAPIImpl;

import java.util.ResourceBundle;

@Slf4j
public class DropboxFactory {
    private static class DropboxFactoryHolder {
        private static final DropboxFactory dropboxFactory = new DropboxFactory();
    }
    public DropboxAPI dropboxAPI;

    private DropboxFactory() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String accessToken = resourceBundle.getString("ACCESS_TOKEN");
        log.info("Creating a DropboxAPI instance");
        dropboxAPI = new DropboxAPIImpl(accessToken);
        log.info("DropboxAPI instance created");
    }

    public static DropboxAPI getDropboxAPI() {
        return DropboxFactoryHolder.dropboxFactory.dropboxAPI;
    }
}
