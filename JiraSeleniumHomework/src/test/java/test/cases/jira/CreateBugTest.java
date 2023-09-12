package test.cases.jira;

import com.telerikacademy.testframework.datainput.Data;
import enums.IssueTypes;
import enums.PriorityLevels;
import org.junit.Test;
import pages.jira.ProjectPage;

public class CreateBugTest extends BaseTest {

    @Test
    public void createBugTest() throws InterruptedException {

        login();
        ProjectPage projectPage = new ProjectPage(actions.getDriver());
        projectPage.createIssue(Data.summaryBug, Data.descriptionBug, IssueTypes.BUG, PriorityLevels.HIGHEST);

        projectPage.assertIssueIsCreated();
    }
}
