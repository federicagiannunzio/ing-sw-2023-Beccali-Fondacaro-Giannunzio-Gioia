package it.polimi.ingsw.Observer;

import it.polimi.ingsw.model.Tile;

import java.util.ArrayList;
import java.util.List;

public interface ViewObserver {

    /**
     * Sends a message to the server with the username of that client.
     *
     * @String username the username of the player
     * */
    void sendUsername(String username);

    /**
     * Sends the chosen number of players for the game
     *
     * @param playersNum xthe number of player chosen
     * */
    void sendPlayersNumber(int playersNum);

    void sendTilesNumberChosen(int numberOfTiles);

    void sendTilePosition(int[] row, int[] col);

    void sendShelfieColumn(int column);

    void sendChoiceBeginTurn(int choice);

    void sendServerInfo(String address, int port);

    void sendArrayTileIndex(int index);

    void sendChatMessage(String recipient, List<String> messages);


}
