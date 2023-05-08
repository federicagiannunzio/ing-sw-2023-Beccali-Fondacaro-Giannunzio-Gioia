package it.polimi.ingsw.network.Events;

public class StartTurnEvent extends Event{
    public StartTurnEvent(String user, EventType eventType) {
        super(user, EventType.TURN_START);
    }



    public String toString() {
        return ;
    }

}
