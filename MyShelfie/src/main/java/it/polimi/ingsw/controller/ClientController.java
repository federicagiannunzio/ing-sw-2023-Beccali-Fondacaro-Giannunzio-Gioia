package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.network.Client.ClientSocket;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Events.LoginRequestEvent;
import it.polimi.ingsw.network.Events.UsersNumberEvent;

import java.io.IOException;

public class ClientController implements Observer {
    private String username;
    private ClientSocket client;

    /**
     * Sends a message to the server that contains the username that he has picked
     *
     * @param username username that will be sent
     * */
    public void sendUsername(String username) throws IOException {
        this.username = username;
        client.sendMessage(new LoginRequestEvent(username));
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) throws IOException {
        client.sendMessage(new UsersNumberEvent(username, playersNum));
    }

    /**
     * Sends a message to the server that contains the username of the first player.
     *
     * @param usernameFirst the username of the first player
     */
    public void sendFirstPlayerChosen(String usernameFirst) {

    }

    /**
     * Sends a message to the server that contains the number of tiles
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new tilesNumberRequestEvent());
    }

    /**
     *
     */
    public void sendTilePosition(String )






}
