package it.polimi.ingsw.model;

import java.io.Serializable;

public class Tile implements Serializable {
    private Color color;
    private int id;

    /**
     * default constructor
     *
     * @param color is the color of the tile
     * @param id identifies the tile
     */
    public Tile(Color color, int id){
        this.color = color;
        this.id = id;
    }

    /**
     * return the color of this tile
     *
     * @return color
     * */
    public Color getColor() {
        return color;
    }

    //valutare di toglierlo perchè in realtà non serve al momento
    /**
     * returns the id of this tile
     *
     * @ return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the color of this tile
     * */
    public void setColor(Color color) {
        this.color = color;
    }
}