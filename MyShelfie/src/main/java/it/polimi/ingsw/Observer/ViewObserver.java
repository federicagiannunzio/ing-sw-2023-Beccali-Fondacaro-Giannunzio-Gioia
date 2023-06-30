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
     * Sends a message to the server with the chosen number of players for the game
     *
     * @param playersNum the number of player chosen
     * */
    void sendPlayersNumber(int playersNum);

    /**
     * Sends a message to the server with the chosen number of tiles picked by a player
     *
     * @param numberOfTiles the number of player chosen
     * */
    void sendTilesNumberChosen(int numberOfTiles);

    /**
     * Sends a message to the server with the positions on board of the tiles picked
     *
     * @param row the row's numbers of the position of the tiles picked
     * @param col the column's numbers of the position of the tiles picked
     * */
    void sendTilePosition(int[] row, int[] col);

    /**
     * Sends a message to the server with the number of the column o the shelf in which
     * the player wants to place his tiles
     * @param column the number of the column
     * */
    void sendShelfieColumn(int column);

    /**
     * Sends a message to the server with the number that corresponds to s choice
     * To every choice correspond an action in the game
     *
     * @oaram choice the number of the choice
     * */
    void sendChoiceBeginTurn(int choice);

    /**
     * Create a new connection to the server with the updated info
     *
     * @param address server IP address
     * @param port server port
     */
    void sendServerInfo(String address, int port);

    /**
     * Send the server the index of the cell of the array that contains the tile
     * that the player will place
     *
     * @param index the index of the array
     * */
    void sendArrayTileIndex(int index);


    /**
     * Send the server the messages that a player wants to send to an other
     *
     * @param recipient the recipient of the messages
     * @param messages the messages that will be forwarded
     * */
    void sendChatMessage(String recipient, List<String> messages);


}
