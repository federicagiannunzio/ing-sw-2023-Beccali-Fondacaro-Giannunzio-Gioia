package it.polimi.ingsw.network.Server;

import java.io.*;
import java.net.Socket;
import java.lang.*;


public class ServerSocket {

    private java.net.ServerSocket server;

    public static final int PORT = 5050;
    private static final int SOCKET_TIMEOUT = 1000;

    public ServerSocket() throws IOException {
        try {
            this.server = new java.net.ServerSocket(PORT, SOCKET_TIMEOUT);
        } catch (IOException error) {
            System.err.println("Can't start the server");
            throw error;
        }

        while (true) {
            Socket newClient = server.accept();
            ClientHandlerSocket clientHandler = new ClientHandlerSocket(newClient);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }



}

