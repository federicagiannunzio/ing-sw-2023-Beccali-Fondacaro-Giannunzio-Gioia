package it.polimi.ingsw.model;

import java.io.Serializable;

public class Tile implements Serializable {
    private Color color;
    private final int id;

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

    public Color getColor() {
        return color;
    }
    public int getId() {
        return id;
    }

    /**
     * Sets the color of this tile
     * */
    public void setColor(Color color) {
        this.color = color;
    }
}