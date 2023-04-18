package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ShelfieTest {
    private Shelfie shelf;

    @Before
    public void setUp() {
        shelf = new Shelfie();
    }

    @After
    public void tearDown() {
        shelf = null;
    }

    //missing a method in shelfie to count the points, therefore also the respective test



    @Test
    public void addTile_correctInput_correctOutput() throws Exception {
        Tile t = new Tile(Color.BLUE, 0);
        Tile[][] s = shelf.getStructure();
        shelf.addTile(t, 1);

        assertEquals(t, s[5][1]);
    }

    @Test
    public void getStructure_emptyShelf_correctOutput() {
        Tile [][] expected = new Tile[6][5];

        assertArrayEquals(expected, shelf.getStructure());
    }

    @Test
    public void getStructure_addedTilesSameColumn_correctOutput() throws fullColumnException {
        Tile[][] expected = new Tile[6][5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        //assertArrayEquals(expected, shelf.getStructure());
        expected[5][1] = t1;
        expected[4][1] = t2;
        shelf.addTile(t1, 1);
        shelf.addTile(t2, 1);

        assertArrayEquals(expected, shelf.getStructure());
    }

    @Test
    public void getStructure_addedTilesDifferentColumns_correctOutput() throws fullColumnException {
        Tile[][] expected = new Tile[6][5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        expected[5][1] = t1;
        expected[5][2] = t2;
        shelf.addTile(t1, 1);
        shelf.addTile(t2, 2);

        assertArrayEquals(expected, shelf.getStructure());
    }

    @Test (expected = fullColumnException.class)
    public void addTiles_alreadyFull_shouldThrowException() throws fullColumnException {
        Tile [][] m = shelf.getStructure();
        Tile t1 = new Tile(Color.YELLOW, 0);

        m[0][0] = new Tile(Color.BLUE, 0);
        m[1][0] = new Tile(Color.BLUE, 0);
        m[2][0] = new Tile(Color.BLUE, 0);
        m[3][0] = new Tile(Color.BLUE, 0);
        m[4][0] = new Tile(Color.BLUE, 0);
        m[5][0] = new Tile(Color.BLUE, 0);

        shelf.addTile(t1, 0);
    }

    @Test
    public void DFS_threeTilesSameColumn_correctOutput () throws fullColumnException {
        Tile t1 = new Tile(Color.YELLOW, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        Tile t3 = new Tile(Color.YELLOW, 0);

        boolean [][] b = new boolean[6][5];
        int c = 1;

        shelf.addTile(t1, 0);
        shelf.addTile(t2, 0);
        shelf.addTile(t3, 0);

        assertEquals(3, shelf.DFS(shelf.getStructure(), 3, 0, b,c));
    }


}
