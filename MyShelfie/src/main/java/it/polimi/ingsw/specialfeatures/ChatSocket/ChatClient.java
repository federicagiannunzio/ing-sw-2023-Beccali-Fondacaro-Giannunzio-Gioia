package it.polimi.ingsw.specialfeatures.ChatSocket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatClient {

    private Socket socket;
    private BufferedReader inputBuffer;
    private BufferedWriter outputBuffer;
    private String username;

    public ChatClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputBuffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, outputBuffer, inputBuffer);
        }
    }

    /**
     * sends a message to the clientHandler
     */
    public void messageSender() {
        try {
            outputBuffer.write(username);
            outputBuffer.newLine();
            outputBuffer.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                outputBuffer.write("["+ username +"]: " + messageToSend);
                outputBuffer.newLine();
                outputBuffer.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, outputBuffer, inputBuffer);
        }
    }
    /**
     * Receive a message from the client handler.
     * It is executed on a separated thread
     */
    public void messageListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()) {
                    try {
                        String messageFromOthers = inputBuffer.readLine();
                        System.out.println(messageFromOthers);
                    } catch (IOException e ) {
                        closeEverything(socket, outputBuffer, inputBuffer);
                    }
                }
            }
        }).start();
    }

    /**
     * this method is used to close down the connection and the stream
     *
     * @param socket socket that will be closed
     * @param bufferedReader input stream that will be closed
     * @param bufferedWriter output stream that will be closed
     */
    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if(socket != null)
                socket.close();
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String username;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the group chat! Please set your username: ");
        username = scanner.nextLine();
        System.out.println("----------------------------------------------------------");
        Socket socket = new Socket("localhost", 1099);
        ChatClient client = new ChatClient(socket, username);
        client.messageListener();
        client.messageSender();

    }
}
