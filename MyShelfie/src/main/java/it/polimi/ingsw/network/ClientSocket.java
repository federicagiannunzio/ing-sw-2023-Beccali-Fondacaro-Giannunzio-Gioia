package it.polimi.ingsw.network;

import java.net.Socket;

public class ClientSocket {
    private final Socket socket;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }
}
