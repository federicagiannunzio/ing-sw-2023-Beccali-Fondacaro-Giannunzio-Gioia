package it.polimi.ingsw.network.Events;

public class ShowCommonGoalsEvent extends Event{
    public ShowCommonGoalsEvent(String user, EventType eventType) {
        super(user, EventType.SHOWCOMMONGOALS);
    }
}
