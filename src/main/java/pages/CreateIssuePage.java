package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateIssuePage extends BasePage {
    private final static String TITLE = "Create issue page";
    private final By titleInputLocator = By.id("issue_title");
    private final By commentInputLocator = By.id("issue_body");
    private final By avatarLogoLocator = By.xpath("//img[@class='avatar avatar-user']");
    private final By submitNewIssueButtonLocator = By.xpath("(//button[contains(text(),'Submit new issue')])[1]");
    private final By issueTitleLocator = By.xpath("//bdi[@class='js-issue-title markdown-title']");

    public CreateIssuePage(WebDriver driver) {
        super(driver, TITLE);
    }

    public CreateIssuePage titleIsVisible() {
        Assert.assertTrue(driver.findElement(titleInputLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage commentInputVisible() {
        Assert.assertTrue(driver.findElement(commentInputLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage verifyAvatarLogoDisplayed() {
        Assert.assertTrue(driver.findElement(avatarLogoLocator).isDisplayed());
        return this;
    }

    public CreateIssuePage verifySubmitButtonDisabled() {
        Assert.assertEquals(driver.findElement(submitNewIssueButtonLocator).getAttribute("disabled"), "true", "Field is not disabled");
        return this;
    }

    public CreateIssuePage verifyPlaceholderForTitleInput(String placeholder) {
        Assert.assertEquals(driver.findElement(titleInputLocator).getAttribute("placeholder"), placeholder, "Placeholder is not matched");
        return this;
    }

    public CreateIssuePage verifyPlaceholderForCommentInput(String placeholder) {
        Assert.assertEquals(driver.findElement(commentInputLocator).getAttribute("placeholder"), placeholder, "Placeholder is not matched");
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
        Assert.assertTrue(driver.findElement(submitNewIssueButtonLocator).isEnabled());
        return this;
    }

    public CreateIssuePage clickSubmitIssueButtonEnabled() {
        driver.findElement(submitNewIssueButtonLocator).click();
        return this;
    }

    public IssueTabPage verifyIssueCreated(String titleValue) {
        Assert.assertEquals(driver.findElement(issueTitleLocator).getText(), titleValue, "Title value not matched");
        return new IssueTabPage(driver);
    }

}
