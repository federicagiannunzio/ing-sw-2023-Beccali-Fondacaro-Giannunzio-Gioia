package main.java.it.polimi.ingsw.network;

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

    public ServerSckt() throws IOException{
        try{
            this.server = new ServerSocket(PORT, SOCKET_TIMEOUT);
        }catch (IOException error){
            System.err.println("Can't start the server");
            throw error;
        }

        while(true){
            Socket newClient = server.accept();
            ClientHandler handler = new ClientHandler(newClient);
            new Thread(handler).start();
        }


    }

    private static class ClientHandler implements Runnable {

        private final Socket connectedClient;
        private final DataOutputStream output;
        private final DataInputStream input;
        private final Scanner scanner = new Scanner(System.in);

        private boolean inGame = true;

        public ClientHandler(Socket socket) throws IOException{

            this.connectedClient = socket;

            this.input = new DataInputStream(connectedClient.getInputStream());
            this.output = new DataOutputStream(connectedClient.getOutputStream());

        }

        @Override
        public void run() {

            while (inGame){

            }

            try {
                connectedClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        public void receiveMessage() throws IOException {
            if(inGame) {
                try {
                    String received = input.readUTF();
                    System.out.println(received);
                }catch(IOException error){
                    System.err.println(error);
                    throw error;
                }
            }
        }

        public void sendMessage() throws IOException {
            if (inGame) {
                try {
                    String toSend = scanner.nextLine();
                    output.writeUTF(toSend);
                } catch (IOException error) {
                    System.err.println(error);
                    throw error;
                }
            }
        }

        public void finishGame(){
            this.inGame = true;
        }
    }

}

