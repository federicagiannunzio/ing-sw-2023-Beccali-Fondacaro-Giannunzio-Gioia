package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.View;
import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.network.Events.*;

import java.io.Serializable;
import java.util.Optional;


public class InputController implements Serializable {

    private final GameController gameController;


    /**
     * constructor of the class
     * */
    public InputController(GameController gameController) {

        this.gameController = gameController;
    }

    /**
     * Verify data sent by the client to server
     *
     * @param event message sent by the client
     * @param view view for the active client
     * @return {code @true} if it's a valid nickname {code @false} otherwise
     */
    public boolean verifyReceivedData(Event event, VirtualView view) {

        switch(event.getEventType()) {
            case LOGIN_REPLY:
                LoginReplyEvent loginReplyEvent = (LoginReplyEvent) event;
                return checkUsernameValidity(loginReplyEvent.getUser(), view);
            case CHOOSE_TILE_REPLY:
                return pickedTileCheck(event, view);
            case PLACE_TILE_REPLY:
                return insertedTileCheck(event, view);
            default:
                return false;
        }
    }

    /**
     * Check if a username is valid or not
     *
     * @param username username chose by the player (the client)
     * @return {code @true} if it's a valid nickname {code @false} otherwise.
     */
    public boolean checkUsernameValidity(String username, VirtualView view) {

        if(username.isEmpty()) {
            view.showGenericMessage("This username is not valid");
            return false;
        } else if (gameController.getNicknames().contains(username)) {
            view.showGenericMessage("This username is already taken");
            return false;
        }
        return true;
    }

    /**
     *receives a message from the client (receives position in the board of the tile that he wants to pick) and check if the tile is available
     *
     * @param event message from the client
     * @param view view for the active client
     * @return {code @true} id it's a valid position {code @false} otherwise
     * */
    public boolean pickedTileCheck(Event event, VirtualView view) {
        ChooseTileReplyEvent pickedTileMessage = ((ChooseTileReplyEvent) event);
        int row = pickedTileMessage.getrow();
        int column = pickedTileMessage.getcolumn();
        boolean isAvailable = gameController.getBoard().checkTile(row, column);

        if (isAvailable) {
            return true;
        } else {
            view.showGenericMessage("This position is not valid");
            view.askTilePositionOnBoard();
            return false;
        }
    }

    //da sistemare e rifare
    /**
     * receives a message from the client (receives the position of the shelf in which he wants to put ) and check if the position is empty.
     *
     * @param event message from the client
     * @param view view for the active client
     * @return {code @true} id it's a valid position {code @false} otherwise
     *
     */
    public boolean insertedTileCheck(Event event, VirtualView view) {
        String activePlayerusername = gameController.getActivePlayer();
        PlaceTileReplyEvent tileToPlace= ((PlaceTileReplyEvent) event);
        int column = tileToPlace.getColumn();
        Optional<Player> activePlayer = gameController.getPlayerByUsername(activePlayerusername);
        if(activePlayer.isEmpty()) {
            System.out.println("This user doesn't exist");
            return false;
        }

        Shelfie playerShelf = activePlayer.get().getMyShelfie();

        if(playerShelf.getCell(0, column) != null) {
            view.showGenericMessage("You have already filled this spot");
            view.askShelfieColumn();
            return false;
        }
        return true;
    }

    /**
     * Check player number reply message.
     * The client send to the server the number of player that wants for this game.
     *
     * @param event message from the client
     *
     */
    public boolean playerNumberReplyCheck(Event event, VirtualView view) {
        NumberOfUsersReplyEvent playerNumberMessage = (NumberOfUsersReplyEvent) event;

        if(playerNumberMessage.getNumPlayers() > 4 && playerNumberMessage.getNumPlayers() < 1) {
            return true;
        } else {
            view.showGenericMessage("Please select a valid number of players");
            view.askPlayersNumber();
            return false;
        }
    }

    /**
     * check if the message is sent from the active player
     *
     * @param event message from client
     * @return {code @true} id it's a valid position {code @false} otherwise
     */
    public boolean checkUser(Event event) {
        return event.getUser().equals(gameController.getActivePlayer());
    }
}
