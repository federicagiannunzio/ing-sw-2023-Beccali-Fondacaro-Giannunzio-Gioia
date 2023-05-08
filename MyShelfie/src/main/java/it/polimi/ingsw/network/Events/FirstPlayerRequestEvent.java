package it.polimi.ingsw.network.Events;

public class FirstPlayerRequestEvent extends Event{
    public FirstPlayerRequestEvent(String user, EventType eventType) {
        super(user, EventType.FIRSTPLAYER_REQUEST);
    }
}
