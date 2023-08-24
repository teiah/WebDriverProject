package googlesearchtests;

import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.StartBrowserHelper.startBrowser;

public class GoogleSearchByTermTests {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    public static void classSetup(){
        // Set desired driver from enum
        driver = startBrowser(BrowserTypes.CHROME);

        // Navigate to bing.com
        driver.get("https://google.com");

        // Wait for 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterAll
    public static void classTearDown(){
        driver.close();
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
        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));
        Assertions.assertEquals("IT Career Start in 6 Months - Telerik Academy Alpha", firstResult.getText(), "Search result not found.");

    }
}
