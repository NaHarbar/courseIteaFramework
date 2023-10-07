package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreateNewRepositoryPage extends BasePage {
    private final static String TYPE_VALUE_LOCATOR = "//div[contains(@class, 'CheckboxOrRadioGroup')]//input[@value='%s']";
    private final By pageTitleLocator = By.xpath("//h1[contains(text(),'Create a new repository')]");
    private final By confirmNameAvailableNameLocator = By.id("RepoNameInput-is-available");
    private final By repositoryNameInputLocator = By.xpath("//input[@aria-label = 'Repository']");
    private final By createRepositoryButtonLocator = By.xpath("//button[@type='submit']//span[text()='Create repository']");

    public CreateNewRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewRepositoryPage verifyPageTitleLocator() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageTitleLocator));
        Assert.assertEquals(driver.findElement(pageTitleLocator).getText(), "Create a new repository");
        return this;
    }

    public CreateNewRepositoryPage enterRepositoryName(String name) {
        driver.findElement(repositoryNameInputLocator).sendKeys(name);
        return this;
    }

    public CreateNewRepositoryPage setType(String type) {
        driver.findElement(By.xpath(TYPE_VALUE_LOCATOR.formatted(type.toLowerCase()))).click();
        return this;
    }

    public CreateNewRepositoryPage verifyNameAvailable() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmNameAvailableNameLocator));
        String actualValue = driver.findElement(confirmNameAvailableNameLocator).getText();
        Assert.assertTrue(actualValue.contains("is available."));
        return this;
    }

    public CodeTabPage clickCreateRepository() {
        driver.findElement(createRepositoryButtonLocator).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(createRepositoryButtonLocator));
        return new CodeTabPage(driver);
    }

}
