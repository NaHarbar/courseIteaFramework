package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MainPage extends BasePage {
    private final static String TITLE = "Main page";
    private final static String searchResultLocator = "(//a[@data-hovercard-type = 'repository' and contains(@href, '%s')])[2]";
    private final By imageLocator = By.xpath("(//img[@class = 'avatar circle'])[1]");
    private final By searchRepositoryLocator = By.id("dashboard-repos-filter-left");
    private final By actionPanelButtonLocator = By.id("global-create-menu-button");
    private final By addRepositoryButtonLocator = By.xpath("//span[contains(text(),'New repository')]");

    public MainPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public WebElement getLogoOnMainPage() {
        return driver.findElement(imageLocator);
    }

    public CodeTabPage openRepository(String name) {
        driver.findElement(searchRepositoryLocator).sendKeys(name);
        driver.findElement(By.xpath(searchResultLocator.formatted(name))).click();
        return new CodeTabPage(driver);
    }

    public ProfileForm goToProfileForm() {
        Assert.assertTrue(driver.findElement(imageLocator).isDisplayed());
        driver.findElement(imageLocator).click();
        return new ProfileForm(driver);
    }

    public MainPage verifyLogoIsDisplayed(){
        Assert.assertTrue(getLogoOnMainPage().isDisplayed());
        return this;
    }

    public CreateNewRepositoryPage clickCreateNewRepository(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(actionPanelButtonLocator));
        driver.findElement(actionPanelButtonLocator).click();
        driver.findElement(addRepositoryButtonLocator).click();
        return new CreateNewRepositoryPage(driver);
    }

}
