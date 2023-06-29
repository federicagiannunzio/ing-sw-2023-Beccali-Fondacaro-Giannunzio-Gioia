package it.polimi.ingsw.network.Events;

/**
 * Message to notify a disconnection to the other players.
 */
public class DisconnectionMessageEvent extends Event {
    private final String nicknameDisconnected;

    public DisconnectionMessageEvent(String user, String discUSer) {
        super(user, EventType.USERDISCONNECTED);
        this.nicknameDisconnected = discUSer;
    }

    public String getNicknameDisconnected() {
        return nicknameDisconnected;
    }
}
