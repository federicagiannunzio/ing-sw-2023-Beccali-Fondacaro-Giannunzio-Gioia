package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board = null;
    private Bag bag = null;

    @Before
    public void setUp() throws IOException {
        bag = new Bag();
        board = new Board(bag);
    }

    @After
    public void tearDown() {
        bag = null;
        board = null;
    }

    @Test
    public void getBoard_emptyBoard_correctOutput() {
        Tile [][] b = new Tile[9][9];
       assertArrayEquals(b, board.getBoard());

    }

    @Test
    public void remainingTilesGetterAndSetter_noTilesRemoved_correctOutput() {
        board.inizializeRemainingTiles(3);
        assertEquals(37, board.getRemainingTiles());
    }

    @Test
    public void getRemainingTiles_someTilesRemoved_correctOutput() throws IOException, notAvailableTileException {
        bag.setBag();
        board.fillTheBoard(1);
        board.inizializeRemainingTiles(3);
        Tile t = board.takeTileFromBoard(2,2);
        assertEquals(36, board.getRemainingTiles());
    }

    @Test
    public void fillTheBoard_fullBoard_correctNumberOfTiles() throws IOException {
        bag.setBag();
        board.inizializeRemainingTiles(3);
        board.fillTheBoard(1);
        Tile [][] m = board.getBoard();
        int counter = 0;

        for(int i=0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(m[i][j] != null && !m[i][j].getColor().equals(Color.BLACK))
                    counter++;
            }
        }

        assertEquals(board.getRemainingTiles(), counter);
    }

    @Test
    public void checkTiles_legalTileChose_correctOutput() throws IOException {
        bag.setBag();
        board.fillTheBoard(1);
        board.inizializeRemainingTiles(3);
        assertTrue(board.checkTile(2,2));
    }

    @Test
    public void checkTiles_IllegalTileChose_correctOutput() throws IOException {
        bag.setBag();
        board.fillTheBoard(1);
        board.inizializeRemainingTiles(3);
        assertFalse(board.checkTile(4,4));
    }

    @Test
    public void takeTileFromBoard_legalTileChose_correctOutput() throws IOException, notAvailableTileException {
        bag.setBag();
        board.fillTheBoard(1);
        board.inizializeRemainingTiles(3);

        assertEquals(board.getTile(2,2), board.takeTileFromBoard(2,2));
    }

    @Test (expected = notAvailableTileException.class)
    public void takeTileFromBoard_IllegalTileChose_correctOutput() throws IOException, notAvailableTileException {
        bag.setBag();
        board.fillTheBoard(1);
        board.inizializeRemainingTiles(3);
        board.takeTileFromBoard(4,4);

    }

    @Test
    public void getTile_oneTileRemoved_correctTile() {
        board.setTile(0,0, Color.BLUE);
        assertEquals(Color.BLUE, board.getTile(0,0).getColor());
    }

    @Test
    public void refreshBoard_fullBoard_noRefresh() throws IOException {
        bag.setBag();
        board.fillTheBoard(1);
        Tile [][] expected = board.getBoard();
        board.refreshBoard();
        assertArrayEquals(expected, board.getBoard());
    }

    @Test
    public void tileSetterAndGetter_correctInput_correctOutput() {
        board.setTile(1, 2, Color.BLUE);
        assertEquals(Color.BLUE, board.getTile(1,2).getColor());
    }

    @Test
    public void tileSetterAndGetter_oneTileSetted_allTheOthersNotModified() {
        board.setTile(1, 2, Color.YELLOW);
        for(int i=0; i < 9; i++) {
            for(int j=0; j < 9 ; j++) {
                if(i!=1 && j!=2)
                    assertNull(board.getTile(i, j));
            }
        }
    }

}
