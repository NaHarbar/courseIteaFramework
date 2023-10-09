package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SettingsPage extends BasePage {
    private final static String TITLE = "Settings Page";
    private final By deleteThisRepositoryLocator = By.id("dialog-show-repo-delete-menu-dialog");
    private final By wantDeleteRepositoryButtonLocator = By.id("repo-delete-proceed-button");
    private final By understandEffectsButtonLocator = By.id("repo-delete-proceed-button-container");
    private final By deleteThisRepositoryButtonLocator = By.id("repo-delete-proceed-button");
    private final By subHeaderTitleLocator = By.xpath("//div[@class='Subhead']/h2[@class='Subhead-heading']");
    private final By confirmInputNameLocator = By.xpath("//input[@data-test-selector='repo-delete-proceed-confirmation']");
    private final By wantDeleteRepositoryLabelLocator = By.xpath("//button[@id='repo-delete-proceed-button']//span[@class='Button-label']");

    public SettingsPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public SettingsPage verifySubHeader() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(subHeaderTitleLocator));
        Assert.assertEquals(driver.findElement(subHeaderTitleLocator).getText(), "General");
        return this;
    }

    public RepositoriesPage deleteThisRepository() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(deleteThisRepositoryLocator));
        driver.findElement(deleteThisRepositoryLocator).click();
        Assert.assertEquals(driver.findElement(wantDeleteRepositoryLabelLocator).getText(), "I want to delete this repository");
        driver.findElement(wantDeleteRepositoryButtonLocator).click();
        driver.findElement(understandEffectsButtonLocator).click();
        String name = driver.findElement(confirmInputNameLocator).getAttribute("data-repo-nwo");
        driver.findElement(confirmInputNameLocator).sendKeys(name);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(deleteThisRepositoryButtonLocator));
        driver.findElement(deleteThisRepositoryButtonLocator).click();
        return new RepositoriesPage(driver);
    }
}
