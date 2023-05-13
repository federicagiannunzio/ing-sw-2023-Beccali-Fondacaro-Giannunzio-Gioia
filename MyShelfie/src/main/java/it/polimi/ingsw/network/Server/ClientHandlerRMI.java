package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;

public class ClientHandlerRMI implements ClientHandler{
    private boolean inGame = true;
    public void run(){
        server.receive(event){
            //scarta evento e leggi il nickname, individua observer giusto, inolti messaggio all'observer

        }
    }

    @Override
    public void sendMessage(Event event) {

    }
}
