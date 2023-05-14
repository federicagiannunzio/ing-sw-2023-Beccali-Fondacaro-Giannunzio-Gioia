package it.polimi.ingsw.View;

import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface View {

    /**
     * Asks the user to choose a username
     */
    void askUsername();


    /**
     * Asks the first user connected how many players he wants to play with
     */
    void askPlayersNumber();

    /**
     * Asks the first user connected to choose the first player.
     */
    void askFirstPLayer(List<String> usersOnGame);

    /**
     * Asks the player how many tiles he wants to pick.
     */
    void askTilesNumber();

    /**
     * Asks the player the position (coordinates) of the tile that he has picked.
     *
     * @param numberOfTiles is the number of tiles that he has picked.
     */
    void askTilePositionOnBoard();

    /**
     * Asks the player in which column of his shelf wants to insert the tiles.
     */
    void askShelfieColumn();

    void askPlacingTile(List<Tile> tilesPicked);

    /**
     * Shows the board on the screen
     */
    void showBoard(Tile[][] structure);

    /**
     * Shows the shelfie of the player that asked for it.
     */
    void showMyShelfie(Tile[][] structure);

    /**
     * Shows the common goals
     */
    void showCommonGoals(CommonGoalsName name);

    /**
     * Shows the points that the player has achieved till now.
     */
    void showMyPoints(int currentPoints);

    /**
     * Shows a message that announce that someone has completed a common goal.
     */
    void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved);

    /**
     * Shows a win message.
     *
     * @param winner the username of the winner.
     */
    void showWinMessage(String winner);

    /**
     * Shows a generic message
     *
     * @param messageToSend the message to be shown.
     */
    void showGenericMessage(String messageToSend);

    /**
     *
     */
    void showErrorMessage(String errorMessage);

    /**
     * Notify all the players that a new user has just connected to the game.
     *
     * @param connectedUser the user that has just connected.
     */
    void showConnectionMessage(String connectedUser);

    /**
     * Notify all the players that a user has just disconnected to the game.
     *
     * @param disconnectedUser the user that has just disconnected.
     */
    void showDisconnectionMessage(String disconnectedUser);

    void showTurnState(String user);

    void showTilesArray(List<Tile> tilesPicked);

    void askChoiceBeginTurn();

    void showLobby(List<String> nicknameList, int numPlayers);

    void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username) throws IOException;
}
