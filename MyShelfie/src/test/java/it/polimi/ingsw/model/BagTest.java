package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BagTest {
    private Bag b;

    @Before
    public void setUp() {
        b = new Bag();
    }

    @After
    public void tearDown() {
        b = null;
    }

    @Test
    public void bagGetterAndSetter_rightDim() {
        b.setBag();
        assertEquals(132, b.getBag().size());
    }

    @Test
    public void bagGetterAndSetter_rightBuilding() {
        b.setBag();
        for(int i=0; i<22; i++)
            assertEquals(Color.YELLOW, b.getBag().get(i).getColor());
        for(int i=22; i<44; i++)
            assertEquals(Color.GREEN, b.getBag().get(i).getColor());
        for(int i=44; i<66; i++)
            assertEquals(Color.BLUE, b.getBag().get(i).getColor());
        for(int i=66; i<88; i++)
            assertEquals(Color.CYAN, b.getBag().get(i).getColor());
        for(int i=88; i<110; i++)
            assertEquals(Color.PURPLE, b.getBag().get(i).getColor());
        for(int i=110; i<132; i++)
            assertEquals(Color.WHITE, b.getBag().get(i).getColor());
    }

    @Test
    public void pickTile_oneTilePicked_decreaseDimByOne() {
        b.setBag();
        Tile t = b.pickTile();
        assertEquals(131, b.getBag().size());
    }

    @Test
    public void pickTile_lastTilePicked_correctOutput() {
        b.setBag();
        ArrayList<Tile> a = b.getBag();
        a.remove(131);
        Tile t = b.getBag().get(131);

        assertEquals(t, b.pickTile());
        assertEquals(a, b.getBag());
    }


    @Test
    public void shuffle_orderedArray_shuffledArray() {
        ArrayList<Tile> a = b.getBag();
        b.shuffle();
        assertNotEquals(a, b.getBag());
    }

}
