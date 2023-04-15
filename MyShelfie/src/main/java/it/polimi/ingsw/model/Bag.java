package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Tile;

import java.util.ArrayList;

public class Bag {
    ArrayList<Tile> bag = new ArrayList<>();

    //generates a bag at the start of every game
    public Bag(){
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
            this.bag.add(t);;
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

    //picks a Tile from the bag and removes it from the array
    public Tile pickTile(Tile[] bag){
        Tile curr;
        int counter = this.bag.size();

        //takes the last tile of the array (which has already been shuffled)
        curr = this.bag.get(counter-1);

        //removes the tile from the array
        this.bag.remove(counter-1);

        return curr;
    }

    //shuffles the array of tiles generated at the start of every game
    public void shuffle() {
        int counter = this.bag.size();

        //while there are elements in the array
        while (counter > 0){

            //pick a random index
            int index = (int) Math.floor(Math.random() * counter);

            //decrease counter by 1
            counter--;

            //swap the last element with it
            Tile temp = bag.get(counter-1);
            Tile temp2 = bag.get(index);
            bag.set(counter-1,temp2);
            bag.set(index, temp);
        }
    }
}
