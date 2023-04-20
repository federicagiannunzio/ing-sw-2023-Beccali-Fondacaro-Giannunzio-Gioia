package it.polimi.ingsw.model;

import jdk.jshell.Diag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiagonalGoalTest {
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
    public void diagonalGoal_firstDiagonal(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 1, t2);
        s.setShelfTile(2, 2, t3);
        s.setShelfTile(3, 3, t4);
        s.setShelfTile(4, 4, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_secondDiagonal(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(1, 0, t1);
        s.setShelfTile(2, 1, t2);
        s.setShelfTile(3, 2, t3);
        s.setShelfTile(4, 3, t4);
        s.setShelfTile(5, 4, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_thirdDiagonal(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(0, 4, t1);
        s.setShelfTile(1, 3, t2);
        s.setShelfTile(2, 2, t3);
        s.setShelfTile(3, 1, t4);
        s.setShelfTile(4, 0, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_fourthDiagonal(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(1, 4, t1);
        s.setShelfTile(2, 3, t2);
        s.setShelfTile(3, 2, t3);
        s.setShelfTile(4, 1, t4);
        s.setShelfTile(5, 0, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_nullMatrix(){
        DiagonalGoal objective = new DiagonalGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_differentColors(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.GREEN, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.CYAN, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(1, 4, t1);
        s.setShelfTile(2, 3, t2);
        s.setShelfTile(3, 2, t3);
        s.setShelfTile(4, 1, t4);
        s.setShelfTile(5, 0, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_nullElements(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(1, 4, t1);
        s.setShelfTile(3, 2, t3);
        s.setShelfTile(4, 1, t4);
        s.setShelfTile(5, 0, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void diagonalGoal_randomTilePos(){
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        s.setShelfTile(2, 4, t1);
        s.setShelfTile(5, 3, t2);
        s.setShelfTile(0, 0, t3);
        s.setShelfTile(4, 1, t4);
        s.setShelfTile(5, 0, t5);
        DiagonalGoal objective = new DiagonalGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
