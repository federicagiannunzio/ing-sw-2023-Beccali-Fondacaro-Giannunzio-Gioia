package it.polimi.ingsw.View;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a generic view to be implemented by each view type
 */
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
     * Asks the player how many tiles he wants to pick.
     */
    void askTilesNumber();

    /**
     * Asks the player the position (coordinates) of the tile that he has picked.
     */
    void askTilePositionOnBoard(int tilesNumber);

    /**
     * Asks the player in which column of his shelf wants to insert the tiles.
     */
    void askShelfieColumn();

    /**
     * Asks the player which tile he wants to insert first. It prints the list of the tiles picked and the player has to choose the corresponding number.
     * */
    void askPlacingTile(List<Tile> tilesPicked);

    /**
     * Shows the board on the screen
     */
    void showBoard(Tile[][] structure);

    /**
     * Shows the shelf of the player that asked for it.
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
     * Show error message
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

    /**
     * Notify all the players who is starting the turn.
     * */
    void showTurnState(String user);

    /**
     * Prints a list containing the tiles that the player has picked.
     * */
    void showTilesArray(List<Tile> tilesPicked);

    /**
     * Asks the player to select a number. To every number corresponds a different action in the game.
     **/
    void askChoiceBeginTurn();

    /**
     *
     * */
    void showLobby(List<String> nicknameList, int numPlayers);


    /**
     * Shows to the user if the Login succeeded
     *
     * @param usernameAccepted     indicates if the chosen nickname has been accepted
     * @param connectionSuccessful indicates if the connection has been successful
     * @param username             the nickname of the player to be greeted
     */
    void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username);

    /**
     * Asks the player with who wants to chat and to type the messages that he wants to send
     * */
    void privateChat(List<String> playersName, ArrayList<ArrayList<String>> bufferMessages, String sender);

    /**
     * Asks the player to type the messages that he wants to send to the other players
     * */
    void groupChat(List<String> bufferMessages);
}
