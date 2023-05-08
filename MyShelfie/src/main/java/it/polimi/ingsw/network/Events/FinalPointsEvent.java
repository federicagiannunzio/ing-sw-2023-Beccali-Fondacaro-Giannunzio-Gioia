package it.polimi.ingsw.network.Events;

public class FinalPointsEvent extends Event{
    public FinalPointsEvent(String user, EventType eventType) {
        super(user, EventType.FINAL_POINTS);
    }
}
