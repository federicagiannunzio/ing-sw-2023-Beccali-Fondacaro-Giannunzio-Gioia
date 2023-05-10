package it.polimi.ingsw;

import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.network.Server.ClientHandler;
import it.polimi.ingsw.network.Server.ClientHandlerSocket;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class ClientApp
{
    public static void main( String[] args ) {

        Socket socket = new Socket();
        socket.connect();
        Cli cli = new Cli();
        cli.start();

    }
}
