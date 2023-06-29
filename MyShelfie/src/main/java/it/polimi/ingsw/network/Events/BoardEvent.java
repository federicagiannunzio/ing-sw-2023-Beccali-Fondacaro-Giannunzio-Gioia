package it.polimi.ingsw.network.Events;


import it.polimi.ingsw.model.Tile;

import java.util.Arrays;

/**
 * Message that contains the board. Used to send the updated board to the user.
 */
public class BoardEvent extends Event{
    private final Tile[][] board;
    public BoardEvent(String user, Tile[][] board) {
        super(user,EventType.BOARD);
        this.board = board;
    }
    public Tile[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "BoardEvent{" +
                "board=" + Arrays.deepToString(board) +
                '}';
    }
}
