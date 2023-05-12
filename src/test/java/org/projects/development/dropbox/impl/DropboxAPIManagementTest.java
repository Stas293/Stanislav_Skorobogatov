package org.projects.development.dropbox.impl;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.projects.development.dropbox.DropboxAPI;
import org.projects.development.dropbox.factories.DropboxFactory;
import org.projects.development.model.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DropboxAPIManagementTest {
    private final DropboxAPI dropboxAPI;
    private final Path MAIN_FILE_PATH;
    private final Path FILE_FROM_DROPBOX_PATH;

    private Optional<Entry> fileMetadata;

    public DropboxAPIManagementTest() {
        dropboxAPI = DropboxFactory.getDropboxAPI();
        MAIN_FILE_PATH = Path.of("mainFile.txt");
        FILE_FROM_DROPBOX_PATH = Path.of("fileFromDropbox.txt");
    }

    @Given("the file is created")
    public void createNewFile() {
        try {
            log.info("Creating a file");
            Files.write(MAIN_FILE_PATH, "Some important info".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(String.format("Could not create the file: %s", e));
        }
    }

    @When("user uploads the file")
    public void uploadToDropbox() {
        log.info("Uploading the file");
        dropboxAPI.upload(MAIN_FILE_PATH);
    }

    @Then("the file is on Dropbox")
    public void checkIfFileIsUploaded() {
        log.info("Checking the file upload");
        List<Entry> entries = dropboxAPI.getFileList();

        for (Entry entry : entries) {
            if (entry.getName().equals(MAIN_FILE_PATH.toString())) {
                return;
            }
        }
        log.error("Could not get the file");
        throw new RuntimeException("Could not get the file");
    }

    @And("user requests file's metadata")
    public void getFileMetadata() {
        log.info("Getting the file metadata");
        fileMetadata = dropboxAPI.getFileMetadata(MAIN_FILE_PATH);
    }

    @Then("file's metadata is received")
    public void checkFileMetadata(){
        log.info("Checking the file metadata");
        if (fileMetadata.isEmpty()) {
            log.error("Could not get the file metadata");
            throw new RuntimeException("Could not get the file metadata");
        }
    }

    @And("user deletes the file")
    public void deleteFileFromDropbox() {
        log.info("Deleting the file");
        dropboxAPI.delete(MAIN_FILE_PATH);
    }

    @Then("there is no file on Dropbox")
    public void checkFileAbsent() {
        log.info("Checking the file deletion");
        boolean success = dropboxAPI.download(MAIN_FILE_PATH, FILE_FROM_DROPBOX_PATH);
        if (success){
            log.error("Could not delete the file");
            throw new RuntimeException("Could not delete the file");
        }
    }

    @After
    @SneakyThrows
    public void finalizeDropbox(){
        log.info("Cleaning up");
        log.info("Deleting the file");
        Files.deleteIfExists(MAIN_FILE_PATH);
        Files.deleteIfExists(FILE_FROM_DROPBOX_PATH);
    }
}
