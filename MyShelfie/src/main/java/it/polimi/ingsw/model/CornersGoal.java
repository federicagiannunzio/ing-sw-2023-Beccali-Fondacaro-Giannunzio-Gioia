package it.polimi.ingsw.model;
public class CornersGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        if(structure[0][0] != null && structure[0][4] != null && structure[5][0] != null && structure[5][4] != null &&
                structure[0][0].getColor() == structure[0][4].getColor() && structure[0][4].getColor() == structure[5][0].getColor() && structure[5][0].getColor() == structure[5][4].getColor())
            return true;
        else
            return false;
    }
}
