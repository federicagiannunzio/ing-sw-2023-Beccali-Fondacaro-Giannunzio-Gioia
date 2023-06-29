package it.polimi.ingsw.Observer;

import it.polimi.ingsw.model.Tile;

import java.util.ArrayList;
import java.util.List;

public interface ViewObserver {

    void sendUsername(String username);

    void sendPlayersNumber(int playersNum);

    void sendFirstPlayerChosen(String usernameFirst);

    void sendTilesNumberChosen(int numberOfTiles);

    void sendTilePosition(int[] row, int[] col);

    void sendShelfieColumn(int column);

    void sendChoiceBeginTurn(int choice);

    void sendServerInfo(String address, int port);

    void sendArrayTileIndex(int index);

    void sendChatMessage(String recipient, List<String> messages);


}
