package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;
import java.rmi.Remote;
/**
 * Interface to handle clients. Every type of connection must implement this interface.
 */
public interface ClientHandler extends Remote {
    void run();

    /**
     * Sends a message to the client.
     *
     * @param event the message to be sent.
     */
    void sendMessage(Event event);

    /**
     * Disconnects from the client.
     */
    void disconnect();
}
