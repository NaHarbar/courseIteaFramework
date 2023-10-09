import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoriesPage extends BasePage{

    private List<WebElement> repositories = driver.findElements(By.xpath("//a[@itemprop = 'name codeRepository']"));

    public RepositoriesPage(WebDriver driver) {
        super(driver);
    }
    public List<String> getRepositories(){
        List <String> repositoriesList = repositories.stream().map(element ->element.getText()).collect(Collectors.toList());
        return repositoriesList;
    }
}
