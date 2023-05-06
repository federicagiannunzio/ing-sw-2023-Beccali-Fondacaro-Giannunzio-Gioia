package it.polimi.ingsw.View;

public interface View {

    /**
     * Asks the user to choose a username
     * */
    void askUsername();

    /**
     * Asks the first user connected how many players he wants to play with
     */
    void askPlayersNumber();

    /**
     * Asks the first user connected to choose the first player.
     */
    void askFirstPLayer();

    /**
     * Asks the player how many tiles he wants to pick.
     */
    void askTilesNumber();

    /**
     * Asks the player the position (coordinates) of the tile that he has picked.
     *
     * @param numberOfTiles is the number of tiles that he has picked.
     */
    void askTilePositionOnBoard(int numberOfTiles);

    /**
     * Asks the player in which column of his shelf wants to insert the tiles.
     */
    void askShelfieColumn();

    /**
     * Shows the board on the screen
     */
    void showBoard();

    /**
     * Shows the shelfie of the player that asked for it.
     */
    void showMyShelfie();

    /**
     * Shows the shelf of all the other players.
     */
    void showOtherPlayersShelfie();

    /**
     * Shows this player's personal goal
     */
    void showMyPersonalGoal();

    /**
     * Shows the common goals
     */
    void showCommonGoals();

    /**
     * Shows the points that the player has achieved till now.
     */
    void showMyPoints();

    /**
     * Notify all the players when someone has completed a common goal.
     */
    void showCommonGoalCompletion();

    /**
     * Shows a win message.
     *
     * @param winner the username of the winner.
     */
    void showWinMessage(String winner);

    /**
     *
     */
    void showLoginResult();

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
}
