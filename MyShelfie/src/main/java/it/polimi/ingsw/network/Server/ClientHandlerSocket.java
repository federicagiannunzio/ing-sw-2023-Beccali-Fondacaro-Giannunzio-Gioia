package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerSocket implements ClientHandler, Runnable {

    private final Socket connectedClient;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final Scanner scanner = new Scanner(System.in);

    private boolean inGame = true;

    public ClientHandlerSocket(Socket socket) throws IOException {

        this.connectedClient = socket;

        this.input = new ObjectInputStream(connectedClient.getInputStream());
        this.output = new ObjectOutputStream(connectedClient.getOutputStream());

    }

    @Override
    public void run() {
        Event event;

        while (inGame) {
            try {
                event = (Event) input.readObject();
                //gameController.eventReceived();
            } catch (IOException | ClassNotFoundException exc) {
                event = new ErrorEvent(null, "Unable read from client input");
            }
        }
        try {
            connectedClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(Event event){
        if (inGame) {
            try {
                output.writeObject(event);
                output.flush();
            } catch (IOException error) {
                event = new ErrorEvent(null, "Unable to send message to the client");
            }
        }
    }

    public void finishGame(){
        this.inGame = false;
    }
}
