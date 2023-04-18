package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void addTile_correctInput_correctOutput() throws fullColumnException {
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

    @Test
    public void isSafe_true(){
        Tile t = new Tile(Color.YELLOW, 0);
        int i = 2, j = 4;
        shelf.setShelfTile(i, j, t);
        boolean[][] visited = new boolean[6][5];
        assertTrue(shelf.isSafe(shelf.getStructure(), i, j, visited));
    }

    @Test
    public void isSafe_alreadyVisited(){
        Tile t = new Tile(Color.YELLOW, 0);
        int i = 2, j = 4;
        shelf.setShelfTile(i, j, t);
        boolean[][] visited = new boolean[6][5];
        visited[i][j] = true;
        assertFalse(shelf.isSafe(shelf.getStructure(), i, j, visited));
    }

    @Test
    public void isSafe_nullTile(){
        int i = 2, j = 4;
        boolean[][] visited = new boolean[6][5];
        assertFalse(shelf.isSafe(shelf.getStructure(), i, j, visited));
    }

    @Test
    public void adjacentTile_threeGroups(){
        Tile t = new Tile(Color.YELLOW, 0);
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.YELLOW, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.BLUE, 0);
        Tile t9 = new Tile(Color.BLUE, 0);
        Tile t10 = new Tile(Color.BLUE, 0);
        Tile t11 = new Tile(Color.BLUE, 0);
        Tile t12 = new Tile(Color.BLUE, 0);

        shelf.setShelfTile(0, 0, t);
        shelf.setShelfTile(0, 1, t1);
        shelf.setShelfTile(0, 1, t2);
        shelf.setShelfTile(0, 1, t3);
        shelf.setShelfTile(0, 1, t4);
        shelf.setShelfTile(0, 1, t5);
        shelf.setShelfTile(0, 1, t6);
        shelf.setShelfTile(0, 1, t7);
        shelf.setShelfTile(0, 1, t8);

        boolean[][] visited = new boolean[6][5];
        assertTrue(shelf.isSafe(shelf.getStructure(), i, j, visited));
    }

}
