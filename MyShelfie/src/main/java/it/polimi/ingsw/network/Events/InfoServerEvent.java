package it.polimi.ingsw.network.Events;

public class InfoServerEvent extends Event{

    private final int port;
    private final String address;
    public InfoServerEvent(String user, String address, int port) {
        super(user, EventType.SERVER_INFO);
        this.address = address;
        this.port = port;
    }

    public String getAddress(){return this.address;}
    public int getPort(){return this.port;}

    @Override
    public String toString(){
        return "Player chose server " + address + " with the port " + port;
    }
}
