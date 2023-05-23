package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.Server.Server;
import it.polimi.ingsw.network.Server.ServerSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerApp {

    public static void main(String[] args){
        try {
            InetAddress i = InetAddress.getLocalHost();
            System.out.println(i);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = new GameController();
        Server server = new Server(gameController);
        ServerSocket socket = new ServerSocket(server);
        Thread  thread = new Thread(socket);
        thread.start();
    }
}
