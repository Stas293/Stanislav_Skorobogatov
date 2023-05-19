package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class AdminPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]")
    private WebElement jobDropDown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")
    private WebElement jobTitlesOption;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public JobsPage getJobsMainPage() {
        jobDropDown.click();
        jobTitlesOption.click();
        return ObjectFactory.getObject(JobsPage.class);
    }
}
