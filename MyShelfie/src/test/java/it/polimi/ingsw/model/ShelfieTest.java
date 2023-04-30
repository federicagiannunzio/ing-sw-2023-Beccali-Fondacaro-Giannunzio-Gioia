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
    public void getStructure_addedTilesSameColumn_correctOutput() throws FullColumnException {
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
    public void getStructure_addedTilesDifferentColumns_correctOutput() throws FullColumnException {
        Tile[][] expected = new Tile[6][5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        expected[5][1] = t1;
        expected[5][2] = t2;
        shelf.addTile(t1, 1);
        shelf.addTile(t2, 2);

        assertArrayEquals(expected, shelf.getStructure());
    }

    @Test (expected = FullColumnException.class)
    public void addTiles_alreadyFull_shouldThrowException() throws FullColumnException {
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
    public void DFS_threeTilesSameColumn_correctOutput () throws FullColumnException {
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

    @Test
    public void DFS_noSameColorTiles_correctOutput () throws FullColumnException {
        Tile t1 = new Tile(Color.YELLOW, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.WHITE, 0);

        boolean [][] b = new boolean[6][5];
        int c = 1;

        shelf.addTile(t1, 0);
        shelf.addTile(t2, 0);
        shelf.addTile(t3, 0);

        assertEquals(1, shelf.DFS(shelf.getStructure(), 3, 0, b,c));
    }

    @Test
    public void DFS_nullMatrix_correctOutput () throws FullColumnException {

        boolean [][] b = new boolean[6][5];
        int c = 1;

        assertEquals(1, shelf.DFS(shelf.getStructure(), 3, 0, b,c));
    }

    @Test
    public void adjacentItems_correctOutput(){
        int[] arr = new int[5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.GREEN, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.WHITE, 0);
        Tile t9 = new Tile(Color.WHITE, 0);
        Tile t10 = new Tile(Color.WHITE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.PURPLE, 0);
        Tile t14 = new Tile(Color.PURPLE, 0);
        Tile t15 = new Tile(Color.GREEN, 0);
        shelf.setShelfTile(0, 0, t1);
        shelf.setShelfTile(0, 1, t2);
        shelf.setShelfTile(1, 1, t3);
        shelf.setShelfTile(0, 2, t4);
        shelf.setShelfTile(2, 1, t5);
        shelf.setShelfTile(3, 3, t6);
        shelf.setShelfTile(3, 4, t7);
        shelf.setShelfTile(4, 4, t8);
        shelf.setShelfTile(5, 4, t9);
        shelf.setShelfTile(5, 3, t10);
        shelf.setShelfTile(3, 0, t11);
        shelf.setShelfTile(4, 0, t12);
        shelf.setShelfTile(5, 1, t13);
        shelf.setShelfTile(3, 1, t14);
        shelf.setShelfTile(2, 2, t15);
        arr = shelf.adjacentItem();
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(0, arr[2]);
        assertEquals(1, arr[3]);
        assertEquals(0, arr[4]);

    }

    @Test
    public void adjacentItems_nullMatrix(){
        int[] arr = new int[5];
        arr = shelf.adjacentItem();
        assertEquals(0, arr[0]);
        assertEquals(0, arr[1]);
        assertEquals(0, arr[2]);
        assertEquals(0, arr[3]);
        assertEquals(0, arr[4]);

    }
}
