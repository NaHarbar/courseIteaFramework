import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

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

    @Test
    public void verifyRepositoriesList() {
        HomePage homePage = new HomePage(driver);
        List<String> actualRepositories = homePage.goToLoginPage().validateAuthFieldsAreDisplayed()
                .loginSuccessful("nataliia.rudenko2012@gmail.com", "nataliia.rudenko2012")
                .goToProfileForm()
                .goToRepositoriesPage()
                .getRepositories();
        List<String> expectedRepositories = List.of("courseIteaFramework", "courseITEA", "myITEAFramework", "TestRepository");
        Assertions.assertEquals(actualRepositories, expectedRepositories, "ActualRepositories not matched " +
                "with ExpectedRepositories");

        Actions actions = new Actions(driver);

    }

}
