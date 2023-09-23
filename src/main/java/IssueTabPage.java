import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssueTabPage extends BasePage {

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
        super(driver);
    }

    public CreateIssuePage createNewIssue() {
        driver.findElement(newIssueLocator).click();
        return new CreateIssuePage(driver);
    }

    public IssueTabPage newIssueButtonVisible() {
        Assertions.assertTrue(driver.findElement(newIssueLocator).isDisplayed());
        return this;
    }

    public IssueTabPage verifySearchIssueVisible() {
        Assertions.assertTrue(driver.findElement(searchIssueLocator).isDisplayed());
        return this;
    }

    public IssueTabPage verifyIssueStatusOpen() {
        Assertions.assertTrue(driver.findElement(issueOpenStatusLocator).isDisplayed(), "Issue status isn't open");
        return this;
    }

    public IssueTabPage verifyIssueStatusClosed() {
        Assertions.assertTrue(driver.findElement(issueClosedStatusLocator).isDisplayed(), "Issue status isn't closed");
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
        Assertions.assertTrue(driver.findElement(newCommentInputLocator).isDisplayed());
        return this;
    }

    public IssueTabPage enterComment(String titleValue) {
        driver.findElement(newCommentInputLocator).sendKeys(titleValue);
        return this;
    }

    public IssueTabPage checkCreatedIssueComment(String commentValue) {
        Assertions.assertEquals(driver.findElement(issueCommentLocator).getText(), commentValue, "Issue comment not matched");
        return this;
    }

    public IssueTabPage checkCloseIssueComment(String commentValue) {
        Assertions.assertEquals(driver.findElement(issueCloseCommentLocator).getText(), commentValue, "Close Issue comment not matched");
        return this;
    }

    public IssueTabPage closeIssueButtonEnabled() {
        Assertions.assertTrue(driver.findElement(closeIssueButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage closeWithCommentButtonEnabled() {
        Assertions.assertTrue(driver.findElement(closeWithCommentButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage checkReopenIconVisible() {
        Assertions.assertTrue(driver.findElement(reopenIconLocator).isDisplayed());
        return this;
    }

    public IssueTabPage reopenIssueButtonEnabled() {
        Assertions.assertTrue(driver.findElement(reopenIssueButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage reopenWithCommentButtonEnabled() {
        Assertions.assertTrue(driver.findElement(reopenWithButtonLocator).isEnabled());
        return this;
    }

    public IssueTabPage verifyCommentButtonDisabled() {
        Assertions.assertEquals(driver.findElement(commentButtonLocator).getAttribute("disabled"), "true", "Comment is enabled");
        return this;
    }

    public IssueTabPage verifyCommentButtonEnabled() {
        Assertions.assertTrue(driver.findElement(commentButtonLocator).isEnabled());
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
        Assertions.assertEquals(driver.findElement(deleteIssueMessageLocator).getText(), message, "Confirmation message doesn't matched");
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
        Assertions.assertTrue(driver.findElement(By.xpath(taskListCommentLocator.formatted(commentValue))).isDisplayed(), "Comment message isn't visible");
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
        Assertions.assertEquals(driver.findElement(selectedLabelLocator).getText(), label, "Label value doesn't matched");
        return this;
    }

}
