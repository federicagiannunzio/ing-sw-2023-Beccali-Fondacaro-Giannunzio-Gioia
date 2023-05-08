package it.polimi.ingsw.network.Events;

public class PersonalGoalRequestEvent extends Event{
    public PersonalGoalRequestEvent(String user, EventType eventType) {
        super(user, EventType.PERSONALGOAL_REQUEST);
    }
}
