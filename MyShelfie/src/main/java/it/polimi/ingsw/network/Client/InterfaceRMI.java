package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
    void sendMessage(Event event) throws IOException, RemoteException;
}
