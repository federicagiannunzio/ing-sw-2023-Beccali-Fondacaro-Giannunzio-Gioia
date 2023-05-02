package it.polimi.ingsw.network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    private final Socket socket;
    private final DataOutputStream output;
    private final DataInputStream input;
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
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());

    }

    public void receiveMessage() throws IOException {
        if(connection) {
            try {
                String received = input.readUTF();
                System.out.println(received);
            }catch(IOException exc){
                System.err.println("Client unable to receive message from the server");
                throw exc;
            }
        }
    }

    public void sendMessage() throws IOException {
        if (connection) {
            try {
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
            } catch (IOException exc) {
                System.err.println("Client unable to receive message from the server");
                throw exc;
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
