package ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;

import static org.testng.Assert.assertTrue;

public class GitHubTest extends BaseTestClass {

    @Test
    public void verifyThatLogoOnTheLoginPageIsDisplayed() {

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.goToLoginPage().getLogo().isDisplayed());
    }

    @Test
    public void verifyLoginSuccessful() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.goToLoginPage().validateAuthFieldsAreDisplayed()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .getLogoOnMainPage().isDisplayed());
    }

    @Test
    public void verifyFailedLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().negativeLogin("123", "123")
                .validateErrorMessage("Incorrect username or password.");
    }

    @DataProvider(name = "testDataProvider")
    public Object[][] createData() {
        return new Object[][]{
                {"Private", "Private_Test"},
                {"Public", "Public_Test"}
        };
    }

    @Test(dataProvider = "testDataProvider")
    public void verifyRepositoryCreationParametrized(String type, String name) {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .verifyLogoIsDisplayed();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCreateNewRepository()
                .verifyPageTitleLocator()
                .enterRepositoryName(name)
                .setType(type)
                .verifyNameAvailable()
                .clickCreateRepository()
                .verifyRepositoryName(name)
                .verifyRepositoryType(type);
    }

    @Test(dataProvider = "testDataProvider")
    public void verifyDeleteRepositoryParametrized(String type, String name) {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .openRepository(name)
                .verifyRepositoryName(name)
                .verifyRepositoryType(type)
                .clickSettings()
                .verifySubHeader()
                .deleteThisRepository()
                .verifySuccessfullyDeletedMessage();
    }

}
