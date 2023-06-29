package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Server.ClientHandler;
import it.polimi.ingsw.network.Server.ClientHandlerSocket;
import it.polimi.ingsw.network.Server.Server;
import it.polimi.ingsw.network.Server.ServerSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameControllerTest {
    private GameController gameController;
    private ClientHandler clientHandler;

    @Before
    public void setUp() {
        gameController = new GameController();

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
    }

    @Test
    public void getInputControllerTest() {
        assertNotNull(gameController.getInputController());
    }

    @Test
    public void getCommonGoalsTest() {
        assertNotNull(gameController.getCommonGoals());
    }

    @Test
    public void initGoalPoints_correctContent() {
        List<Integer> l = new ArrayList<>();
        gameController.initGoalPoints(l);
        assertTrue(l.contains(2));
        assertTrue(l.contains(4));
        assertTrue(l.contains(6));
        assertTrue(l.contains(8));
        assertFalse(l.contains(44));
    }

    @Test
    public void initGoalPoints_correctSize() {
        List<Integer> l = new ArrayList<>();
        gameController.initGoalPoints(l);
        assertEquals(l.size(), 4);
    }

    @Test
    public void popGoalsPoints_correctContent() {
        List<Integer> l = new ArrayList<>();
        gameController.initGoalPoints(l);
        gameController.popGoalPoints(l);
        assertFalse(l.contains(8));
    }

    @Test
    public void popGoalsPoints_correctSize() {
        List<Integer> l = new ArrayList<>();
        gameController.initGoalPoints(l);
        gameController.popGoalPoints(l);
        assertEquals(l.size(), 3);
    }

    @Test
    public void playerJoin_correctPlayersAdded() {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.playerJoin("Pietro", clientHandler);
        gameController.playerJoin("Giorgio", clientHandler);

        assertEquals(gameController.getPlayers().size(), 4);
        assertTrue(gameController.getNicknames().contains("Federica"));
        assertTrue(gameController.getNicknames().contains("Andrea"));
        assertTrue(gameController.getNicknames().contains("Pietro"));
        assertTrue(gameController.getNicknames().contains("Giorgio"));
    }

    @Test
    public void playersJoin_correctVVMap() {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.playerJoin("Pietro", clientHandler);
        gameController.playerJoin("Giorgio", clientHandler);

        assertEquals(gameController.getVirtualViewMap().size(), 4);
        assertNotNull(gameController.getVirutalViewByUser("Federica"));
        assertNotNull(gameController.getVirutalViewByUser("Andrea"));
        assertNotNull(gameController.getVirutalViewByUser("Pietro"));
        assertNotNull(gameController.getVirutalViewByUser("Giorgio"));
    }


    @Test
    public void startGameTest_correctCommonGoals() throws Exception {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();
        assertEquals(gameController.getCommonGoals().size(), 2);
        assertNotEquals(gameController.getCommonGoals().get(0), gameController.getCommonGoals().get(1));
    }

    @Test
    public void startGameTest_correctPersonalGoals() throws Exception {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        assertNotNull(gameController.getPlayers().get(0).getPersonalGoal());
        assertNotNull(gameController.getPlayers().get(1).getPersonalGoal());
        assertNotEquals(gameController.getPlayers().get(0).getPersonalGoal(), gameController.getPlayers().get(1).getPersonalGoal());
    }

    @Test
    public void startGameTest_TurnControllerCreated() throws Exception {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        assertNotNull(gameController.getTurnController());
    }

    @Test
    public void endGame() throws Exception {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        gameController.startGame();

        gameController.getPlayers().get(0).setPoints(23);
        gameController.getPlayers().get(1).setPoints(15);
        gameController.endGame();
    }

    @Test
    public void removePlayerTest_correctOutput() {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);
        Player p = gameController.getPlayerByUser("Federica");
        gameController.removePlayer("Federica");

        assertFalse(gameController.getPlayers().contains(p));
        assertEquals(gameController.getPlayers().size(), 1);
    }

    @Test
    public void getPlayers_correctOutput() throws Exception {
        Player p1 = new Player("Federica", 0, false);
        Player p2 = new Player("Andrea", 1, false);

        List<Player> p = new ArrayList<>();
        p.add(p1);
        p.add(p2);

        gameController.getPlayers().add(p1);
        gameController.getPlayers().add(p2);

        assertTrue(gameController.getPlayers().containsAll(p));

    }

    @Test
    public void getPlayerByUser_correctOutput() {
        Player p1 = new Player("Federica", 0, false);
        Player p2 = new Player("Andrea", 1, false);

        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        assertEquals(gameController.getPlayerByUser("Federica"), p1);
    }


    @Test
    public void getVirtualViewByUserTest_correctOutput() {
        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);

        assertEquals(gameController.getVirtualViewMap().get("Federica"), gameController.getVirutalViewByUser("Federica"));

    }

    @Test
    public void getNicknamesTest_correctOutput() {

        List<String> n = new ArrayList<>();
        n.add("Federica");
        n.add("Andrea");

        gameController.playerJoin("Federica", clientHandler);
        gameController.playerJoin("Andrea", clientHandler);

        assertEquals(n, gameController.getNicknames());

    }






}
