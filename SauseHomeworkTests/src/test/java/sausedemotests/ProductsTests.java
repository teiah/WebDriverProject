package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsTests extends BaseTest {

    @BeforeAll
    public static void beforeAllTests() {
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to Google.com
        driver.get("https://www.saucedemo.com/");

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @AfterEach
    public void resetSite() throws InterruptedException {
        WebElement hamburgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        hamburgerMenu.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bm-item-list")));
        WebElement resetButton = driver.findElement(By.id("reset_sidebar_link"));
        Thread.sleep(5000);
        resetButton.click();
        WebElement allItemsButton = driver.findElement(By.id("inventory_sidebar_link"));
        Thread.sleep(5000);
        allItemsButton.click();
    }
    @AfterAll
    public static void afterTest(){
        // close driver
        driver.close();
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart() throws InterruptedException {
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        addItemToCart(backpackTitle, "btn_inventory");
        addItemToCart(shirtTitle, "btn_inventory");

        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(2, items.size(), "Items count not as expected");

        Assertions.assertEquals(backpackTitle, items.get(0).getText(), "Item title not as expected");
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), "Item title not as expected");
    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation() throws InterruptedException {

        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        addItemToCart(backpackTitle, "btn_inventory");
        addItemToCart(shirtTitle, "btn_inventory");

        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert Items and Totals
        driver.findElement(By.id("checkout")).click();

        // fill form
        fillShippingDetails("Fname", "lname", "zip");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(2, items.size(), "Items count not as expected");

        var total = driver.findElement(By.className("summary_total_label")).getText();
        double expectedPrice = 29.99 + 15.99 + 3.68;
        String expectedTotal = String.format("Total: $%.2f", expectedPrice);

        Assertions.assertEquals(2, items.size(), "Items count not as expected");
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), "Item title not as expected");
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), "Item title not as expected");
        Assertions.assertEquals(expectedTotal, total, "Items total price not as expected");
    }


    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() throws InterruptedException {

        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        addItemToCart(backpackTitle, "btn_inventory");
        addItemToCart(shirtTitle, "btn_inventory");

        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert Items and Totals
        driver.findElement(By.id("checkout")).click();

        // fill form
        fillShippingDetails("Fname", "lname", "zip");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(2, items.size(), "Items count not as expected");

        // Complete Order
        driver.findElement(By.id("finish")).click();
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://www.saucedemo.com/checkout-complete.html");

        // Assert Items removed from Shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();
        var item = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(0, item.size(), "Shopping cart is not empty");
    }

}