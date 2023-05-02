package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.ClientSocket;
import it.polimi.ingsw.network.Events.LoginRequestEvent;

import java.io.IOException;

public class ClientController {
    private String username;
    private ClientSocket client;

    /**
     * Sends a message to the server that comtains the username that he has picked
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
    public void sendPlayersNumber(int playersNum) {
        client.sendMessage(new PlayerNumberReply(this.nickname, playersNum));
    }

    /**
     *
     * */



}
