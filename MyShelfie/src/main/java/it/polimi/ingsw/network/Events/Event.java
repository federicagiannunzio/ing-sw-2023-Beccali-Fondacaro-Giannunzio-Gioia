package it.polimi.ingsw.network.Events;

public abstract class Event {
    private final String user;
    private final EventType eventType;

    public Event(String user, EventType eventType){
        this.user = user;
        this.eventType = eventType;
    }

    public String getUser(){
        return this.user;
    }

    public EventType getEventType(){
        return this.eventType;
    }

    public String toString(){
        return "Event for user " + user + " of the type " + eventType;
    }

}
