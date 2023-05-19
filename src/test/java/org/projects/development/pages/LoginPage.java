package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div/p[1]")
    private WebElement usernameData;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div/p[2]")
    private WebElement passwordData;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    private WebElement loginButton;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void redirectToLoginPage() {
        super.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public String getUsernameData() {
        return usernameData.getText()
                .replace("Username : ", "");
    }

    public String getPasswordData() {
        return passwordData.getText()
                .replace("Password : ", "");
    }

    public MainPage loginWithUserData() {
        usernameField.sendKeys(getUsernameData());
        passwordField.sendKeys(getPasswordData());
        loginButton.click();
        return ObjectFactory.getObject(MainPage.class);
    }
}
