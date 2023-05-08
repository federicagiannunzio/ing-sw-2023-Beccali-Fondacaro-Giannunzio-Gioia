package it.polimi.ingsw.network.Events;

public class CommonGoalCompletedEvent extends Event{
    public CommonGoalCompletedEvent(String user, EventType eventType) {
        super(user, EventType.COMMONGOAL_COMPLETED);
    }
}
