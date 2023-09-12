package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList(String cardName) {

        //create list
        String listName = "stupid list";
        actions.waitForElementVisible("trello.boardPage.addListButton");
        actions.clickElement("trello.boardPage.addListButton");
        actions.waitForElementVisible("trello.boardPage.enterListName");
        actions.typeValueInField(listName, "trello.boardPage.enterListName");
        actions.waitForElementClickable("trello.boardPage.completeList");
        actions.clickElement("trello.boardPage.completeList");

        //add card to list
        actions.waitForElementClickable("trello.boardPage.addCardButton");
        actions.clickElement("trello.boardPage.addCardButton");
        actions.waitForElementClickable("trello.boardPage.enterCardName");
        actions.typeValueInField(cardName, "trello.boardPage.enterCardName");
        actions.waitForElementClickable("trello.boardPage.completeCard");
        actions.clickElement("trello.boardPage.completeCard");

    }

    public void moveCardToList(String cardName, String listName) {
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");
    }

    public void assertCardExists() {
        actions.waitForElementPresent("trello.boardPage.card");
    }

}
