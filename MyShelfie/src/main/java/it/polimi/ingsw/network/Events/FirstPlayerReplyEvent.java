package it.polimi.ingsw.network.Events;

public class FirstPlayerReplyEvent extends Event{
    private final String firstPlayer;
    public FirstPlayerReplyEvent(String user, String firstPlayer) {
        super(user, EventType.FIRSTPLAYER_REPLY);
        this.firstPlayer = firstPlayer;
    }

    public String getFirstPlayer(){
        return firstPlayer;
    }
}