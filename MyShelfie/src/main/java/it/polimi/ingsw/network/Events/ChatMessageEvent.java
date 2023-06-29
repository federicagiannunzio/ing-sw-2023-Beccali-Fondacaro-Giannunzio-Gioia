package it.polimi.ingsw.network.Events;

import java.util.List;

/**Event to send the server the messages that a player wants to send to all the others*/
public class ChatMessageEvent extends Event{
    private boolean priv;
    private String recipient;

    private List<String> messages;

    public ChatMessageEvent(String user, String recipient, List<String> messages) {
        super(user, EventType.CHATMESSAGE);
        this.priv = priv;
        this.recipient = recipient;
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getRecipient() {
        return recipient;
    }
}
