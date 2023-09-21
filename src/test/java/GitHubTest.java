import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GitHubTest {

    private WebDriver driver;

    @Test
    public void gitHubTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Course_ITEA\\courseIteaFramework\\src\\main\\resources\\driveres\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://github.com/");
    }
}
