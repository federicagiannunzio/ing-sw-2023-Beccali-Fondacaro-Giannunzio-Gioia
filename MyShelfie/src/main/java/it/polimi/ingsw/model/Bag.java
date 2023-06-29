package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class bag (object that contains the tiles)
 * @author piegioia
 */
public class Bag implements Serializable {
    private ArrayList<Tile> bag;


    /**
     * generates a bag at the start of every game
     */
    public Bag(){
        this.bag = new ArrayList<>();
    }

    /**
     * picks a tile from the bag and removes it from the array
     * @return is returned the tile that has been picked from the bag
     */
    public Tile pickTile(){
        Tile curr;
        int counter = this.bag.size();

        //takes the last tile of the array (which has already been shuffled)
        curr = this.bag.get(counter-1);

        //removes the tile from the array
        this.bag.remove(counter-1);

        return curr;
    }

    /**
     * shuffles the array of tiles generated at the start of every game
     */
    public void shuffle() {
        int counter = this.bag.size();

        //while there are elements in the array
        while (counter > 0){

            //pick a random index
            int index = (int) Math.floor(Math.random() * counter);

            //decrease counter by 1
            counter--;

            //swap the last element with it
            Tile temp = bag.get(counter);
            Tile temp2 = bag.get(index);
            bag.set(counter,temp2);
            bag.set(index, temp);
        }
    }

    /**
     * This method should be called only at the beginning of every game,
     * it generates the array that represents the bag, which contains 22 tiles for each color
     */
    public void setBag() {
        for(int i=0; i<22; i++){
            Tile t = new Tile(Color.YELLOW, 0);
            this.bag.add(t);
        }
        for(int i=22; i<44; i++){
            Tile t = new Tile(Color.GREEN, 0);
            this.bag.add(t);
        }
        for(int i=44; i<66; i++){
            Tile t = new Tile(Color.BLUE, 0);
            this.bag.add(t);
        }
        for(int i=66; i<88; i++) {
            Tile t = new Tile(Color.CYAN, 0);
            this.bag.add(t);
        }
        for(int i=88; i<110; i++){
            Tile t = new Tile(Color.PURPLE, 0);
            this.bag.add(t);
        }
        for(int i=110; i<132; i++){
            Tile t = new Tile(Color.WHITE, 0);
            this.bag.add(t);
        }
    }

    public ArrayList<Tile> getBag() {
        return bag;
    }
}
