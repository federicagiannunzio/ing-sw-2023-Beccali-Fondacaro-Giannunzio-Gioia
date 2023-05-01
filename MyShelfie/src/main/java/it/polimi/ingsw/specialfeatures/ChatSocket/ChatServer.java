package it.polimi.ingsw.specialfeatures.ChatSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private ServerSocket serverSocket;

    /**
     * default constructor
     */
    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * This method keeps the server running. While the socket is open, the server wait for a client to connect.
     * After that a client has connected, the server generate a thread to handle it
     * Basically every client is handles on a different thread
     *
     * @throws IOException
     */
    public void startServer() throws IOException {
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new user joined the chat!");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread t = new Thread(clientHandler);
                t.start();
            }
        } catch (IOException e){
            System.out.println("SERVER ERROR");
            closeServer();
        }
    }

    /**
     * this method close the socket if the socket is not null
     * @throws IOException
     */
    public void closeServer() throws IOException {
        try {
            if(serverSocket!=null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1099);
        ChatServer server = new ChatServer(serverSocket);
        server.startServer();
    }
}
