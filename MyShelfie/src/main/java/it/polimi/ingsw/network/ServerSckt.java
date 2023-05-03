package it.polimi.ingsw.network;

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
            ClientHandler handler = new ClientHandler(newClient);
            new Thread(handler).start();
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket connectedClient;
        private final ObjectOutputStream output;
        private final ObjectInputStream input;
        private final Scanner scanner = new Scanner(System.in);

        private boolean inGame = true;

        public ClientHandler(Socket socket) throws IOException{

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
                    gameController.eventReceived();
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
        public void sendMessage(Event event) throws IOException {
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

}

