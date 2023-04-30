package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourGroupsGoalTest {
    private Shelfie shelf;

    @Before
    public void setUp() {
        shelf = new Shelfie();
    }

    @After
    public void tearDown() {
        shelf = null;
    }

    @Test
    public void fourGroupsGoal_falseReturn() {
        int[] arr = new int[5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.GREEN, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.WHITE, 0);
        Tile t9 = new Tile(Color.WHITE, 0);
        Tile t10 = new Tile(Color.WHITE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.PURPLE, 0);
        Tile t14 = new Tile(Color.PURPLE, 0);
        Tile t15 = new Tile(Color.GREEN, 0);
        shelf.setShelfTile(0, 0, t1);
        shelf.setShelfTile(0, 1, t2);
        shelf.setShelfTile(1, 1, t3);
        shelf.setShelfTile(0, 2, t4);
        shelf.setShelfTile(2, 1, t5);
        shelf.setShelfTile(3, 3, t6);
        shelf.setShelfTile(3, 4, t7);
        shelf.setShelfTile(4, 4, t8);
        shelf.setShelfTile(5, 4, t9);
        shelf.setShelfTile(5, 3, t10);
        shelf.setShelfTile(3, 0, t11);
        shelf.setShelfTile(4, 0, t12);
        shelf.setShelfTile(5, 1, t13);
        shelf.setShelfTile(3, 1, t14);
        shelf.setShelfTile(2, 2, t15);
        FourGroupsGoal objective = new FourGroupsGoal();
        assertFalse(objective.commonGoalAlg(shelf));
    }

    @Test
    public void fourGroupsGoal_trueReturn() {
        int[] arr = new int[5];
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.BLUE, 0);
        Tile t3 = new Tile(Color.BLUE, 0);
        Tile t4 = new Tile(Color.BLUE, 0);
        Tile t5 = new Tile(Color.BLUE, 0);
        Tile t6 = new Tile(Color.GREEN, 0);
        Tile t7 = new Tile(Color.GREEN, 0);
        Tile t8 = new Tile(Color.WHITE, 0);
        Tile t9 = new Tile(Color.WHITE, 0);
        Tile t10 = new Tile(Color.WHITE, 0);
        Tile t11 = new Tile(Color.PURPLE, 0);
        Tile t12 = new Tile(Color.PURPLE, 0);
        Tile t13 = new Tile(Color.PURPLE, 0);
        Tile t14 = new Tile(Color.PURPLE, 0);
        Tile t15 = new Tile(Color.GREEN, 0);
        Tile t16 = new Tile(Color.PURPLE, 0);
        Tile t17 = new Tile(Color.PURPLE, 0);
        Tile t18 = new Tile(Color.PURPLE, 0);
        Tile t19 = new Tile(Color.PURPLE, 0);
        Tile t20 = new Tile(Color.CYAN, 0);
        Tile t21 = new Tile(Color.CYAN, 0);
        Tile t22 = new Tile(Color.CYAN, 0);
        Tile t23 = new Tile(Color.CYAN, 0);
        shelf.setShelfTile(0, 0, t1);
        shelf.setShelfTile(0, 1, t2);
        shelf.setShelfTile(1, 1, t3);
        shelf.setShelfTile(0, 2, t4);
        shelf.setShelfTile(2, 1, t5);
        shelf.setShelfTile(3, 3, t6);
        shelf.setShelfTile(3, 4, t7);
        shelf.setShelfTile(4, 4, t8);
        shelf.setShelfTile(5, 4, t9);
        shelf.setShelfTile(5, 3, t10);
        shelf.setShelfTile(2, 3, t11);
        shelf.setShelfTile(1, 3, t12);
        shelf.setShelfTile(1, 4, t13);
        shelf.setShelfTile(1, 2, t14);
        shelf.setShelfTile(2, 2, t15);
        shelf.setShelfTile(3, 0, t16);
        shelf.setShelfTile(3, 1, t17);
        shelf.setShelfTile(4, 1, t18);
        shelf.setShelfTile(4, 0, t19);
        shelf.setShelfTile(3, 2, t20);
        shelf.setShelfTile(4, 2, t21);
        shelf.setShelfTile(5, 2, t22);
        shelf.setShelfTile(4, 3, t23);
        arr = shelf.adjacentItem();
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);
        FourGroupsGoal objective = new FourGroupsGoal();
        assertTrue(objective.commonGoalAlg(shelf));
    }
}