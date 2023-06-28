package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.personalGoal;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Server.ClientHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurnControllerTest {
    TurnController turnController;
    GameController gameController;

    ClientHandler clientHandler;

    @Before
    public void setUp() {
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

        gameController = new GameController();
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        turnController = new TurnController(gameController, gameController.getVirtualViewMap());

    }

    @After
    public void tearDown() {
        gameController = null;
        turnController = null;
    }


    @Test
    public void removeVirtualViewMapTest_correctOutput() {
        turnController.removeVirtualViewMap("Federica");
        assertEquals(turnController.getVirtualViewMap().size(), 1);
        assertNull(turnController.getVirtualViewMap().get("Federica"));
        assertNotNull(turnController.getVirtualViewMap().get("Andrea"));
    }

    @Test
    public void pointsUpdating_personalGoalPointsUpdate_correctOutput() throws Exception {
        //gameController.startGame();
        personalGoal p1 = new personalGoal(0);
        Tile t1 = new Tile(Color.PURPLE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);

        p1.createTheGoal(p1.getId());

        gameController.getPlayers().get(0).setPersonalGoal(p1);

        gameController.getPlayers().get(0).getMyShelfie().setShelfTile(0,0, t1);
        gameController.getPlayers().get(0).getMyShelfie().setShelfTile(0,2, t2);

        turnController.pointsUpdating();
        assertEquals(2, gameController.getPlayers().get(0).getPoints());

    }

    @Test
    public void beginTurnChoiceTest() {

    }



}
