package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board = null;
    private Bag bag = null;

    @Before
    public void setUp() {
        bag = new Bag();
        board = new Board(10, bag);
    }

    @After
    public void tearDown() {
        bag = null;
        board = null;
    }

    @Test
    public void getReaminingTiles_correctOutput() {

        assertEquals(0, board.getRemainingTiles());
    }

    //da rivedere
    @Test
    public void getTile() {
        board.setTile(0,0, Color.BLUE);
        assertEquals(Color.BLUE, board.getTile(0,0).getColor());

    }

    @Test
    public void refreshBoard_fullBoard_returnNull() {

    }


}
