package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoColumnsGoalTest {
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
    public void twoColumnsGoal_differentColumns(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.CYAN, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.PURPLE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.CYAN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.WHITE, 0);
        Tile t12 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 0, t2);
        s.setShelfTile(2, 0, t3);
        s.setShelfTile(3, 0, t4);
        s.setShelfTile(4, 0, t5);
        s.setShelfTile(5, 0, t6);
        s.setShelfTile(0, 3, t7);
        s.setShelfTile(1, 3, t8);
        s.setShelfTile(2, 3, t9);
        s.setShelfTile(3, 3, t10);
        s.setShelfTile(4, 3, t11);
        s.setShelfTile(5, 3, t12);
        TwoColumnsGoal objective = new TwoColumnsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void twoColumnsGoal_equalColumns(){
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
        Tile t11 = new Tile(Color.BLUE, 0);
        Tile t12 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 0, t2);
        s.setShelfTile(2, 0, t3);
        s.setShelfTile(3, 0, t4);
        s.setShelfTile(4, 0, t5);
        s.setShelfTile(5, 0, t6);
        s.setShelfTile(0, 3, t7);
        s.setShelfTile(1, 3, t8);
        s.setShelfTile(2, 3, t9);
        s.setShelfTile(3, 3, t10);
        s.setShelfTile(4, 3, t11);
        s.setShelfTile(5, 3, t12);
        TwoColumnsGoal objective = new TwoColumnsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void twoColumnsGoal_mixedColumns(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.PURPLE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.CYAN, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.CYAN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.YELLOW, 0);
        Tile t11 = new Tile(Color.YELLOW, 0);
        Tile t12 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 0, t2);
        s.setShelfTile(2, 0, t3);
        s.setShelfTile(3, 0, t4);
        s.setShelfTile(4, 0, t5);
        s.setShelfTile(5, 0, t6);
        s.setShelfTile(0, 3, t7);
        s.setShelfTile(1, 3, t8);
        s.setShelfTile(2, 3, t9);
        s.setShelfTile(3, 3, t10);
        s.setShelfTile(4, 3, t11);
        s.setShelfTile(5, 3, t12);
        TwoColumnsGoal objective = new TwoColumnsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void twoRowsGoal_nullMatrix(){
        TwoRowsGoal objective = new TwoRowsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
