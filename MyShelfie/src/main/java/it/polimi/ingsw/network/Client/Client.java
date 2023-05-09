package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;

public interface Client {

    public void receiveMessage();

    public void sendMessage(Event event);

    public void closeConnection();
}
