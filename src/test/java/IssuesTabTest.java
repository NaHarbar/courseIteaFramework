import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IssuesTabTest extends BaseTestClass {

    @BeforeEach
    public void openRepository() {
        final String repositoryName = "TestRepository";

        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .openRepository(repositoryName)
                .verifyRepositoryName(repositoryName);
    }

    @Test
    public void verifyIssueTabCanBeOpened() {
        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .verifySearchIssueVisible()
                .newIssueButtonVisible();
    }

    @Test
    public void checkCreateIssueFields() {
        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .verifyAvatarLogoDisplayed()
                .titleIsVisible()
                .commentInputVisible()
                .verifySubmitButtonDisabled()
                .verifyPlaceholderForTitleInput("Title")
                .verifyPlaceholderForCommentInput("Leave a comment");
    }

    @Test
    public void verifyNewIssueCanBeCreated() {
        final String titleValue = "Test 1";
        final String commentValue = "Create Comment";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .enterComment(commentValue)
                .verifySubmitIssueButtonEnabled()
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .checkCreatedIssueComment(commentValue)
                .verifyIssueStatusOpen();
    }

    @Test
    public void closeIssueWithoutComment() {
        final String titleValue = "Test 2";
        final String createCommentValue = "Create Comment";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .enterComment(createCommentValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .closeIssueButtonEnabled()
                .verifyCommentButtonDisabled()
                .clickCloseIssue()
                .verifyIssueStatusClosed();

    }

    @Test
    public void closeIssueWithComment() {
        final String titleValue = "Test 3";
        final String createCommentValue = "Create Comment";
        final String closeCommentValue = "Close Comment";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .enterComment(createCommentValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .closeCommentInputVisible()
                .enterComment(closeCommentValue)
                .verifyCommentButtonEnabled()
                .closeWithCommentButtonEnabled()
                .clickCloseWithComment()
                .verifyIssueStatusClosed()
                .checkCloseIssueComment(closeCommentValue);
    }

    @Test
    public void reopenIssueWithoutComment() {
        final String titleValue = "Test 3";
        final String createCommentValue = "Create Comment";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .enterComment(createCommentValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .clickCloseIssue()
                .verifyIssueStatusClosed()
                .reopenIssueButtonEnabled()
                .verifyCommentButtonDisabled()
                .clickReopenIssueButton()
                .verifyIssueStatusOpen()
                .checkReopenIconVisible();
    }

    @Test
    public void reopenIssueWithComment() {
        final String titleValue = "Test 4";
        final String createCommentValue = "Create Comment";
        final String reopenCommentValue = "Reopen Comment";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .enterComment(createCommentValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .clickCloseIssue()
                .verifyIssueStatusClosed()
                .enterComment(reopenCommentValue)
                .verifyCommentButtonEnabled()
                .reopenWithCommentButtonEnabled()
                .clickReopenWithCommentButton()
                .verifyIssueStatusOpen()
                .checkReopenIconVisible();
    }

    @Test
    public void deleteOpenIssue() {
        final String titleValue = "Test 5";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .verifyIssueStatusOpen()
                .deleteIssue("The issue was successfully deleted.");
    }

    @Test
    public void deleteClosedIssue() {
        final String titleValue = "Test 6";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .titleIsVisible()
                .enterTitle(titleValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .verifyIssueStatusOpen()
                .clickCloseIssue()
                .verifyIssueStatusClosed()
                .clickOpenIssueTab()
                .newIssueButtonVisible()
                .showClosedIssue()
                .clickOnCloseIssueWithTitle(titleValue)
                .verifyIssueStatusClosed()
                .deleteIssue("The issue was successfully deleted.");
    }

    @Test
    public void leaveCommentsForOpenIssue() {
        final String titleValue = "Test 7";
        final String commentValue = "Comment 1 for issue";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .verifyIssueStatusOpen()
                .clickOpenIssueTab()
                .newIssueButtonVisible()
                .clickOnOpenIssueWithTitle(titleValue)
                .verifyIssueStatusOpen()
                .enterComment(commentValue)
                .verifyCommentButtonEnabled()
                .clickCommentButton()
                .verifyLeavedComment(commentValue);
    }

    @Test
    public void setLabelForOpenIssue() {
        final String titleValue = "Test 8";
        final String labelValue = "good first issue";

        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .createNewIssue()
                .enterTitle(titleValue)
                .clickSubmitIssueButtonEnabled()
                .verifyIssueCreated(titleValue)
                .verifyIssueStatusOpen()
                .setLabel(labelValue)
                .closeLabelSelector()
                .verifyLabelForIssue(labelValue);
    }
}
