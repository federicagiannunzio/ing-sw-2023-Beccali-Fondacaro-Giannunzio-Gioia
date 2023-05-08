package it.polimi.ingsw.network.Events;

public class InfoServerEvent extends Event{
    public InfoServerEvent(String user, EventType eventType) {
        super(user, EventType.SERVER_INFO);
    }
}
