package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.lang.*;


public class ServerSckt {

    private ServerSocket server;

    public static final int PORT = 5050;
    private static final int SOCKET_TIMEOUT = 1000;

    public ServerSckt() throws IOException {
        try {
            this.server = new ServerSocket(PORT, SOCKET_TIMEOUT);
        } catch (IOException error) {
            System.err.println("Can't start the server");
            throw error;
        }

        while (true) {
            Socket newClient = server.accept();
            ClientHandlerSocket clientHandler = new ClientHandlerSocket(this)
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }



}

