import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GitHubTest extends BaseTestClass {

    @Test
    public void verifyThatLogoOnTheLoginPageIsDisplayed() {

        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().getLogo().isDisplayed());
    }

    @Test
    public void verifyLoginSuccessful() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().validateAuthFieldsAreDisplayed()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .getLogoOnMainPage().isDisplayed());
    }

    @Test
    public void verifyFailedLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().negativeLogin("123", "123")
                .validateErrorMessage("Incorrect username or password.");
    }
}
