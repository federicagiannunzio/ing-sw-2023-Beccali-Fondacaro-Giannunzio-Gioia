package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.controller.Observer.Observable;
import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ClientSocket extends Client{
    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private boolean connection = false;
    private final ExecutorService readQueue;

    public static final int PORT = 5050;
    private static final int SOCKET_TIMEOUT = 10000;

    public ClientSocket(String ip, int port) throws IOException{
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(ip, port), SOCKET_TIMEOUT);
        connection = true;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
        this.readQueue = Executors.newSingleThreadExecutor();
    }

    public void receiveMessage() {
        readQueue.execute(() -> {
            while(connection) {
                Event event;
                try {
                    event = (Event) input.readObject();
                } catch (IOException | ClassNotFoundException exc) {
                    event = new ErrorEvent(null, "Unable to reach the server");
                }
                notifyObserver(event);
            }
        });
    }

    @Override
    public void sendMessage(Event event) {
        if (connection) {
            try {
                output.writeObject(event);
                output.flush();
                output.reset();
            } catch (IOException exc) {
                notifyObserver(new ErrorEvent(null, "Unable to send message"));
                closeConnection();
            }
        }
    }

    @Override
    public void closeConnection() {
        if (connection) {
            try {
                readQueue.shutdownNow();
                socket.close();
                System.exit(0);
            } catch (IOException exc) {
                System.err.println("Client unable to close the socket");
            }
        } else {
            System.err.println("There is no connection currently active");
        }
    }
}
