package it.polimi.ingsw.model;
public class DiagonalGoal implements CommonGoal {
    Tile[][] structure = new Tile[5][4];
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        if(structure[0][0].getColor() == structure[1][1].getColor() && structure[0][0].getColor() == structure[2][2].getColor() && structure[0][0].getColor() == structure[3][3].getColor() && structure[0][0].getColor() == structure[4][4].getColor()){
            return true;
        }
        if(structure[1][0].getColor() == structure[2][1].getColor() && structure[1][0].getColor() == structure[3][2].getColor() && structure[1][0].getColor() == structure[4][3].getColor() && structure[1][0].getColor() == structure[5][4].getColor()){
            return true;
        }
        if(structure[0][4].getColor() == structure[1][3].getColor() && structure[0][4].getColor() == structure[2][2].getColor() && structure[0][4].getColor() == structure[3][1].getColor() && structure[0][4].getColor() == structure[4][0].getColor()){
            return true;
        }
        if(structure[1][4].getColor() == structure[2][3].getColor() && structure[1][4].getColor() == structure[3][2].getColor() && structure[1][4].getColor() == structure[4][1].getColor() && structure[1][4].getColor() == structure[5][0].getColor()){
            return true;
        }
        return false;
    }
}
