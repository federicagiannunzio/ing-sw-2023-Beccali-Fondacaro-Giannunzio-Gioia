package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CrossGoalTest {
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
    public void crossGoal_equalCross(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 1, t2);
        s.setShelfTile(2, 2, t3);
        s.setShelfTile(0, 2, t4);
        s.setShelfTile(2, 0, t5);
        CrossGoal objective = new CrossGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void crossGoal_differentCross(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.CYAN, 0);
        s.setShelfTile(2, 1, t1);
        s.setShelfTile(3, 2, t2);
        s.setShelfTile(4, 3, t3);
        s.setShelfTile(2, 3, t4);
        s.setShelfTile(4, 1, t5);
        CrossGoal objective = new CrossGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void crossGoal_nullCross(){
        CrossGoal objective = new CrossGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
