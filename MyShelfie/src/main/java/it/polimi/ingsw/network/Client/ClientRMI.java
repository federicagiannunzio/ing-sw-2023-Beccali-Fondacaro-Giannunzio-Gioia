package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;
import java.rmi.RemoteException;

public class ClientRMI implements Client, InterfaceRMI {

    public ClientRMI(){

    }
    @Override
    public void sendMessage(Event event) {

    }

    @Override
    public void closeConnection(){

    }
}
