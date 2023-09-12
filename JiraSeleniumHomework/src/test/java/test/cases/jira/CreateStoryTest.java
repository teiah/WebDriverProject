package test.cases.jira;

import com.telerikacademy.testframework.datainput.Data;
import enums.IssueTypes;
import enums.PriorityLevels;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.jira.ProjectPage;

public class CreateStoryTest extends BaseTest{

    @Test
    public void createStoryTest() throws InterruptedException {

        login();
        ProjectPage projectPage = new ProjectPage(actions.getDriver());
        projectPage.createIssue(Data.summaryStory,Data.descriptionStory, IssueTypes.STORY, PriorityLevels.HIGH);

        projectPage.assertIssueIsCreated();
    }
}
