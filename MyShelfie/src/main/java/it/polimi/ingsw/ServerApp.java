package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.Server.Server;
import it.polimi.ingsw.network.Server.ServerSocket;

import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) throws IOException {
        int serverPort = 5050;

        GameController gameController = new GameController();
        Server server = new Server(gameController);
        ServerSocket socket = new ServerSocket(server);
        Thread  thread = new Thread(socket);
        thread.start();
    }
}
