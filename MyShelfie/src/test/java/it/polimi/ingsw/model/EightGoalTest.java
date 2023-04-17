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
    public void tearDown() {s = null;}

    @Test
    public void eightGoal_eightEquals(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.BLUE, 0);
        s.setShelfTile(t1, 2 , 4);
        s.setShelfTile(t2, 1 , 3);
        s.setShelfTile(t3, 1 , 2);
        s.setShelfTile(t4, 5 , 1);
        s.setShelfTile(t5, 3 , 3);
        s.setShelfTile(t6, 4 , 4);
        s.setShelfTile(t7, 4 , 1);
        s.setShelfTile(t8, 5 , 3);
        EightGoal corner = new EightGoal();
        assertTrue(corner.commonGoalAlg(s));
    }

    @Test
    public void eightGoal_eightDifferent(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.GREEN, 0);
        Tile t3 = new Tile(Color.WHITE, 0);
        Tile t4 = new Tile(Color.PURPLE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.CYAN, 0);
        Tile t8 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(t1, 2 , 4);
        s.setShelfTile(t2, 1 , 3);
        s.setShelfTile(t3, 3 , 2);
        s.setShelfTile(t4, 5 , 4);
        s.setShelfTile(t5, 3 , 3);
        s.setShelfTile(t6, 4 , 4);
        s.setShelfTile(t7, 4 , 1);
        s.setShelfTile(t8, 5 , 3);
        EightGoal corner = new EightGoal();
        assertFalse(corner.commonGoalAlg(s));
    }

    @Test
    public void eightGoal_nullMatrix(){
        EightGoal corner = new EightGoal();
        assertFalse(corner.commonGoalAlg(s));
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
        s.setShelfTile(t1, 2 , 4);
        s.setShelfTile(t2, 1 , 3);
        s.setShelfTile(t3, 3 , 2);
        s.setShelfTile(t4, 5 , 4);
        s.setShelfTile(t5, 3 , 3);
        s.setShelfTile(t6, 4 , 4);
        s.setShelfTile(t7, 4 , 1);
        EightGoal corner = new EightGoal();
        assertFalse(corner.commonGoalAlg(s));
    }
}
