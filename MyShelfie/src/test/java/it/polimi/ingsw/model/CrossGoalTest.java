package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CrossGoalTest{
    private Shelfie s;

    @Before
    public void setUp() {
        s = new Shelfie();
    }

    @After
    public void tearDown() {s = null;}

    @Test
    public void crossGoal_crossExists(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(t1, 0 , 0);
        s.setShelfTile(t2, 1 , 1);
        s.setShelfTile(t3, 2 , 2);
        s.setShelfTile(t4, 2 , 0);
        s.setShelfTile(t5, 0 , 2);
        CrossGoal corner = new CrossGoal();
        assertTrue(corner.commonGoalAlg(s));
    }

    @Test
    public void crossGoal_crossColorsDifferent(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.GREEN, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(t1, 0 , 0);
        s.setShelfTile(t2, 1 , 1);
        s.setShelfTile(t3, 2 , 2);
        s.setShelfTile(t4, 2 , 0);
        s.setShelfTile(t5, 0 , 2);
        CrossGoal corner = new CrossGoal();
        assertFalse(corner.commonGoalAlg(s));
    }

    @Test
    public void crossGoal_nullMatrix(){
        CrossGoal corner = new CrossGoal();
        assertFalse(corner.commonGoalAlg(s));
    }

    @Test
    public void crossGoal_nullElementsInCross(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        s.setShelfTile(t1, 0 , 0);
        s.setShelfTile(t2, 1 , 1);
        s.setShelfTile(t3, 2 , 2);
        s.setShelfTile(t4, 2 , 0);
        CrossGoal corner = new CrossGoal();
        assertFalse(corner.commonGoalAlg(s));
    }
}
