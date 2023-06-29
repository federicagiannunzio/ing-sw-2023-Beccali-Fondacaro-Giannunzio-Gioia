package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.Events.ChooseTileReplyEvent;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Events.PlaceTileReplyEvent;
import it.polimi.ingsw.network.Events.TilesNumberReplyEvent;
import it.polimi.ingsw.network.Server.ClientHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputControllertest {

    private InputController inputController;
    private GameController gameController;
    private ClientHandler clientHandler;

    @Before
    public void setUp() {
        gameController = new GameController();
        inputController = new InputController(gameController);
        clientHandler = new ClientHandler() {
            @Override
            public void run() {

            }

            @Override
            public void sendMessage(Event event) {

            }

            @Override
            public void disconnect() {

            }
        };
    }

    @After
    public void tearDown() {
        gameController = null;
        inputController = null;
    }

    @Test
    public void checkUsernameValidityTest_validUsername_correctOutput() {

        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        assertTrue(inputController.checkUsernameValidity("Andrea", VView));

    }

    @Test
    public void checkUsernameValidityTest_notValidUsername_correctOutput() {

        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        assertFalse(inputController.checkUsernameValidity("Federica", VView));

    }

    @Test
    public void pickedTileCheckTest_availableTile_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        int [] r = { 1, 1, 2};
        int [] c = { 3, 4, 3};

        ChooseTileReplyEvent chooseTileReplyEvent = new ChooseTileReplyEvent("Federica", r, c);
        assertTrue(inputController.pickedTileCheck(chooseTileReplyEvent, VView));

    }

    @Test
    public void pickedTileCheckTest_noAdjacentTiles_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        int [] r = { 2, 1, 2};
        int [] c = { 4, 4, 3};

        ChooseTileReplyEvent chooseTileReplyEvent = new ChooseTileReplyEvent("Federica", r, c);
        assertFalse(inputController.pickedTileCheck(chooseTileReplyEvent, VView));

    }

    @Test
    public void pickedTileCheckTest_tileNotExist_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        int [] r = { 0, 1, 2};
        int [] c = { 0, 4, 3};

        ChooseTileReplyEvent chooseTileReplyEvent = new ChooseTileReplyEvent("Federica", r, c);
        assertFalse(inputController.pickedTileCheck(chooseTileReplyEvent, VView));
    }

    @Test
    public void numberOfTilesTest_notThatMuch_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();
        gameController.getBoard().initializeBlackBoard(2);
        gameController.getBoard().setTile(1, 4 , Color.BLUE);
        gameController.getBoard().setTile(2, 4 , Color.BLUE);

        TilesNumberReplyEvent tilesNumberReplyEvent = new TilesNumberReplyEvent("Federica", 3);
        assertFalse(inputController.numberOfTilesCheck(tilesNumberReplyEvent, VView));

    }

    @Test
    public void numberOfTilesTest_availableTiles_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        TilesNumberReplyEvent tilesNumberReplyEvent = new TilesNumberReplyEvent("Federica", 2);
        assertTrue(inputController.numberOfTilesCheck(tilesNumberReplyEvent, VView));

    }

    @Test
    public void insertedTileCheckTest_availablePosition_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        PlaceTileReplyEvent tileToPlace = new PlaceTileReplyEvent("Federica", 0);
        assertTrue(inputController.insertedTileCheck(tileToPlace, VView));

    }

    @Test
    public void insertedTileCheckTest_fullColumn_correctOutput() throws Exception {
        VirtualView VView = new VirtualView(clientHandler);
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.PURPLE, 0);


        gameController.getPlayerByUser("Federica").getMyShelfie().setShelfTile(0,0, t1);
        gameController.getPlayerByUser("Federica").getMyShelfie().setShelfTile(1,0, t2);
        gameController.getPlayerByUser("Federica").getMyShelfie().setShelfTile(2,0, t3);
        gameController.getPlayerByUser("Federica").getMyShelfie().setShelfTile(3,0, t4);

        TilesNumberReplyEvent tilesNumberReplyEvent = new TilesNumberReplyEvent("Federica", 2);
        gameController.update(tilesNumberReplyEvent);

        PlaceTileReplyEvent tileToPlace = new PlaceTileReplyEvent("Federica", 0);
        assertFalse(inputController.insertedTileCheck(tileToPlace, VView));

    }



}
