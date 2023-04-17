package it.polimi.ingsw.model;
public class CrossGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        for(int i = 1; i < 5; i++){
            for(int j = 1; j < 4; j++){
                if(structure[i][j] != null && structure[i+1][j+1] != null && structure[i+1][j-1] != null && structure[i-1][j+1] != null &&
                        structure[i][j].getColor() == structure[i+1][j+1].getColor() && structure[i][j].getColor() == structure[i-1][j-1].getColor() && structure[i][j].getColor() == structure[i+1][j-1].getColor() && structure[i][j].getColor() == structure[i-1][j+1].getColor())
                    return true;
            }
        }
        return false;
    }
}
