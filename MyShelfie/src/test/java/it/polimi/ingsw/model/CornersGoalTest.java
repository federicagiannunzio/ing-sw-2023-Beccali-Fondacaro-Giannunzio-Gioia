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
    public void tearDown() {
        s = null;
    }

    @Test
    public void cornersGoal_equalCorners(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(0, 4, t2);
        s.setShelfTile(5, 0, t3);
        s.setShelfTile(5, 4, t4);
        CornersGoal objective = new CornersGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void cornersGoal_differentCorners(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.CYAN, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(0, 4, t2);
        s.setShelfTile(5, 0, t3);
        s.setShelfTile(5, 4, t4);
        CornersGoal objective = new CornersGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void cornersGoal_nullCorners(){
        CornersGoal objective = new CornersGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

}
