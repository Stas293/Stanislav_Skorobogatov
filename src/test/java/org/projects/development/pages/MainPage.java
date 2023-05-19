package org.projects.development.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.projects.development.factories.ObjectFactory;

public class MainPage extends BasePage {
    @FindBy(xpath = "//a[@href='/web/index.php/admin/viewAdminModule']")
    private WebElement adminButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage getAdminPage() {
        adminButton.click();
        return ObjectFactory.getObject(AdminPage.class);
    }
}
