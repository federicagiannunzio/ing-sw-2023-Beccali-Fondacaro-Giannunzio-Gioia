package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EightGoalTest {
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
    public void eightGoal_eightEqual(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.BLUE, 0);

        s.setShelfTile(2, 1, t1);
        s.setShelfTile(3, 2, t2);
        s.setShelfTile(4, 3, t3);
        s.setShelfTile(2, 3, t4);
        s.setShelfTile(5, 4, t5);
        s.setShelfTile(5, 1, t6);
        s.setShelfTile(3, 4, t7);
        s.setShelfTile(4, 1, t8);
        EightGoal objective = new EightGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void eightGoal_eightDifferent(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.CYAN, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.WHITE, 0);
        Tile t5 = new Tile(Color.GREEN, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.PURPLE, 0);
        Tile t8 = new Tile(Color.YELLOW, 0);

        s.setShelfTile(0, 1, t1);
        s.setShelfTile(3, 2, t2);
        s.setShelfTile(4, 3, t3);
        s.setShelfTile(2, 3, t4);
        s.setShelfTile(5, 4, t5);
        s.setShelfTile(1, 1, t6);
        s.setShelfTile(5, 3, t7);
        s.setShelfTile(4, 2, t8);
        EightGoal objective = new EightGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void eightGoal_nullMatrix(){
        EightGoal objective = new EightGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void eightGoal_lessThanEight(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);

        s.setShelfTile(0, 1, t1);
        s.setShelfTile(3, 2, t2);
        s.setShelfTile(4, 3, t3);
        s.setShelfTile(2, 3, t4);
        s.setShelfTile(5, 4, t5);
        s.setShelfTile(1, 1, t6);
        s.setShelfTile(5, 3, t7);
        EightGoal objective = new EightGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
