import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CodeTabPage extends BasePage {

    private final By repositoryNameLocator = By.xpath("//strong[@itemprop='name']/a");
    private final By issueTabLocator = By.id("issues-tab");

    public CodeTabPage(WebDriver driver) {
        super(driver);
    }

    public CodeTabPage verifyRepositoryName(String name) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> driver.findElement(repositoryNameLocator).isDisplayed());
        Assertions.assertEquals(driver.findElement(repositoryNameLocator).getText(), name);
        return this;
    }

    public IssueTabPage openIssueTabPage() {
        driver.findElement(issueTabLocator).click();
        return new IssueTabPage(driver);
    }
}
