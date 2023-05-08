package it.polimi.ingsw.network.Events;

public class OtherShelfiesEvent extends Event{
    public OtherShelfiesEvent(String user, EventType eventType) {
        super(user, EventType.OTHERSHELFIES);
    }
}
