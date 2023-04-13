package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void getId_correctInput_correctOutput() {
        assertEquals(1, goal.getId());
    }

    @Test
    public void getTilesCount_correctInput_correctOutput() {
        assertEquals(0, goal.getTilesCount());
    }

    @Test
    public void getAchieved_emptyList_correctOutput() {

    }

    @Test
    public void getAchieved_notEmpyList_correctOutput() {

    }

    @Test
    public void getBody_correctInput_correctOutput() {

    }

    @Test
    public void checkPoints_zeroPoints_correctOutput () {

    }

    @Test
    public void checkPoints_somePoints_correctOutput() {

    }

    //aggiungi test per createTheGoal



}
