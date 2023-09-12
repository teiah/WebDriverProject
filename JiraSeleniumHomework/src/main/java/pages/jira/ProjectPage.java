package pages.jira;

import com.telerikacademy.testframework.pages.BasePage;
import enums.IssueTypes;
import enums.LinkIssuesOptions;
import enums.PriorityLevels;
import org.junit.Assert;
import org.openqa.selenium.*;

public class ProjectPage extends BasePage {
    public ProjectPage(WebDriver driver) {
        super(driver, "jira.loginPage");
    }

    public void createIssue(String summary, String description, IssueTypes type, PriorityLevels level) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        actions.clickElement("jira.create.button");
        Thread.sleep(5000);

        // Select issue type
        actions.waitForElementClickable("jira.issueTypeBar");
        actions.clickElement("jira.issueTypeBar");
        driver.switchTo().activeElement().sendKeys(type.name() + Keys.RETURN);

        // Add summary
        actions.waitForElementVisible("jira.issue.summary");
        actions.typeValueInField(summary, "jira.issue.summary");

        // Add description
        WebElement descriptionField = driver.findElement(By.xpath(
                "//*[starts-with(@aria-label,'Description')]"));
        js.executeScript("arguments[0].scrollIntoView();", descriptionField);
        actions.clickElement("jira.descriptionField");
        driver.switchTo().activeElement().sendKeys(description);

        // Add priority level
        WebElement priority = driver.findElement(By.xpath("//label[@id='priority-field-label']"));
        js.executeScript("arguments[0].scrollIntoView();", priority);
        priority.click();
        driver.switchTo().activeElement().sendKeys(level.name() + Keys.RETURN);

        // Create
        actions.clickElement("jira.createIssue.button");
        Thread.sleep(5000);

    }

    public void assertIssueIsCreated() {
        driver.findElement(By.xpath(
                "//div[@data-testid='jira-issue-create.modal.create-form.success-flag']"));
    }

    public void refreshIssueList() throws InterruptedException {
        actions.clickElement("jira.refreshButton");
        Thread.sleep(5000);
        actions.waitForElementVisible("jira.issue.header");
    }

    public String saveID() {
        WebElement popUp = driver.findElement(By.xpath(
                "//div[@data-testid='jira-issue-create.modal.create-form.success-flag']"));
        return popUp.getAttribute("data-issue-key");
    }

    public void findIssueByID(String ID) throws InterruptedException {
        actions.clickElement("jira.issueID", ID);
        Thread.sleep(5000);
    }

    public void linkIssues(LinkIssuesOptions options, String ID) throws InterruptedException {
        actions.clickElement("jira.issue.LinkIssueButton");
        actions.waitForElementVisible("jira.issue.LinkIssueOptions");
        driver.switchTo().activeElement().sendKeys(ID + Keys.RETURN);
        actions.waitForElementVisible("jira.issue.LinkIssueOptions");
        actions.clickElement("jira.issue.LinkIssueOptions");
        driver.switchTo().activeElement().sendKeys(options.toString() + Keys.RETURN);
        Thread.sleep(5000);
        actions.waitForElementVisible("jira.issue.linkButton");
        actions.clickElement("jira.issue.linkButton");
        Thread.sleep(5000);
    }

    public void assertLinkIsSuccessful(String ID) throws InterruptedException {
        WebElement jqlBar = driver.findElement(By.xpath("//div[@aria-label='JQL query']"));

        for (int i = 0; i < 40; i++) {
            jqlBar.sendKeys(Keys.BACK_SPACE);
        }

        jqlBar.sendKeys("project = \"JIR\" and issueIsBlockedBy = ", ID + Keys.RETURN);
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElements(By.linkText("No issues were found matching your search")).isEmpty());


    }
}
