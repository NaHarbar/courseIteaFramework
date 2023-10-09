import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final static String TITLE = "Login page";
    @FindBy(id = "login_field")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Sign in']")
    private WebElement logInButton;

    @FindBy(className = "header-logo")
    private WebElement logo;

    @FindBy(xpath = "//div[contains(text(),'Incorrect username or password.')]")
    private WebElement errorText;

    public LoginPage(WebDriver driver) {
        super(driver, TITLE);
        PageFactory.initElements(driver, this);
    }

    public WebElement getLogo() {
        return logo;
    }

    public MainPage loginSuccessful(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
        return new MainPage(driver);
    }

    public LoginPage negativeLogin(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        logInButton.click();
        return this;
    }

    public LoginPage validateErrorMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, errorText.getText(), "Actual error text not matched with expected message");
        return this;
    }

    public LoginPage validateAuthFieldsAreDisplayed() {
        Assertions.assertTrue(loginField.isDisplayed());
        Assertions.assertTrue(passwordField.isDisplayed());
        Assertions.assertTrue(logInButton.isDisplayed());
        return this;
    }
}
