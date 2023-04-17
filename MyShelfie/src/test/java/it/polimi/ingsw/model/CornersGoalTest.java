package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CornersGoalTest {
    private Shelfie s;

    @Before
    public void setUp() {
        s = new Shelfie();
    }

    @After
    public void tearDown() {s = null;}

    @Test
    public void cornersGoal_equalCorners(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        s.setShelfTile(t1, 0 , 0);
        s.setShelfTile(t2, 0 , 4);
        s.setShelfTile(t3, 5 , 0);
        s.setShelfTile(t4, 5 , 4);
        CornersGoal corner = new CornersGoal();
        assertTrue(corner.commonGoalAlg(s));
    }

    @Test
    public void cornersGoal_differentCorners(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.YELLOW, 0);
        Tile t4 = new Tile(Color.GREEN, 0);
        s.setShelfTile(t1, 0 , 0);
        s.setShelfTile(t2, 0 , 4);
        s.setShelfTile(t3, 5 , 0);
        s.setShelfTile(t4, 5 , 4);
        CornersGoal corner = new CornersGoal();
        assertFalse(corner.commonGoalAlg(s));
    }

    @Test
    public void cornersGoal_nullCorners(){
        CornersGoal corner = new CornersGoal();
        assertFalse(corner.commonGoalAlg(s));
    }
}
