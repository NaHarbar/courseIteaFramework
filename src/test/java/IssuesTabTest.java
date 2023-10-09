import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static helpers.Level.INFO;
import static helpers.PrinterColors.printColorMessage;

public class IssuesTabTest extends BaseTestClass {

    @BeforeEach
    public void openRepository() {
        printColorMessage("New test was started", logger, INFO);
        final String repositoryName = "TestRepository";

        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .openRepository(repositoryName)
                .verifyRepositoryName(repositoryName);
        logger.info("Repository was opened");
    }

    @Test
    public void verifyIssueTabCanBeOpened() {
        CodeTabPage codeTabPage = new CodeTabPage(driver);
        codeTabPage
                .openIssueTabPage()
                .verifySearchIssueVisible()
                .newIssueButtonVisible();
        logger.info("The Issue tab has corresponding view");
    }

    @Test
    public void verifyCreateIssueFields() {
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
        logger.info("Create Issue Page has corresponding view");
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
        logger.info("A new Issue was successfully created");
    }

    @Test
    public void verifyCloseIssueWithoutComment() {
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
        logger.info("Issue was closed without comment");
    }

    @Test
    public void verifyCloseIssueWithComment() {
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
        logger.info("Issue was closed with comment {}", closeCommentValue);
    }

    @Test
    public void verifyReopenIssueWithoutComment() {
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
        logger.info("Issue {} was reopen without comment", titleValue);
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
        logger.info("Issue {} was reopen with comment", titleValue);
    }

    @Test
    public void verifyAbilityToDeleteOpenIssue() {
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
        logger.info("The open issue {} was deleted", titleValue);
    }

    @Test
    public void verifyAbilityToDeleteClosedIssue() {
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
        logger.info("The closed issue {} was deleted", titleValue);
    }

    @Test
    public void verifyAbilityToLeaveCommentsForOpenIssue() {
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
        logger.info("The open issue {}  has comment {}", titleValue, commentValue);
    }

    @Test
    public void verifyAbilityToSetLabelForOpenIssue() {
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
        logger.info("The issue {}  has label{}", titleValue, labelValue);
    }
}
