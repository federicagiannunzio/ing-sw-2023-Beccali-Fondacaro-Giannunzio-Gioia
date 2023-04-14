package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Tile;

public class Shelfie {
    Tile[][] structure;

    //creates a new Shelfie
    public Shelfie() {
        this.structure = new Tile[6][5];
    }

    //returns the current Shelfie
    public Tile[][] getStructure() {
        return structure;
    }

    //receives a Tile and returns the modified Shelfie
    public void addTile(Tile t, int j) {
        for (int i = 5; i >= 0; i--) {
            if (this.structure[i][j] == null) {
                this.structure[i][j] = t;
                //return structure;
            }
        }
    }
}





