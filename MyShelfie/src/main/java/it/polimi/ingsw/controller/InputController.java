package main.java.it.polimi.ingsw.controller;

import it.polimi.ingsw.View.GameView;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.Messages.Message;


public class InputController {

    private final GameView view;
    private final GameController gameController;

    /**
     * constructor of the class
     * */
    public InputController(GameView view) {

        this.view = view;
    }

    /**
     * Verify data sent by the client to server
     *
     * @param message message sent by the client
     * @return {code @true} if it's a valid nickname {code @false} otherwise
     */
    //TO DO: aggiungere anche i messaggi "proibiti" da client a server
    public boolean verifyReceivedData(Event event) {

        switch(event.getEventType()) {
            case CHOOSE_TILE_REPLY:
                return pickTileCheck(event);
            case PLACE_TILE_REPLY:
                return insertedTileCheck(event);
        }

    }

    /**
     * Check if a username is valid or not
     *
     * @param username username chose by the player (the client)
     * @param view
     * @return {code @true} if it's a valid nickname {code @false} otherwise.
     */
    public boolean checkUsernameValidity(String username, GameView view) {

        if(username.isEmpty()) {
            view.showGenericMessage("This username is not valid");
            view.showLoginResult();
            return false;
        } else if (gameController.playersNicknameList.contains(username)) {
            view.showGenericMessage("This username is already taken");
            view.showLoginResult();
            return false;
        }
        return true;
    }

    /**
     *receives a message from the client (receives position in the board of the tile that he wants to pick) and check if the tile is available
     *
     * @param message message from the client
     * @return {code @true} id it's a valid position {code @false} otherwise
     * */
    public boolean pickTileCheck(Event event) {
        PositionEvent positionMessage = ((PositionEvent) event);
        //salvo la posizione
        boolean isAvailable = gameController.getBoard().checkTile(..)

        if (isAvailable) {
            return true;
        } else {
            view.showGenericMessage("This position is not valid");
        }
        return true;
    }

    /**
     * receives a message from the client (receives the position of the selfie in which he wants to put ) and check if the tile is available
     *
     * @param message message from the client
     * @return {code @true} id it's a valid position {code @false} otherwise
     */
    public boolean insertedTileCheck(Event event) {
        String username; //salvo qui username di quello che mi ha inviato il messaggio
        PositionEvent positionMessage = ((PositionEvent) event);
        //salvo la posizione
        Shelfie playerShelf = gameController.getPlayerByUsername(username).getShelfie();

        if(playerShelf.getCell() != null) {
            view.showGenericMessage("You have already filled this spot");
            return false;
        }
        return true;
    }

    /**
     * Check player number reply message.
     * The client send to the server the number of player that wants for this game.
     *
     * @param message message from the client
     */
    public boolean playerNumberReplyCheck(Message message) {
        PlayerNumberReply playerNumberReply = (PlayerNumberReply) message;

        if(playerNumberReply.getPlayerNumber() < 4 && playerNumberReply.getPlayerNumber() < 1) {
            return true;
        } else {
            view.showGenericMessage("Please select a valid number of players");
            view.askPlayersNumber();
        }

    }
    /**
     * check if the message is sent from the active player
     *
     * @param receivedMessage message from client
     * @return {code @true} id it's a valid position {code @false} otherwise
     */
    public boolean checkUser(Event event) {
        return event.getUser().equals(gameController.getGameController().getActivePlayer());
    }


}
