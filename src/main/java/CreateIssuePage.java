import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateIssuePage extends BasePage {
    private final By titleInputLocator = By.id("issue_title");
    private final By commentInputLocator = By.id("issue_body");
    private final By avatarLogoLocator = By.xpath("//img[@class='avatar avatar-user']");
    private final By submitNewIssueButtonLocator = By.xpath("(//button[contains(text(),'Submit new issue')])[1]");
    private final By issueTitleLocator = By.xpath("//bdi[@class='js-issue-title markdown-title']");

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

    public CreateIssuePage titleIsVisible() {
        Assertions.assertTrue(driver.findElement(titleInputLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage commentInputVisible() {
        Assertions.assertTrue(driver.findElement(commentInputLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage verifyAvatarLogoDisplayed() {
        Assertions.assertTrue(driver.findElement(avatarLogoLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage verifySubmitButtonDisabled() {
        Assertions.assertEquals(driver.findElement(submitNewIssueButtonLocator).getAttribute("disabled"), "true", "Field is not disabled");
        return this;
    }

    public CreateIssuePage verifyPlaceholderForTitleInput(String placeholder) {
        Assertions.assertEquals(driver.findElement(titleInputLocator).getAttribute("placeholder"), placeholder, "Placeholder is not matched");
        return this;
    }

    public CreateIssuePage verifyPlaceholderForCommentInput(String placeholder) {
        Assertions.assertEquals(driver.findElement(commentInputLocator).getAttribute("placeholder"), placeholder, "Placeholder is not matched");
        return this;
    }

    public CreateIssuePage enterTitle(String titleValue) {
        driver.findElement(titleInputLocator).sendKeys(titleValue);
        return this;
    }

    public CreateIssuePage enterComment(String titleValue) {
        driver.findElement(commentInputLocator).sendKeys(titleValue);
        return this;
    }

    public CreateIssuePage verifySubmitIssueButtonEnabled() {
        Assertions.assertTrue(driver.findElement(submitNewIssueButtonLocator).isEnabled());
        return this;
    }

    public CreateIssuePage clickSubmitIssueButtonEnabled() {
        driver.findElement(submitNewIssueButtonLocator).click();
        return this;
    }

    public IssueTabPage verifyIssueCreated(String titleValue) {
        Assertions.assertEquals(driver.findElement(issueTitleLocator).getText(), titleValue, "Title value not matched");
        return new IssueTabPage(driver);
    }

}
