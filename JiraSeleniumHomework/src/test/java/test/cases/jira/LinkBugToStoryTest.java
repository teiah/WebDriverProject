package test.cases.jira;

import com.telerikacademy.testframework.datainput.Data;
import enums.IssueTypes;
import enums.LinkIssuesOptions;
import enums.PriorityLevels;
import org.junit.Test;
import pages.jira.ProjectPage;

public class LinkBugToStoryTest extends BaseTest{

    @Test
    public void linkBugToStoryTest() throws InterruptedException {
        login();
        ProjectPage projectPage = new ProjectPage(actions.getDriver());

        // create a story and save itd ID
        projectPage.createIssue(Data.summaryStory,Data.descriptionStory, IssueTypes.STORY, PriorityLevels.HIGH);
        String storyID = projectPage.saveID();

        // create a bug and save its ID
        projectPage.createIssue(Data.summaryBug, Data.descriptionBug, IssueTypes.BUG, PriorityLevels.HIGHEST);
        String bugID = projectPage.saveID();

        projectPage.refreshIssueList();

        // add link between the story and bug
        projectPage.findIssueByID(storyID);
        projectPage.linkIssues(LinkIssuesOptions.IS_BLOCKED_BY, bugID);

        projectPage.assertLinkIsSuccessful(bugID);
    }
}
