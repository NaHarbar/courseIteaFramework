package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileForm extends BasePage{
    private final static String TITLE = "Profile Form page";
    private final By yourRepositoriesLocator = By.xpath("//span[contains(text(),'Your repositories')]");


    public ProfileForm(WebDriver driver) {
        super(driver, TITLE);
    }

    public RepositoriesPage goToRepositoriesPage(){
        driver.findElement(yourRepositoriesLocator).click();
        return new RepositoriesPage(driver);
    }
}
