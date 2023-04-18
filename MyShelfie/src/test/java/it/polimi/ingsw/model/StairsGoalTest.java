package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StairsGoalTest {

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
    public void stairsGoal_firstStair() {
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.GREEN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.CYAN, 0);
        Tile t14 = new Tile(Color.CYAN, 0);
        Tile t15 = new Tile(Color.CYAN, 0);
        Tile t16 = new Tile(Color.WHITE, 0);
        Tile t17 = new Tile(Color.WHITE, 0);
        Tile t18 = new Tile(Color.YELLOW, 0);
        Tile t19 = new Tile(Color.YELLOW, 0);
        Tile t20 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(0, 0, t1);
        s.setShelfTile(1, 0, t2);
        s.setShelfTile(2, 0, t3);
        s.setShelfTile(3, 0, t4);
        s.setShelfTile(4, 0, t5);
        s.setShelfTile(5, 0, t6);
        s.setShelfTile(1, 1, t7);
        s.setShelfTile(2, 1, t8);
        s.setShelfTile(3, 1, t9);
        s.setShelfTile(4, 1, t10);
        s.setShelfTile(5, 1, t11);
        s.setShelfTile(2, 2, t12);
        s.setShelfTile(3, 2, t13);
        s.setShelfTile(4, 2, t14);
        s.setShelfTile(5, 2, t15);
        s.setShelfTile(3, 3, t16);
        s.setShelfTile(4, 3, t17);
        s.setShelfTile(5, 3, t18);
        s.setShelfTile(4, 4, t19);
        s.setShelfTile(5, 4, t20);



        StairsGoal objective = new StairsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void stairsGoal_secondStair() {
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.GREEN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.CYAN, 0);
        Tile t14 = new Tile(Color.CYAN, 0);
        Tile t15 = new Tile(Color.CYAN, 0);
        s.setShelfTile(1, 0, t1);
        s.setShelfTile(2, 0, t2);
        s.setShelfTile(3, 0, t3);
        s.setShelfTile(4, 0, t4);
        s.setShelfTile(5, 0, t5);
        s.setShelfTile(2, 1, t6);
        s.setShelfTile(3, 1, t7);
        s.setShelfTile(4, 1, t8);
        s.setShelfTile(5, 1, t9);
        s.setShelfTile(3, 2, t10);
        s.setShelfTile(4, 1, t11);
        s.setShelfTile(5, 2, t12);
        s.setShelfTile(4, 3, t13);
        s.setShelfTile(5, 3, t14);
        s.setShelfTile(5, 4, t15);

        StairsGoal objective = new StairsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void stairsGoal_thirdStair() {
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.GREEN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.CYAN, 0);
        Tile t14 = new Tile(Color.CYAN, 0);
        Tile t15 = new Tile(Color.CYAN, 0);
        s.setShelfTile(5, 0, t1);
        s.setShelfTile(5, 1, t2);
        s.setShelfTile(4, 1, t3);
        s.setShelfTile(5, 2, t4);
        s.setShelfTile(4, 2, t5);
        s.setShelfTile(3, 2, t6);
        s.setShelfTile(5, 3, t7);
        s.setShelfTile(4, 3, t8);
        s.setShelfTile(3, 3, t9);
        s.setShelfTile(2, 3, t10);
        s.setShelfTile(5, 4, t11);
        s.setShelfTile(4, 4, t12);
        s.setShelfTile(3, 4, t13);
        s.setShelfTile(2, 4, t14);
        s.setShelfTile(1, 4, t15);

        StairsGoal objective = new StairsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void stairsGoal_fourthStair() {
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.WHITE, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.GREEN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.CYAN, 0);
        Tile t14 = new Tile(Color.CYAN, 0);
        Tile t15 = new Tile(Color.CYAN, 0);
        Tile t16 = new Tile(Color.WHITE, 0);
        Tile t17 = new Tile(Color.WHITE, 0);
        Tile t18 = new Tile(Color.YELLOW, 0);
        Tile t19 = new Tile(Color.YELLOW, 0);
        Tile t20 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(5, 0, t1);
        s.setShelfTile(4, 0, t2);
        s.setShelfTile(5, 1, t3);
        s.setShelfTile(4, 1, t4);
        s.setShelfTile(3, 1, t5);
        s.setShelfTile(5, 2, t6);
        s.setShelfTile(4, 2, t7);
        s.setShelfTile(3, 2, t8);
        s.setShelfTile(2, 2, t9);
        s.setShelfTile(5, 3, t10);
        s.setShelfTile(4, 3, t11);
        s.setShelfTile(3, 3, t12);
        s.setShelfTile(2, 3, t13);
        s.setShelfTile(1, 3, t14);
        s.setShelfTile(5, 4, t15);
        s.setShelfTile(4, 4, t16);
        s.setShelfTile(3, 4, t17);
        s.setShelfTile(2, 4, t18);
        s.setShelfTile(1, 4, t19);
        s.setShelfTile(0, 4, t20);



        StairsGoal objective = new StairsGoal();
        assertTrue(objective.commonGoalAlg(s));
    }

    @Test
    public void stairsGoal_notAStair() {
        Tile t2 = new Tile(Color.WHITE, 0);
        Tile t3 = new Tile(Color.GREEN, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.GREEN, 0);
        Tile t9 = new Tile(Color.GREEN, 0);
        Tile t10 = new Tile(Color.PURPLE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.CYAN, 0);
        Tile t14 = new Tile(Color.CYAN, 0);
        Tile t15 = new Tile(Color.CYAN, 0);
        Tile t16 = new Tile(Color.WHITE, 0);
        Tile t17 = new Tile(Color.WHITE, 0);
        Tile t18 = new Tile(Color.YELLOW, 0);
        Tile t19 = new Tile(Color.YELLOW, 0);
        Tile t20 = new Tile(Color.YELLOW, 0);
        s.setShelfTile(1, 0, t2);
        s.setShelfTile(2, 0, t3);
        s.setShelfTile(3, 0, t4);
        s.setShelfTile(4, 0, t5);
        s.setShelfTile(1, 1, t7);
        s.setShelfTile(2, 1, t8);
        s.setShelfTile(3, 1, t9);
        s.setShelfTile(4, 1, t10);
        s.setShelfTile(5, 1, t11);
        s.setShelfTile(2, 2, t12);
        s.setShelfTile(3, 2, t13);
        s.setShelfTile(4, 2, t14);
        s.setShelfTile(5, 2, t15);
        s.setShelfTile(3, 3, t16);
        s.setShelfTile(4, 3, t17);
        s.setShelfTile(5, 3, t18);
        s.setShelfTile(4, 4, t19);
        s.setShelfTile(5, 4, t20);



        StairsGoal objective = new StairsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }

    @Test
    public void stairsGoal_nullMatrix() {

        StairsGoal objective = new StairsGoal();
        assertFalse(objective.commonGoalAlg(s));
    }
}
