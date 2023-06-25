package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.network.Events.Event;
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

}
