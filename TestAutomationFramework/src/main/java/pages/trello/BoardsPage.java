package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardsPage extends BaseTrelloPage {

    WebDriverWait webDriverWait;

    public BoardsPage(WebDriver driver) {
        super(driver, "trello.boardsPage");
    }

    public void createBoard() {

        String boardName = getUIMappingByKey("trello.boardName");

        actions.waitForElementClickable("trello.header.create.menuButton");
        actions.clickElement("trello.header.create.menuButton");

        actions.waitForElementClickable("trello.header.createBoard.dropDownButton");
        actions.clickElement("trello.header.createBoard.dropDownButton");

        actions.waitForElementClickable("trello.createBoard.titleInput");
        actions.typeValueInField(boardName, "trello.createBoard.titleInput");

        actions.waitForElementClickable("trello.create.board.submitButton");
        actions.clickElement("trello.create.board.submitButton");
    }

    public void clickOnBoard(String boardName) {
        actions.waitForElementVisible("trello.boardsPage.boardByTeamAndName", boardName);
        actions.clickElement("trello.boardsPage.boardByTeamAndName", boardName);
    }

    public void assertBoardExists() {
        actions.waitForElementPresent("trello.boardsPage.boardByTeamAndName", "Board Name from Automation");

    }

    public void deleteBoard() throws InterruptedException {
        // Locate and click the "Show Menu" button
        actions.hoverElement("//*[@data-rbd-droppable-id=starred-boards-left-nav");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement showMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Show Menu']")));
        showMenuButton.click();

        // Locate and click the "More" option
        WebElement moreOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='More']")));
        moreOption.click();

        // Locate and click the "Close Board" option
        WebElement closeBoardOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Close Board']")));
        closeBoardOption.click();

        // Confirm board closure
        WebElement confirmCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Close']")));
        confirmCloseButton.click();
    }

    public void assertBoardIsDeleted() {

    }
}
