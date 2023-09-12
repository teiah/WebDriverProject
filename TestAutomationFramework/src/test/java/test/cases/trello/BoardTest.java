package test.cases.trello;

import api.BoardModel;
import api.CardModel;
import api.ListModel;
import api.TrelloApi;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BoardTest extends BaseTest {


    private TrelloApi trelloApi = new TrelloApi();
    private BoardModel createdBoard;

    @Before
    public void beforeTest() {
        createdBoard = trelloApi.createBoard("Board Name from Automation", "Description");
    }

    @After
    public void afterTest() {
        var deletionResponse = trelloApi.deleteBoard(createdBoard.id);
    }

    @Test
    public void createBoardWhenCreateBoardClicked() {
        login();

//        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
//        boardsPage.createBoard();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.assertBoardExists();
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {
        login();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("My First Board");

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList("something");

        // Assert
        boardPage.assertCardExists();
    }


    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() throws InterruptedException {
        login();
        ListModel responseListFrom = trelloApi.createList(createdBoard.id, "ListNameAutoFrom");
        ListModel responseListTo = trelloApi.createList(createdBoard.id, "ListNameAutoTo");
        Response responseCreatedCard = trelloApi.createCard(responseListFrom.id, "CardNameAuto");
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("Board Name from Automation");
        Thread.sleep(5000);
        actions.dragAndDropElement("trello.boardPage.cardByName", "trello.boardPage.listByName");

        // Assert
        CardModel movedCard = trelloApi.getCardByNameFromList(responseListTo.id, "CardNameAuto");
        assertNotNull(movedCard); // Check if the card is found in the target list

        // Assert that the card is no longer in the original list
        CardModel originalCard = trelloApi.getCardByNameFromList(responseListFrom.id, "CardNameAuto");
        assertNull(originalCard); // Check if the card is not found in the original list
    }


    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() throws InterruptedException {
        // login and create additional board
        login();
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        // delete board
        boardsPage.deleteBoard();

        // Assert
    }
}
