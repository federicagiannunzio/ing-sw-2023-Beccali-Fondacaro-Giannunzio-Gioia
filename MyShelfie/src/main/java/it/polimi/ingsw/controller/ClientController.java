package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientSocket;
import it.polimi.ingsw.network.Events.UsersNumberEvent;

import java.io.IOException;

public class ClientController {
    private String username;
    private ClientSocket client;

    /**
     * Sends a message to the server that comtains the username that he has picked
     *
     * @param username username that will be sent
     * */
    public void sendUsername(String username) {
        this.username = username;
        client.sendMessage(new LoginRequest(this.username));
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) throws IOException {
        client.sendMessage(new UsersNumberEvent(this.username, playersNum));
    }

    /**
     *
     * */



}
