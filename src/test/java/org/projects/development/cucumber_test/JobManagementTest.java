package org.projects.development.cucumber_test;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.projects.development.factories.ObjectFactory;
import org.projects.development.pages.*;

@Slf4j
public class JobManagementTest {
    @Before
    public void init(){
        ObjectFactory.init();
    }

    @Given("user enters login page")
    public void enterLoginPage() {
        log.info("Entering login page");
        ObjectFactory.getObject(LoginPage.class).redirectToLoginPage();
        log.info("Login page entered");
    }

    @When("user logs in")
    public void logIn() {
        log.info("Logging in");
        MainPage mainPage = ObjectFactory.getObject(LoginPage.class).loginWithUserData();
        log.info("Main page: {}", mainPage);
    }

    @When("user loads Admin page")
    public void loadAdmin() {
        log.info("Loading Admin page");
        AdminPage adminPage = ObjectFactory.getObject(MainPage.class).getAdminPage();
        log.info("Admin page: {}", adminPage);
    }

    @And("user loads Job Titles")
    public void getJobTitlesPage() {
        log.info("Loading Job Titles page");
        JobsPage jobsMainPage = ObjectFactory.getObject(AdminPage.class).getJobsMainPage();
        log.info("Job Titles page: {}", jobsMainPage);
    }

    @And("user clicks on the Add button")
    public void getAddJobTitlePage() {
        log.info("Loading Add Job Title page");
        AddNewJobPage jobTitlePage = ObjectFactory.getObject(JobsPage.class).getAddNewJobPage();
        log.info("Add Job Title page: {}", jobTitlePage);
    }

    @And("user adds new job with title {string}, description {string}, and note {string}")
    public void addJobTitle(String jobTitle, String description, String note) {
        log.info("Adding new job title");
        AddNewJobPage newJobPage = ObjectFactory.getObject(AddNewJobPage.class);
        newJobPage.enterJobData(jobTitle, description, note);
        log.info("Entered job data");
        JobsPage jobsPage = newJobPage.saveJobTitle();
        log.info("Jobs page: {}", jobsPage);
    }


    @Then("the new job {string} is on the main page")
    public void checkNewJobTitle(String jobTitle) {
        log.info("Checking new job title");
        ObjectFactory.getObject(JobsPage.class).assertNewJobWasAdded(jobTitle);
        log.info("New job title checked");
    }

    @When("user selects new job and deletes {string}")
    public void selectAndDeleteJobTitle(String jobTitle) {
        log.info("Deleting job title");
        DeletePopupPage deletePopupPage = ObjectFactory.getObject(JobsPage.class).removeDeletedJobTitle(jobTitle);
        log.info("DeletePopupPage: {}", deletePopupPage);
    }

    @And("user clicks 'Yes, Delete'")
    public void approveDeletion() {
        log.info("Approving deletion");
        JobsPage jobsPage = ObjectFactory.getObject(DeletePopupPage.class).clickDeleteButton();
        log.info("Jobs page: {}", jobsPage);
    }

    @Then("the job {string} is removed")
    public void checkDeletion(String jobTitle) {
        log.info("Checking deletion");
        ObjectFactory.getObject(JobsPage.class).assertDeleted(jobTitle);
        log.info("Deletion checked");
    }

    @After
    public void closeBrowser() {
        log.info("Closing browser");
        ObjectFactory.closeDriver();
        log.info("Browser closed");
    }
}
