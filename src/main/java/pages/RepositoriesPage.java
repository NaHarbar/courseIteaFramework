package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RepositoriesPage extends BasePage {

    private final By deleteMessageLocator = By.xpath("//div[@role='alert']");

    public RepositoriesPage(WebDriver driver) {
        super(driver);
    }

    public RepositoriesPage verifySuccessfullyDeletedMessage() {
        String actualMessage = driver.findElement(deleteMessageLocator).getText();
        Assert.assertTrue(actualMessage.contains("was successfully deleted."));
        return this;
    }
}
