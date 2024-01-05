package base;

import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    protected static WebDriver startBrowser(BrowserTypes browserType) {
        // Setup Browser
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case SAFARI:
                return new SafariDriver();
        }

        return null;
    }

    @AfterEach
    public void afterTest() {
        // close driver
        driver.close();
    }
}
