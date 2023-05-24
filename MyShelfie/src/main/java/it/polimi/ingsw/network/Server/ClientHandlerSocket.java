package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Events.EventType;
import it.polimi.ingsw.network.Events.LoginRequestEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ClientHandlerSocket implements ClientHandler, Runnable {

    private final Socket connectedClient;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final Object inputLock;
    private final Object outputLock;
    private final ServerSocket serverSocket;
    private final Scanner scanner = new Scanner(System.in);

    private boolean inGame = true;

    public ClientHandlerSocket(ServerSocket serverSocket, Socket socket) throws IOException {

        this.connectedClient = socket;
        this.serverSocket = serverSocket;
        this.inputLock = new Object();
        this.outputLock = new Object();
        this.output = new ObjectOutputStream(connectedClient.getOutputStream());
        this.input = new ObjectInputStream(connectedClient.getInputStream());
    }

    @Override
    public void run() {
        Event event;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (inputLock) {
                        event = (Event) input.readObject();
                        if (event.getEventType() == EventType.LOGIN_REQUEST) {
                            serverSocket.addClient(event.getUser(), this);
                        }
                        serverSocket.update(event);
                    }
                }
                connectedClient.close();
            } catch (IOException | ClassNotFoundException exc) {
                event = new ErrorEvent(null, "Unable read from client input");
            }
        disconnect();
    }
    public void sendMessage(Event event){
        if (inGame) {
            try {
                synchronized (outputLock){
                    output.writeObject(event);
                    output.flush();
                    output.reset();
                }
            } catch (IOException error) {
                event = new ErrorEvent(null, "Unable to send message to the client");
                disconnect();
            }
        }
    }

    @Override
    public void disconnect(){

        try {
            connectedClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inGame = false;
        Thread.currentThread().interrupt();
        serverSocket.disconnectClient(this);
    }


    public void finishGame(){
        this.inGame = false;
    }
}
