package bingsearchtests;

import org.example.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.StartBrowserHelper.startBrowser;

public class BingSearchByTermTests {

    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    public static void classSetup(){
        // Set desired driver from enum
        driver = startBrowser(BrowserTypes.CHROME);

        // Navigate to bing.com
        driver.get("https://bing.com");

        // Wait for 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterAll
    public static void classTearDown(){
        driver.close();
    }

    @Test
    public void resultFound_when_searchProvided() throws InterruptedException {

        // Accept cookies
//        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='bnp_btn_accept']"));
//        agreeButton.click();

        // Type text in search field
//        WebElement searchField = driver.findElement(By.id("sb_form_q"));
        WebElement searchField = driver.findElement(By.xpath("//*[@id='sb_form_q']"));
        searchField.click();
        searchField.sendKeys("Telerik Academy Alpha");
        searchField.sendKeys(Keys.ENTER);

        // Assert result found
        WebElement firstResult = driver.findElement(By.xpath("(//h2/a)[1]"));
        wait.until(ExpectedConditions.visibilityOf(firstResult));
        Assertions.assertEquals("IT Career Start in 6 Months - Telerik Academy Alpha",
                firstResult.getText(), "Search result not found.");
    }
}
