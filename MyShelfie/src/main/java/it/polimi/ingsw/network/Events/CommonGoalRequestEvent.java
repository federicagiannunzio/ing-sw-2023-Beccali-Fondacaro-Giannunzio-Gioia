package it.polimi.ingsw.network.Events;

public class CommonGoalRequestEvent extends Event{
    public CommonGoalRequestEvent(String user, EventType eventType) {
        super(user, EventType.COMMONGOAL_REQUEST);
    }
}
