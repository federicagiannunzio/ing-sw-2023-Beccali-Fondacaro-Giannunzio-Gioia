
package it.polimi.ingsw.model;

public class TwoRowsGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];

    /**
     * checks if there are two columns made up of five different types of tiles
     *
     * @param s is the Shelf of the player that will be checked
     */
    public boolean commonGoalAlg(Shelfie s) {
        int i, j, k, counter = 0;
        boolean flag = true;
        structure = s.getStructure();
        for(i = 0; i < 6; i++){
            for(j = 0; j < 5 && flag; j++){
                if(structure[i][j] == null)
                    flag = false;
                for(k = j+1; k < 5 && flag; k++){
                    if(structure[i][k] == null)
                        flag = false;
                    if(flag && structure[i][j].getColor() == structure[i][k].getColor())
                        flag = false;

                }
            }
            if(flag)
                counter++;
            else
                flag = true;
        }
        if(counter == 2)
            return true;
        else
            return false;
    }
}
