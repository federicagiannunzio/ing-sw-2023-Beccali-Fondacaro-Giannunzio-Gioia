package it.polimi.ingsw.model;

public class Tile{

    private Color color;
    private int id;

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

    public void setColor(Color color) {
        this.color = color;
    }
}