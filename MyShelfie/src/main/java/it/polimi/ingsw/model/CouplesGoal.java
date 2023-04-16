public class CouplesGoal implements CommonGoal{
    Tile[][] structure = new Tile[5][4];
    private int counter;
    public boolean commonGoalAlg(Shelfie s) {
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[1] + arrayPoints[2] + arrayPoints[3] + arrayPoints[0] + arrayPoints[5] >= 6)
            return true;
        else
            return false;
    }
}
