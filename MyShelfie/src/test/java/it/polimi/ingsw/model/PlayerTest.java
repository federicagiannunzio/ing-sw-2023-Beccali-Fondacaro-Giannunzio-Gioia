package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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

    //aggiungi test pickTiles una volta sistemato il metodo
    //test su getTileByPosition?

    @Test
    public void insertTile_fullArray_correctOutput() {
        Shelfie s = new Shelfie();
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 1);
        Tile t3 = new Tile(Color.CYAN, 2);

        s.addTile(t1, 0);
        s.addTile(t2, 0);
        s.addTile(t3, 0);

        player.getMyTiles().add(t1);
        player.getMyTiles().add(t2);
        player.getMyTiles().add(t3);
        player.insertTile(0,0);
        player.insertTile(0, 0);
        player.insertTile(0, 0);

        assertArrayEquals(s.getStructure(), player.getMyShelfie().getStructure());
        assertTrue(player.getMyTiles().isEmpty());
    }

    @Test
    public void setAndGetSPersonalGoal_correctInput_correctOutput() {
        personalGoal goal = new personalGoal(1);
        player.setPersonalGoal(goal);
        assertEquals(goal, player.getPersonalGoal());
    }

    @Test
    public void setAndGetEndGame_correctOutput() {
        player.setEndGame();
        assertTrue(player.isEndGame());
    }

    @Test
    public void getAndSetPoints_correctInput_correctOutput() {
        player.setPoints(10);
        assertEquals(10, player.getPoints());
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
    public void isYellowChair_correctOutput() {

        assertTrue(player.isYellowChair());
    }

    @Test
    public void getMyShelfie_Empty_correctOutput() {
        assertNotEquals(null, player.getMyShelfie());
    }

    //test in cui la shelf del giocatore non è vuota ma ha una tessera dentro, forse va implementato diversamente
    @Test
    public void getMyShelfie_notEmpty_correctOutput() {
        Shelfie s = new Shelfie();
        Tile t = new Tile(Color.BLUE, 0);
        s.addTile(t, 0);

        player.getMyShelfie().addTile(t,0);
        assertNotEquals(s, player.getMyShelfie());
    }

    @Test
    public void getMyTiles_Empty_correctOutput() {
        assertTrue(player.getMyTiles().isEmpty());
    }

    //stesso discorso di shelfie
    @Test
    public void getMyTiles_notEmpty_correctOutput() {

        ArrayList<Tile> a = new ArrayList<>();
        Tile t1 = new Tile(Color.BLUE, 0);
        Tile t2 = new Tile(Color.YELLOW, 0);
        Tile t3 = new Tile(Color.GREEN, 0);

        //setup expected array
        a.add(t1);
        a.add(t2);
        a.add(t3);

        //setup Tiles in the array
        player.getMyTiles().add(t1);
        player.getMyTiles().add(t2);
        player.getMyTiles().add(t3);

        assertEquals(a, player.getMyTiles());

    }
}
