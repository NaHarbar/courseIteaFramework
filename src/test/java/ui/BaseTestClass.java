package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static helpers.Level.DEBUG;
import static helpers.Level.INFO;
import static helpers.PrinterColors.printColorMessage;

public class BaseTestClass {
    protected WebDriver driver;
    protected Logger logger;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Course_ITEA\\courseIteaFramework\\src\\main\\resources\\driveres\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.logger = LogManager.getLogger();
        logger.info("Chrome driver object is starting");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://github.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        printColorMessage("Test was finished", logger, INFO);
    }
}
