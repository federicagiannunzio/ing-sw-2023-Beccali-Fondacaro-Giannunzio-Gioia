public class CrossGoal implements CommonGoal{
    Tile[][] structure = new Tile[5][4];
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        for(int i = 1; i < 5; i++){
            for(int j = 1; j < 4; j++){
                if(structure[i][j].getColor() == structure[i+1][j+1].getColor() && structure[i][j].getColor() == structure[i-1][j-1].getColor() && structure[i][j].getColor() == structure[i+1][j-1].getColor() && structure[i][j].getColor() == structure[i-1][j+1].getColor())
                    return true;
            }
        }
        return false;
    }
}
