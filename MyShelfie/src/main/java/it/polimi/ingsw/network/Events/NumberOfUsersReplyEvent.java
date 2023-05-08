package it.polimi.ingsw.network.Events;


public class NumberOfUsersReplyEvent extends Event{
    public NumberOfUsersReplyEvent(String user, EventType eventType) {
        super(user, EventType.NUMBER_OF_USERS_REPLY);
    }
}
