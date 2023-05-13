package it.polimi.ingsw.network.Events;

import java.util.List;

public class LobbyEvent extends Event {
    private final List<String> usersList;
    private final int maxPlayers;

    public LobbyEvent(String user, List<String> usersList, int maxPlayers) {
        super(user, EventType.LOBBY);
        this.usersList = usersList;
        this.maxPlayers = maxPlayers;
    }

    public List<String> getUsersList() {
        return usersList;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String toString() {
        return "LobbyMessage{" +
                "nickname=" + getUser() +
                ", nicknameList=" + usersList +
                ", numPlayers=" + maxPlayers +
                '}';
    }
}
