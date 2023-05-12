package it.polimi.ingsw.network.Events;

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
