package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private final WebDriver WEB_DRIVER;

    public BasePage(WebDriver driver) {
        this.WEB_DRIVER = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return WEB_DRIVER;
    }
}
