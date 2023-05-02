package it.polimi.ingsw.Observer;

import it.polimi.ingsw.network.Messages.Message;

public interface Observer {
    void update(Message message);

}
