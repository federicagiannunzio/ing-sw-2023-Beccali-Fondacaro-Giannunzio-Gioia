package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket  extends Observable{
    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private boolean connection = false;
    private Scanner scanner = new Scanner(System.in);

    public static final int PORT = 5050;
    private static final int SOCKET_TIMEOUT = 1000;

    public ClientSocket(String ip) throws IOException{
        this.socket = new Socket();
        try {
            this.socket.connect(new InetSocketAddress(ip, PORT), SOCKET_TIMEOUT);
            connection = true;
        } catch(IOException exc){
            System.err.println("The client is unable to reach the server");
            throw exc;
        }
        this.input = new ObjectInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(socket.getOutputStream());

    }

    public void receiveMessage() throws IOException {
        Event event;
        if(connection) {
            try {
            event = (Event) input.readObject();
            notifyObserver(event);
            }catch(IOException | ClassNotFoundException exc){
                event = new ErrorEvent(null, "Unable to reach the server");
            }
        }
    }

    public void sendMessage(Event event) throws IOException {
        if (connection) {
            try {
                output.writeObject(event);
                output.flush();
            } catch (IOException exc) {
                notifyObserver(new ErrorEvent(null, "Unable to send message"));
            }
        }
    }

    public void closeConnection() throws IOException {
        if (connection) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException exc) {
                System.err.println("Client unable to close the socket");
                throw exc;
            }
        } else {
            System.err.println("There is no connection currently active");
        }
    }
}
