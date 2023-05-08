package it.polimi.ingsw.network.Events;

public class ShowPersonalGoalEvent extends Event{
    public ShowPersonalGoalEvent(String user, EventType eventType) {
        super(user, EventType.SHOWPERSONALGOAL);
    }
}
