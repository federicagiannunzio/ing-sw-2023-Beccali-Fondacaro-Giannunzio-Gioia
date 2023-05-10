package it.polimi.ingsw.network.Events;

public class TurnStateEvent extends Event{
    public TurnStateEvent(String user) {
        super(user, EventType.TURN_STATE);
    }


}
