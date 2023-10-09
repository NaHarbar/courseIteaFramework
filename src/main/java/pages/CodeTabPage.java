package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CodeTabPage extends BasePage {

    private final static String TITLE = "Code tab page";
    private final By repositoryNameLocator = By.xpath("//strong[@itemprop='name']/a");
    private final By issueTabLocator = By.id("issues-tab");
    private final By settingsTabLocator = By.id("settings-tab");
    private final By repositoryTypeTabLocator = By.xpath("//strong[@itemprop='name']//following-sibling::span[contains(@class,'secondary')]");

    public CodeTabPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public CodeTabPage verifyRepositoryName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(repositoryNameLocator));
        Assertions.assertEquals(driver.findElement(repositoryNameLocator).getText(), name);
        return this;
    }

    public IssueTabPage openIssueTabPage() {
        driver.findElement(issueTabLocator).click();
        return new IssueTabPage(driver);
    }

    public CodeTabPage verifyRepositoryType(String type) {
        Assert.assertEquals(driver.findElement(repositoryTypeTabLocator).getText(), type);
        return this;
    }

    public SettingsPage clickSettings() {
        driver.findElement(settingsTabLocator).click();
        return new SettingsPage(driver);
    }
}
