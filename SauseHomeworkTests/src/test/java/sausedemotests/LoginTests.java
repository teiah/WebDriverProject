package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @BeforeAll
    public static void beforeAllTests() {
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to Google.com
        driver.get("https://www.saucedemo.com/");
    }



    @Test
    public void userAuthenticated_when_validCredentialsProvided() {
        authenticateWithUser("standard_user", "secret_sauce");

        // Add Assert
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://www.saucedemo.com/inventory.html");
//        int t = driver.findElements(By.xpath("//*[@class='app_logo']")).size();
//        if (t > 0) {
//            System.out.println("Element is present");
//        } else {
//            System.out.println("Element is not present");
//        }
        driver.close();
    }
}
