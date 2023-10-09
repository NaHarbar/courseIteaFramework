import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomePage extends BasePage {
    private final static String TITLE = "Home page";
    private final By signInButtonLocator = By.xpath("//a[contains(text(),'Sign in')]");

    public HomePage(WebDriver driver) {
        super(driver, TITLE);
    }

    public LoginPage goToLoginPage() {
        webDriverWait.until(elementToBeClickable(driver.findElement(signInButtonLocator)));
        driver.findElement(signInButtonLocator).click();
        return new LoginPage(driver);
    }
}
