package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class DeletePopupPage extends BasePage {
    @FindBy(xpath = "//button[.=' Yes, Delete ']")
    private WebElement deleteButton;

    public DeletePopupPage(WebDriver driver) {
        super(driver);
    }

    public JobsPage clickDeleteButton() {
        deleteButton.click();
        return ObjectFactory.getObject(JobsPage.class);
    }
}
