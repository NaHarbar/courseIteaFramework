package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage extends BasePage {
    private final By loginFieldLocator = By.id("login_field");
    private final By passwordFieldLocator = By.id("password");
    private final By logInButtonLocator = By.xpath("//input[@value='Sign in']");
    private final By logoLocator = By.className("header-logo");
    private final By errorTextLocator = By.xpath("//div[contains(text(),'Incorrect username or password.')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLogo() {
        return driver.findElement(logoLocator);
    }

    public MainPage loginSuccessful(String login, String password) {
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(logInButtonLocator).click();
        return new MainPage(driver);
    }

    public LoginPage negativeLogin(String login, String password) {
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(logInButtonLocator).click();
        return this;
    }

    public LoginPage validateErrorMessage(String expectedMessage) {
        assertEquals(expectedMessage, driver.findElement(errorTextLocator).getText(), "Actual error text not matched with expected message");
        return this;
    }

    public LoginPage validateAuthFieldsAreDisplayed() {
        assertTrue(driver.findElement(loginFieldLocator).isDisplayed());
        assertTrue(driver.findElement(passwordFieldLocator).isDisplayed());
        assertTrue(driver.findElement(logInButtonLocator).isDisplayed());
        return this;
    }
}
