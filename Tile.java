public class Tile {
    private color Color;
    private int id;


    //costruttore
    public Tile(color c, int id) {
        this.color = c;
        this.id = id;
    }

    public color getColor() {
        return Color;
    }

    public int getId() {
        return id;
    }

}
