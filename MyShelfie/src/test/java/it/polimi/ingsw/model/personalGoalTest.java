package it.polimi.ingsw.model;//package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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

    @Test
    public void createTheGoal_correctInput_correctOutput() throws Exception {
        int id = 7;
        goal.createTheGoal(id);
        Tile [][] b = goal.getBody();
        assertEquals(Color.PURPLE, b[0][0].getColor());
        assertEquals(Color.BLUE, b[0][2].getColor());
        assertEquals(Color.GREEN, b[1][4].getColor());
        assertEquals(Color.WHITE, b[2][3].getColor());
        assertEquals(Color.YELLOW, b[3][1].getColor());
        assertEquals(Color.CYAN, b[5][2].getColor());
    }

    //come faccio a verificare che mi arriva un'eccezione?
    @Test
    public void createTheGoal_notExistingID_shouldThrowException() throws Exception {
        assertThrows(Exception.class, () -> {goal.createTheGoal(0);});
    }

    @Test
    public void getId_correctInput_correctOutput() {
        assertEquals(1, goal.getId());
    }

    @Test
    public void getTilesCount_zeroTiles_correctOutput() {
        assertEquals(0, goal.getTilesCount());
    }


    @Test
    public void getAchieved_emptyList_correctOutput() {
        assertTrue(goal.getAchieved().isEmpty());
    }

    @Test
    public void getBody_emptyBody_correctOutput() {
        assertNotEquals(null, goal.getBody());
    }

    @Test
    public void checkPoints_emptyShelfie_correctOutput () {
        Shelfie s = new Shelfie();
        int points = goal.checkpoints(s);

        assertEquals(0, points);

    }

    @Test
    public void checkPoints_noTilesMatching_zeroPoints() throws Exception {
        goal.createTheGoal(goal.getId());
        Shelfie s = new Shelfie();
        Tile [][] m = s.getStructure();

        m[0][0] = new Tile(Color.YELLOW, 0);
        m[0][2] = new Tile(Color.CYAN, 0);
        m[1][4] = new Tile(Color.WHITE, 0);
        assertEquals(0, goal.checkpoints(s));
    }

    @Test
    public void checkPoints_threeTilesMatching_fourPoints() throws Exception {
        goal.createTheGoal(goal.getId());
        Shelfie s = new Shelfie();
        Tile [][] m = s.getStructure();

        m[0][0] = new Tile(Color.PURPLE, 0);
        m[0][2] = new Tile(Color.BLUE, 0);
        m[1][4] = new Tile(Color.GREEN, 0);
        assertEquals(4, goal.checkpoints(s));
    }

    //the player has a shelf that contains matched tiles and other tiles in non-rilevant positions
    @Test
    public void checkPoints_differentTiles_correctOutput() throws Exception {
        goal.createTheGoal(goal.getId());
        Shelfie s = new Shelfie();
        Tile [][] m = s.getStructure();
        m[0][0] = new Tile(Color.PURPLE, 0);
        assertEquals(1, goal.checkpoints(s));
        m[0][2] = new Tile(Color.BLUE, 0);
        m[1][4] = new Tile(Color.GREEN, 0);
        assertEquals(4, goal.checkpoints(s));
        m[1][1] = new Tile(Color.BLUE, 0);
        m[5][4] = new Tile(Color.YELLOW, 0);
        assertEquals(4, goal.checkpoints(s));
    }

    @Test
    public void getAchieved_noTilesMatching_emptyArray() throws Exception {
        goal.createTheGoal(goal.getId());
        Shelfie s = new Shelfie();
        Tile [][] m = s.getStructure();

        m[0][0] = new Tile(Color.YELLOW, 0);
        m[0][2] = new Tile(Color.CYAN, 0);
        m[1][4] = new Tile(Color.WHITE, 0);
        assertTrue(goal.getAchieved().isEmpty());
    }

    @Test
    public void getAchieved_threeTilesMatching_correctOutput() throws Exception {
        goal.createTheGoal(goal.getId());
        Shelfie s = new Shelfie();
        Tile [][] m = s.getStructure();
        ArrayList<Color> expected = new ArrayList<>();

        expected.add(Color.PURPLE);
        expected.add(Color.BLUE);
        expected.add(Color.GREEN);

        m[0][0] = new Tile(Color.PURPLE, 0);
        m[0][2] = new Tile(Color.BLUE, 0);
        m[1][4] = new Tile(Color.GREEN, 0);

        goal.checkpoints(s);

        assertTrue(goal.getAchieved().containsAll(expected));
    }
}
