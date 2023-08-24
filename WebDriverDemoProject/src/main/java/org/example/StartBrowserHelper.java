package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

public class StartBrowserHelper {

    public static WebDriver startBrowser(BrowserTypes browserType) {
        //Setup browser options
        switch (browserType){
            case CHROME:
                return  new ChromeDriver();
            case CHROME_FOR_TESTING:
                ChromeOptions chromeForTestingOptions = new ChromeOptions();
                chromeForTestingOptions.setBinary("/Users/teddy/Downloads/chrome-mac-arm64/Google Chrome for Testing.app");
                return new ChromeDriver();
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case SAFARI:
                return new SafariDriver();
        }
        return null;
    }
}
