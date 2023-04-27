package it.polimi.ingsw.model;
public class CornersGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * checks if there are four types of the  same type at the corners pf the shelfs
     *
     * @param s is the Shelf of the player that will be checked
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        if(structure[0][0] != null && structure[0][4] != null && structure[5][0] != null && structure[5][4] != null &&
                structure[0][0].getColor() == structure[0][4].getColor() && structure[0][4].getColor() == structure[5][0].getColor() && structure[5][0].getColor() == structure[5][4].getColor())
            return true;
        else
            return false;
    }
}
