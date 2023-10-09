package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoriesPage extends BasePage {
    private final static String TITLE = "Repositories Page";
    private final By deleteMessageLocator = By.xpath("//div[@role='alert']");
    private List<WebElement> repositories = driver.findElements(By.xpath("//a[@itemprop = 'name codeRepository']"));

    public RepositoriesPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public RepositoriesPage verifySuccessfullyDeletedMessage() {
        String actualMessage = driver.findElement(deleteMessageLocator).getText();
        Assert.assertTrue(actualMessage.contains("was successfully deleted."));
        return this;
    }
    public List<String> getRepositories(){
        List <String> repositoriesList = repositories.stream().map(element ->element.getText()).collect(Collectors.toList());
        return repositoriesList;
    }
}
