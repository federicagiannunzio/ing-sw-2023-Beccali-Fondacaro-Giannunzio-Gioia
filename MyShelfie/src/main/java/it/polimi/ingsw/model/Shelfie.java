import it.polimi.ingsw.model.Tile;

public class Shelfie {
    Tile[][] structure = new Tile[6][5];

    //creates a new Shelfie
    public Shelfie(Tile[][] structure) {
        this.structure = structure;
    }

    //returns the current Shelfie
    public Tile[][] getShelfie(){
        Tile[][] clone = new Tile[][];
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                clone[i][j] = structure[i][j];
            }
        }
        return clone;
    }

    //receives a Tile and returns the modified Shelfie
    public Tile[][] addTile(Tile t, int j){
        for(int i=5; i>=0; i--){
            if(structure[i][j] == null){
                structure[i][j] = t;
                return structure;
            }
        }
        return -1;
    }
}





