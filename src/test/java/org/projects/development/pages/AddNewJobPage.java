package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class AddNewJobPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")
    private WebElement titleInput;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/textarea")
    private WebElement descriptionTextArea;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div[2]/textarea")
    private WebElement noteTextArea;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]")
    private WebElement saveButton;

    public AddNewJobPage(WebDriver driver) {
        super(driver);
    }

    public void enterJobData(String title,
                             String description,
                             String note) {
        titleInput.sendKeys(title);
        descriptionTextArea.sendKeys(description);
        noteTextArea.sendKeys(note);
    }

    public JobsPage saveJobTitle() {
        saveButton.click();
        return ObjectFactory.getObject(JobsPage.class);
    }
}
