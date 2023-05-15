package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.Event;

import java.io.*;
import java.net.Socket;
import java.lang.*;


public class ServerSocket implements Runnable{

    private java.net.ServerSocket serverSocket;
    private final Server server;

    public static final int PORT = 5050;
    private static final int SOCKET_TIMEOUT = 1000;

    public ServerSocket(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        try {
            this.serverSocket = new java.net.ServerSocket(PORT, SOCKET_TIMEOUT);
        } catch (IOException error) {
            System.err.println("Can't start the server");
            return;
        }

        while (true) {
            Socket newClient = null;
            try {
                newClient = serverSocket.accept();
                ClientHandlerSocket clientHandler = new ClientHandlerSocket(this, newClient);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(Event event){
        server.update(event);
    }

    public void addClient(String user, ClientHandler clientHandler){
        server.addClient(user, clientHandler);
    }



}

