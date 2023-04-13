package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player("chiasmo", 0, true);
    }

    @After
    public void tearDown() {
        player = null;
    }

    //aggiungi test insertTile una volta sistemato il metodo
    //test su getTileByPosition?
    //test getMyShelfie

    @Test
    public void setAndGetSPersonalGoal_correctInput_correctOutput() {
        personalGoal goal = new personalGoal(1);
        player.setPersonalGoal(goal);
        assertEquals(goal, player.getPersonalGoal());
    }

    @Test
    public void setAndGetEndGame_correctOutput() {
        player.setEndGame();
        assertEquals(true, player.isEndGame());
    }

    @Test
    public void getId_correctOutput() {
        assertEquals(0, player.getId());
    }

    @Test
    public void getUsername_correctOutput() {
        assertEquals("chiasmo", player.getUsername());
    }

    @Test
    public void getAndSetPoints_correctInput_correctOutput() {
        player.setPoints(10);
        assertEquals(10, player.getPoints());
    }

    @Test
    public void isYellowChair_correctOutput() {
        assertEquals(true, player.isYellowChair());
    }
}
