package src.main.java.it.polimi.ingsw.model;

/**
 * Class squaresGoal
 * Two separate groups of 4 tiles same type that form a 2x2 square.
 * The tiles of the two groups must be of the same type.
 * @author andreafondacaro
 */
public class SquaresGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    private int yellow;
    private int white;
    private int green;
    private int blue;
    private int cyan;
    private int purple;

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        yellow = 2;
        white = 2;
        green = 2;
        blue = 2;
        cyan = 2;
        purple = 2;
        structure = s.getStructure();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                if(structure[i][j] != null && structure[i+1][j] != null &&  structure[i][j+1] != null && structure[i+1][j+1] != null &&
                        structure[i][j].getColor() == structure[i + 1][j].getColor() && structure[i][j].getColor() == structure[i][j + 1].getColor() && structure[i][j].getColor() == structure[i + 1][j + 1].getColor()){
                    if(structure[i][j].getColor() == Color.YELLOW){
                        yellow--;
                        if(yellow == 0)
                            return true;
                    }
                    else if(structure[i][j].getColor() == Color.WHITE){
                        white--;
                        if(white == 0)
                            return true;
                    }
                    else if(structure[i][j].getColor() == Color.GREEN){
                        green--;
                        if(green == 0)
                            return true;
                    }
                    else if(structure[i][j].getColor() == Color.BLUE){
                        blue--;
                        if(blue == 0)
                            return true;
                    }
                    else if(structure[i][j].getColor() == Color.CYAN){
                        cyan--;
                        if(cyan == 0)
                            return true;
                    }
                    else if(structure[i][j].getColor() == Color.PURPLE){
                        purple--;
                        if(purple == 0)
                            return true;
                    }
                    structure[i][j] = null;
                    structure[i+1][j+1] = null;
                    structure[i+1][j] = null;
                    structure[i][j+1] = null;
                }
            }
        }
        return false;
    }
}
