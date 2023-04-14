package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileTest {

    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(Color.BLUE, 0);
    }

    @After
    public void tearDown() {
        tile = null;
    }

    @Test
    public void getColor_correctInput_correctOutput() {
        assertEquals(Color.BLUE, tile.getColor());
    }


    @Test
    public void getId_correctInput_correctOutput() {
        assertEquals(0, tile.getId());
    }

    @Test
    public void setColor_correctInput_correctOutput() {
        tile.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, tile.getColor());
    }


}
