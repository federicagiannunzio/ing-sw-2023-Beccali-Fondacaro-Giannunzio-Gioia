public class Board {
    private int remainingTiles = 0;
    boolean bool;
    Tile[][] table;
    //creates a new Board for the current game based on the number of players it's given
    public Board(int p){
    }

    //returns the current Board
    public Tile[][] getBoard(){
        return table;
    }

    //checks if there are no more available Tiles, if so refreshes the board and returns it
    public Tile[][] refreshBoard(){
        return table;
    }
    //receives a position in the board and checks if the Tile in that spot can be taken
    public Boolean checkTile(int i, int j){
        return bool;
    }

    //removes a Tile from the Board and returns it
    public Tile getTile(int i, int j){
        Tile t = table[i][j];
        return t;
    }

    //sets a position in the table to the Tile given
    public void setTile(int i, int j){}

    //sets a position in the table to a black Tile, used to mark an empty slot
    public void setBlackTile(int i, int j){}

    //returns the number of the remaining tiles on the board
    public int getRemainingTiles(){
        return remainingTiles;
    }
}
