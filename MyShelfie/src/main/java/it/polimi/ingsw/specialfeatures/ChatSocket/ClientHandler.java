package it.polimi.ingsw.specialfeatures.ChatSocket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * this class is used to handles client's connection
 * 'clientHandlers' keep trace of every instance of this class, it allows o send message to multiple clients
 * 'socket' is the socket that establish the connection
 * 'inputBuffer' is used to read data
 * 'outputBuffer' is used to send data
 * 'username' is the client's username
 */
public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader inputBuffer;
    private BufferedWriter outputBuffer;
    private String username;

    /**
     *
     * @param socket is the socket associated to the connection from server to this client
     * @throws IOException
     * */
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputBuffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String us = inputBuffer.readLine();
            while(!checkUsername(us)) {
                this.outputBuffer.write("[SERVER]: This username is already taken. Please select another username.");
                this.outputBuffer.newLine();
                this.outputBuffer.flush();
                us = inputBuffer.readLine();
            }
            this.username = us;
            this.clientHandlers.add(this);
            broadcastMessage("[SERVER]: " + username + " has joined the chat!");
        } catch (IOException e) {
            closeEverything(socket, outputBuffer, inputBuffer);
        }
    }

    /**
     * everything in the run method will be run on a separate thread
     * while che connection is open, clientHandler receives a message from a specific client and send that to every other client
     */
    @Override
    public void run() {
        while(!socket.isClosed()) {
            String messageFromClient = null;
            try {
                messageFromClient = inputBuffer.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            broadcastMessage(messageFromClient);
        }
    }

    /**
     * this method sends the message that a client has sent to every other client
     *
     * @param messageToSend is the message that the client wants to send to the other clients
     */
    public void broadcastMessage(String messageToSend) {

        for(ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.username.equals(this.username)) {
                    clientHandler.outputBuffer.write(messageToSend);
                    clientHandler.outputBuffer.newLine();
                    clientHandler.outputBuffer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, outputBuffer, inputBuffer);
                break;
            }
        }
    }
    /**
     * this method removes the client from the arraylist if it leaves the chat
     * */
    public void clientHandlerRemover() {
        clientHandlers.remove(this);
        broadcastMessage("[SERVER]: " + this.username + " has left the chat :(");
    }

    /**
     * this method is used to close down the connection and the stream
     *
     * @param socket socket that will be closed
     * @param bufferedReader input stream that will be closed
     * @param bufferedWriter output stream that will be closed
     */
    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        clientHandlerRemover();
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

    /**
     * checks if someone has already picked that username
     *
     * @param usernameToCheck is the username picked by this client and that will be checked
     * @return true if the username is available, else false
     * */
    public boolean checkUsername(String usernameToCheck) {
        for(ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.username.equals(usernameToCheck))
                return false;
        }
        return true;
    }



}
