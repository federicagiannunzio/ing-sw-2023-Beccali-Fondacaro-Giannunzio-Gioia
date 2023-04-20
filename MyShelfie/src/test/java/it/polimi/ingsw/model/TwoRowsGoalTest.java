package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoRowsGoalTest {
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
    public void twoRowsGoal_differentRows(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.CYAN, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.CYAN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(0, 1, t2);
        s.setShelfTile(0, 2, t3);
        s.setShelfTile(0, 3, t4);
        s.setShelfTile(0, 4, t5);
        s.setShelfTile(2, 0, t6);
        s.setShelfTile(2, 1, t7);
        s.setShelfTile(2, 2, t8);
        s.setShelfTile(2, 3, t9);
        s.setShelfTile(2, 4, t10);
        TwoRowsGoal objective = new TwoRowsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void twoRowsGoal_equalRows(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.BLUE, 0);
        Tile t9 = new Tile(Color.BLUE, 0);
        Tile t10 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(0, 1, t2);
        s.setShelfTile(0, 2, t3);
        s.setShelfTile(0, 3, t4);
        s.setShelfTile(0, 4, t5);
        s.setShelfTile(3, 0, t6);
        s.setShelfTile(3, 1, t7);
        s.setShelfTile(3, 2, t8);
        s.setShelfTile(3, 3, t9);
        s.setShelfTile(3, 4, t10);
        TwoRowsGoal objective = new TwoRowsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void twoRowsGoal_mixedRows(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.WHITE, 0);
        Tile t5 = new Tile(Color.CYAN, 0);
        Tile t6 = new Tile(Color.CYAN, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.YELLOW, 0);
        Tile t9 = new Tile(Color.PURPLE, 0);
        Tile t10 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(0, 1, t2);
        s.setShelfTile(0, 2, t3);
        s.setShelfTile(0, 3, t4);
        s.setShelfTile(0, 4, t5);
        s.setShelfTile(3, 0, t6);
        s.setShelfTile(3, 1, t7);
        s.setShelfTile(3, 2, t8);
        s.setShelfTile(3, 3, t9);
        s.setShelfTile(3, 4, t10);
        TwoRowsGoal objective = new TwoRowsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void twoRowsGoal_nullMatrix(){
        TwoRowsGoal objective = new TwoRowsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
