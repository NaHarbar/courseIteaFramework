package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class IssueTabPage extends BasePage {
    private final static String TITLE = "Issue tab page";
    private final static String openIssueTitleLocator = "//a[@data-hovercard-type='issue' and text()='%s']";
    private final static String closeIssueTitleLocator = "//a[@data-hovercard-type='issue' and text()='%s']";
    private final static String taskListCommentLocator = "//td[contains(@class, 'comment-body')]/p[text()='%s']";
    private final static String labelValueLocator = "//span[contains(@class, 'label-name') and text()='%s']";
    private final By issueTabLocator = By.id("issues-tab");
    private final By labelsOptionsLocator = By.id("labels-select-menu");
    private final By searchIssueLocator = By.xpath("//input[@aria-label='Search all issues']");
    private final By newIssueLocator = By.xpath("//span[text()='New issue']");
    private final By issueOpenStatusLocator = By.xpath("(//span[@title='Status: Open'])[1]");
    private final By newCommentInputLocator = By.id("new_comment_field");
    private final By issueCommentLocator = By.xpath("//td[contains(@class,'comment-body')]/p");
    private final By issueCloseCommentLocator = By.xpath("//form[contains(@id,'issuecomment')]//parent::div//td[contains(@class,'comment-body')]/p");
    private final By closeIssueButtonLocator = By.xpath("//span[contains(text(), 'Close issue')]");
    private final By closeWithCommentButtonLocator = By.xpath("//span[contains(text(), 'Close with comment')]");
    private final By reopenIssueButtonLocator = By.xpath("//span[contains(text(), 'Reopen issue')]");
    private final By reopenWithButtonLocator = By.xpath("//span[contains(text(), 'Reopen with comment')]");
    private final By commentButtonLocator = By.xpath("//button[contains(text(),'Comment')]");
    private final By issueClosedStatusLocator = By.xpath("(//span[@title='Status: Closed'])[1]");
    private final By reopenIconLocator = By.xpath("//*[contains(@class,'issue-reopened')]");
    private final By deleteIssueLocator = By.xpath("//strong[text()='Delete issue']");
    private final By confirmDeleteIssueLocator = By.xpath("//button[contains(text(),'Delete this issue')]");
    private final By deleteIssueMessageLocator = By.xpath("//div[contains(text(),'The issue was successfully deleted.')]");
    private final By tableClosedTabLocator = By.xpath("(//a[contains(@data-ga-click,'Issues, Table state, Closed')])[2]");
    private final By selectedLabelLocator = By.xpath("//div[contains(@class,'discussion-sidebar-item')]//a[contains(@id,'label')]");

    public IssueTabPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public CreateIssuePage createNewIssue() {
        driver.findElement(newIssueLocator).click();
        return new CreateIssuePage(driver);
    }

    public IssueTabPage newIssueButtonVisible() {
        Assert.assertTrue(driver.findElement(newIssueLocator).isDisplayed());
        return this;
    }

    public IssueTabPage verifySearchIssueVisible() {
        Assert.assertTrue(driver.findElement(searchIssueLocator).isDisplayed());
        return this;
    }

    public IssueTabPage verifyIssueStatusOpen() {
        Assert.assertTrue(driver.findElement(issueOpenStatusLocator).isDisplayed(), "Issue status isn't open");
        return this;
    }

    public IssueTabPage verifyIssueStatusClosed() {
        Assert.assertTrue(driver.findElement(issueClosedStatusLocator).isDisplayed(), "Issue status isn't closed");
        return this;
    }

    public IssueTabPage clickOnCloseIssueWithTitle(String titleValue) {
        driver.findElement(By.xpath(closeIssueTitleLocator.formatted(titleValue))).click();
        return this;
    }

    public IssueTabPage clickOnOpenIssueWithTitle(String titleValue) {
        driver.findElement(By.xpath(openIssueTitleLocator.formatted(titleValue))).click();
        return this;
    }

    public IssueTabPage closeCommentInputVisible() {
        Assert.assertTrue(driver.findElement(newCommentInputLocator).isDisplayed());
        return this;
    }

    public IssueTabPage enterComment(String titleValue) {
        driver.findElement(newCommentInputLocator).sendKeys(titleValue);
        return this;
    }

    public IssueTabPage checkCreatedIssueComment(String commentValue) {
        Assert.assertEquals(driver.findElement(issueCommentLocator).getText(), commentValue, "Issue comment not matched");
        return this;
    }

    public IssueTabPage checkCloseIssueComment(String commentValue) {
        Assert.assertEquals(driver.findElement(issueCloseCommentLocator).getText(), commentValue, "Close Issue comment not matched");
        return this;
    }

    public IssueTabPage closeIssueButtonEnabled() {
        Assert.assertTrue(driver.findElement(closeIssueButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage closeWithCommentButtonEnabled() {
        Assert.assertTrue(driver.findElement(closeWithCommentButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage checkReopenIconVisible() {
        Assert.assertTrue(driver.findElement(reopenIconLocator).isDisplayed());
        return this;
    }

    public IssueTabPage reopenIssueButtonEnabled() {
        Assert.assertTrue(driver.findElement(reopenIssueButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage reopenWithCommentButtonEnabled() {
        Assert.assertTrue(driver.findElement(reopenWithButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage verifyCommentButtonDisabled() {
        Assert.assertEquals(driver.findElement(commentButtonLocator).getAttribute("disabled"), "true", "Comment is enabled");
        return this;
    }

    public IssueTabPage verifyCommentButtonEnabled() {
        Assert.assertTrue(driver.findElement(commentButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage clickCloseIssue() {
        driver.findElement(closeIssueButtonLocator).click();
        return this;
    }

    public IssueTabPage clickCloseWithComment() {
        driver.findElement(closeWithCommentButtonLocator).click();
        return this;
    }

    public IssueTabPage clickReopenIssueButton() {
        driver.findElement(reopenIssueButtonLocator).click();
        return this;
    }

    public IssueTabPage clickReopenWithCommentButton() {
        driver.findElement(reopenWithButtonLocator).click();
        return this;
    }

    public IssueTabPage clickCommentButton() {
        driver.findElement(commentButtonLocator).click();
        return this;
    }

    public IssueTabPage deleteIssue(String message) {
        driver.findElement(deleteIssueLocator).click();
        driver.findElement(confirmDeleteIssueLocator).click();
        Assert.assertEquals(driver.findElement(deleteIssueMessageLocator).getText(), message, "Confirmation message doesn't matched");
        return this;
    }

    public IssueTabPage clickOpenIssueTab() {
        driver.findElement(issueTabLocator).click();
        return this;
    }

    public IssueTabPage showClosedIssue() {
        driver.findElement(tableClosedTabLocator).click();
        return this;
    }

    public IssueTabPage verifyLeavedComment(String commentValue) {
        Assert.assertTrue(driver.findElement(By.xpath(taskListCommentLocator.formatted(commentValue))).isDisplayed(), "Comment message isn't visible");
        return this;
    }

    public IssueTabPage setLabel(String label) {
        driver.findElement(labelsOptionsLocator).click();
        driver.findElement(By.xpath(labelValueLocator.formatted(label))).click();
        return this;
    }

    public IssueTabPage closeLabelSelector() {
        driver.findElement(labelsOptionsLocator).click();
        return this;
    }

    public IssueTabPage verifyLabelForIssue(String label) {
        Assert.assertEquals(driver.findElement(selectedLabelLocator).getText(), label, "Label value doesn't matched");
        return this;
    }

}
