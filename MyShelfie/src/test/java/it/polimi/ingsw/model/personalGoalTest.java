package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class personalGoalTest {

    personalGoal goal;

    @Before
    public void setUp() {
        goal = new personalGoal(1);
    }

    @After
    public void tearDown() {

        goal = null;
    }

    //aggiungi test per createTheGoal

    @Test
    public void getId_correctInput_correctOutput() {
        assertEquals(1, goal.getId());
    }

    @Test
    public void getTilesCount_zeroTiles_correctOutput() {
        assertEquals(0, goal.getTilesCount());
    }

    @Test
    public void getTilesCount_someTiles_correctOutput() {

    }

    @Test
    public void getAchieved_emptyList_correctOutput() {
        assertTrue(goal.getAchieved().isEmpty());
    }

    @Test
    public void getAchieved_notEmptyList_correctOutput() {

    }

    @Test
    public void getBody_emptyBody_correctOutput() {
        assertNotEquals(null, goal.getBody());
    }

    @Test
    public void checkPoints_zeroPoints_correctOutput () {

    }

    @Test
    public void checkPoints_somePoints_correctOutput() {

    }

}
