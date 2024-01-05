package googlesearchtests;

import base.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class GoogleSearchByTermTests extends BaseTest {

    @BeforeAll
    public static void classSetup(){
        // Set desired driver (
        //      CHROME,
        //      SAFARI)
        driver = startBrowser(BrowserTypes.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Navigate to bing.com
        driver.get("https://google.com");

        // Wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void resultFound_when_searchProvided() {

        // Accept cookies
        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        agreeButton.click();

        // Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys("Telerik Academy Alpha");

        // Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[2]"));
        searchButton.click();

        // Assert result found
        String expectedResult = "IT Career Start in 6 Months - Telerik Academy Alpha";
        WebElement resultAnchor = driver.findElement(By.xpath("//a/h3"));
        wait.until(ExpectedConditions.visibilityOf(resultAnchor));
        Assertions.assertTrue(resultAnchor.getText().contains(expectedResult),
                "Expected result '" + expectedResult + "' not found. Actual text: " + resultAnchor.getText());
    }
}
