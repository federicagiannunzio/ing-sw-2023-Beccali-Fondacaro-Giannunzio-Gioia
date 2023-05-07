package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.network.Events.Event;

import java.util.Optional;


public class InputController {

    private final View view;
    private final GameController gameController;

    /**
     * constructor of the class
     * */
    public InputController(View view, GameController gameController) {

        this.view = view;
        this.gameController = gameController;
    }

    /**
     * Verify data sent by the client to server
     *
     * @param event message sent by the client
     * @return {code @true} if it's a valid nickname {code @false} otherwise
     */
    public boolean verifyReceivedData(Event event) {

        switch(event.getEventType()) {
            case CHOOSE_TILE_REPLY:
                return pickedTileCheck(event);
            case PLACE_TILE_REPLY:
                return insertedTileCheck(event);
        }

    }

    /**
     * Check if a username is valid or not
     *
     * @param username username chose by the player (the client)
     * @return {code @true} if it's a valid nickname {code @false} otherwise.
     */
    public boolean checkUsernameValidity(String username) {

        if(username.isEmpty()) {
            view.showGenericMessage("This username is not valid");
            //view.showLoginResult();
            return false;
        } else if (gameController.getPlayers().contains(username)) {
            view.showGenericMessage("This username is already taken");
            //view.showLoginResult();
            return false;
        }
        return true;
    }

    /**
     *receives a message from the client (receives position in the board of the tile that he wants to pick) and check if the tile is available
     *
     * @param event message from the client
     * @return {code @true} id it's a valid position {code @false} otherwise
     * */
    public boolean pickedTileCheck(Event event) {
        ChooseTileRequest pickedTileMessage = ((ChooseTileRequest) event);
        int row = pickedTileMessage.getRow();
        int column = pickedTileMessage.getColumn();
        boolean isAvailable = gameController.getBoard().checkTile(row, column);

        if (isAvailable) {
            return true;
        } else {
            view.showGenericMessage("This position is not valid");
        }
        return true;
    }

    /**
     * receives a message from the client (receives the position of the selfie in which he wants to put ) and check if the position is empty.
     *
     * @param event message from the client
     * @return {code @true} id it's a valid position {code @false} otherwise
     */
    public boolean insertedTileCheck(Event event) {
        String activePlayerusername = gameController.getTurnController().getActivePlayer();
        PlaceTileRequest tileToPlaceMessage= ((PlaceTileRequest) event);
        int row = tileToPlaceMessage.getRow();
        int column = tileToPlaceMessage.getColumn();
        Optional<Player> activePlayer = gameController.getPlayerByUsername(activePlayerusername);
        if(activePlayer.isEmpty()) {
            System.out.println("This user doesn't exist");
            return false;
        }

        Shelfie playerShelf = activePlayer.get().getMyShelfie();

        if(playerShelf.getCell(row, column) != null) {
            view.showGenericMessage("You have already filled this spot");
            return false;
        }
        return true;
    }

    /**
     * Check player number reply message.
     * The client send to the server the number of player that wants for this game.
     *
     * @param event message from the client
     */
    public boolean playerNumberReplyCheck(Event event) {
        PlayerNumberReply playerNumberMessage = (PlayerNumberReply) event;

        if(playerNumberMessage.getPlayerNumber() > 4 && playerNumberMessage.getPlayerNumber() < 1) {
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
        return event.getUser().equals(gameController.getTurnController().getActivePlayer());
    }


}
