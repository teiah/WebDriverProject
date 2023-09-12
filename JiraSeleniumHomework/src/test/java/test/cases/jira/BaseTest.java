package test.cases.jira;

import pages.jira.LoginPage;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    UserActions actions = new UserActions();

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("jira.homePage");
    }

    @AfterClass
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public void login() throws InterruptedException {

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.jiraLogin("user");
    }

}
