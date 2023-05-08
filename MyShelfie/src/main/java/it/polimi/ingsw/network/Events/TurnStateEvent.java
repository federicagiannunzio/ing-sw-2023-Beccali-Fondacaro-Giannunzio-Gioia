package it.polimi.ingsw.network.Events;

public class TurnStateEvent extends Event{
    public TurnStateEvent(String user, EventType eventType) {
        super(user, EventType.TURN_STATE);
    }
}
