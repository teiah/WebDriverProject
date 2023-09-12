package pages.jira;

import com.telerikacademy.testframework.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseJiraPage {

    public LoginPage(WebDriver driver) {
        super(driver, "jira.loginPage");
    }


    public void jiraLogin(String userKey) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String projectName = PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.projectName");

        String username = getConfigPropertyByKey("jira.users." + userKey + ".username");
        String password = getConfigPropertyByKey("jira.users." + userKey + ".password");

        // Enter username
        actions.waitForElementVisible("jira.loginPage.username");
        actions.typeValueInField(username, "jira.loginPage.username");

        actions.waitForElementClickable("login.button");
        actions.clickElement("login.button");

        // Enter password
        actions.waitForElementVisible("jira.loginPage.password");
        actions.typeValueInField(password, "jira.loginPage.password");

        // Find and click the login button
        actions.waitForElementClickable("login.button");
        actions.clickElement("login.button");

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='sc-cvbbAY fLkdyQ' and text()='Jira Software']")));


        // Click on the "Projects" link
        actions.clickElement("jira.software");
        Thread.sleep(2000);
        actions.waitForElementVisible("jira.project", projectName);
        actions.clickElement("jira.project", projectName);
        Thread.sleep(2000);
    }
}
