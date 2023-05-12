package org.projects.development.dropbox;

import org.projects.development.model.Entry;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface DropboxAPI {
    void upload(Path path);

    boolean download(Path from, Path to);

    Optional<Entry> getFileMetadata(Path path);

    void delete(Path path);

    List<Entry> getFileList();
}
