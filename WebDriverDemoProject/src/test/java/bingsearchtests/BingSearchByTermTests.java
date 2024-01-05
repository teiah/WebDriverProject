package bingsearchtests;

import base.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class BingSearchByTermTests extends BaseTest {

    @BeforeAll
    public static void classSetup() {
        // Set desired driver (
        //        CHROME,
        //        SAFARI)
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to bing.com
        driver.get("https://bing.com");

        // Accept Terms
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bnp_btn_accept")));
        WebElement acceptButton = driver.findElement(By.id("bnp_btn_accept"));
        acceptButton.click();
    }

    @Test
    public void resultFound_when_searchProvided() {
        String searchTerm = "Telerik Academy Alpha";

        // Type search term in search field
        WebElement searchField = driver.findElement(By.xpath("//*[@id='sb_form_q']"));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);

        String expectedResult1 = "Learning Platform - Telerik Academy";
        String expectedResult2 = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String expectedResult3 = "QA Training - Telerik Academy Alpha";

        // Assert result found
        WebElement resultAnchor = driver.findElement(By.xpath("(//div[@class='b_title'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(resultAnchor));

        String actualResult = resultAnchor.getText();

        List<String> expectedResults = Arrays.asList(expectedResult1, expectedResult2, expectedResult3);

        Assertions.assertTrue(expectedResults.stream().anyMatch(actualResult::contains),
                "Expected result not found. Actual text: " + actualResult + ".");
    }
}
