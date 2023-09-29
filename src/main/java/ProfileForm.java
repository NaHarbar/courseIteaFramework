import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileForm extends BasePage{
    private final By yourRepositoriesLocator = By.xpath("//span[contains(text(),'Your repositories')]");

    public ProfileForm(WebDriver driver) {
        super(driver);
    }

    public RepositoriesPage goToRepositoriesPage(){
        driver.findElement(yourRepositoriesLocator).click();
        return new RepositoriesPage(driver);
    }
}
