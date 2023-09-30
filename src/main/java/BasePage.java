import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Level.DEBUG;
import static helpers.PrinterColors.printColorMessage;
import static java.time.Duration.ofMillis;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Logger log;
    protected String title;

    public BasePage(WebDriver driver, String title) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, ofMillis(3));
        this.log = LogManager.getLogger(this.title);
        printColorMessage("Page object of " + title + this.getClass().getName(), log, DEBUG);
    }
}
