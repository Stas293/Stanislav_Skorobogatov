package org.projects.development.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class JobsPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/div/button")
    private WebElement addJobButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[2]/div/div/button")
    private WebElement deleteJobButton;

    public JobsPage(WebDriver driver) {
        super(driver);
    }

    public AddNewJobPage getAddNewJobPage() {
        addJobButton.click();
        return ObjectFactory.getObject(AddNewJobPage.class);
    }

    public void assertNewJobWasAdded(String jobTitle) {
        Assertions.assertTrue(super.getDriver()
                                      .findElements(By.xpath("//div[.='" + jobTitle + "']"))
                                      .size() > 0
        );
    }

    public DeletePopupPage removeDeletedJobTitle(String jobTitle) {
        super.getDriver()
                .findElement(
                        By.xpath("//div[.='" + jobTitle + "']/ancestor::div[2]/div/div[1]/div/div/label/span/i")
                ).click();

        deleteJobButton.click();

        return ObjectFactory.getObject(DeletePopupPage.class);
    }

    public void assertDeleted(String jobTitle) {
        Assertions.assertTrue(super.getDriver()
                .findElements(
                        By.xpath("//div[.='" + jobTitle + "']")
                )
                .isEmpty()
        );
    }
}
