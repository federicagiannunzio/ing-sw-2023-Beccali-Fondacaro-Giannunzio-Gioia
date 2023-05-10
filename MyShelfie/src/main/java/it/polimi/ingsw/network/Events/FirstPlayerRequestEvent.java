package it.polimi.ingsw.network.Events;

import java.util.List;

public class FirstPlayerRequestEvent extends Event{
    private final List<String> usersOnGame;
    public FirstPlayerRequestEvent(String user, List<String> usersOnGame) {
        super(user, EventType.FIRSTPLAYER_REQUEST);
        this.usersOnGame = usersOnGame;
    }

    public List<String> getUsersOnGame(){
        return usersOnGame;
    }
}
