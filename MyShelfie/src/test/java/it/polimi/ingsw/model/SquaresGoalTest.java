package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SquaresGoalTest {
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
    public void squaresGoal_twoSquares(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.BLUE, 0);
        Tile t8 = new Tile(Color.BLUE, 0);

        s.setShelfTile(0, 1, t1);
        s.setShelfTile(0, 0, t2);
        s.setShelfTile(1, 0, t3);
        s.setShelfTile(1, 1, t4);
        s.setShelfTile(3, 4, t5);
        s.setShelfTile(3, 3, t6);
        s.setShelfTile(4, 3, t7);
        s.setShelfTile(4, 4, t8);
        SquaresGoal objective = new SquaresGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void squaresGoal_differentSquares(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.YELLOW, 0);
        Tile t7 = new Tile(Color.YELLOW, 0);
        Tile t8 = new Tile(Color.YELLOW, 0);

        s.setShelfTile(0, 1, t1);
        s.setShelfTile(0, 0, t2);
        s.setShelfTile(1, 0, t3);
        s.setShelfTile(1, 1, t4);
        s.setShelfTile(3, 4, t5);
        s.setShelfTile(3, 3, t6);
        s.setShelfTile(4, 3, t7);
        s.setShelfTile(4, 4, t8);
        SquaresGoal objective = new SquaresGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void squaresGoal_randomSquares(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.PURPLE, 0);
        Tile t4 = new Tile(Color.GREEN, 0);
        Tile t5 = new Tile(Color.YELLOW, 0);
        Tile t6 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.WHITE, 0);
        Tile t8 = new Tile(Color.YELLOW, 0);

        s.setShelfTile(0, 1, t1);
        s.setShelfTile(0, 0, t2);
        s.setShelfTile(1, 0, t3);
        s.setShelfTile(1, 1, t4);
        s.setShelfTile(3, 4, t5);
        s.setShelfTile(3, 3, t6);
        s.setShelfTile(4, 3, t7);
        s.setShelfTile(4, 4, t8);
        SquaresGoal objective = new SquaresGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void squaresGoal_nullMatrix(){
        SquaresGoal objective = new SquaresGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
