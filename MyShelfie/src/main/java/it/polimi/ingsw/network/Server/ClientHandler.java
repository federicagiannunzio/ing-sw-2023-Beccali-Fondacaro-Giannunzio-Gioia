package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;
import java.rmi.Remote;

public interface ClientHandler extends Remote {
    void sendMessage(Event event);

    void disconnect();
}
