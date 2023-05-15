package it.polimi.ingsw;

import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.network.Server.ClientHandler;
import it.polimi.ingsw.network.Server.ClientHandlerSocket;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class ClientApp{
    public static void main( String[] args ) {
        Cli cli = new Cli();
        ClientController clientController = new ClientController(cli);
        cli.addObserver(clientController);
        cli.start();
    }
}
