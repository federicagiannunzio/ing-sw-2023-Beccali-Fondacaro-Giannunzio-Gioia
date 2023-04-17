package it.polimi.ingsw.model;

public class Board {

    private int remainingTiles = 0;
    private Tile[][] table;
    private int size = 0;
    private Bag gameBag;


    //creates a new Board for the current game based on the number of players it's given
    public Board(int p, Bag bag){
        this.table = new Tile[p][p];
        this.size = p;
        this.gameBag = bag;

        placeTile(this.table, gameBag);
    }

    //returns the current Board
    public Tile[][] getBoard(){
        return table;
    }

    //returns the number of the remaining tiles on the board
    public int getRemainingTiles(){
        return remainingTiles;
    }

    //removes a Tile from the Board and returns it
    public Tile getTile(int i, int j){
        Tile t = table[i][j];
        table[i][j] = new Tile(Color.BLACK, 0);
        remainingTiles--;
        return t;
    }

    //checks if there are no more available Tiles, if so refreshes the board and returns it otherwise return null
    public Tile[][] refreshBoard(){

        boolean available = false;
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(checkTile(i, j)){
                    available = true;
                }
            }
        }

        if(!available){
            Tile[][] newBoards = new Tile[size][size];
            placeTile(newBoards, gameBag);
            return newBoards;
        }

        return null;
    }

    //receives a position in the board and checks if the Tile in that spot can be taken
    public boolean checkTile(int i, int j){

        if(getTile(i, j).getColor() == Color.BLACK){
            return false;
        }

        if(i + 1 < size && getTile(i+1, j).getColor() == Color.BLACK){
            return true;
        }
        if(j + 1 < size && getTile(i, j+1).getColor() == Color.BLACK){
            return true;
        }
        if(i - 1 > 0 && getTile(i-1, j).getColor() == Color.BLACK){
            return true;
        }
        if(j - 1 > 0 && getTile(i, j-1).getColor() == Color.BLACK){
            return true;
        }

        return false;
    }

    //sets a position in the table to the Tile given
    public void setTile(int i, int j, Color color){
        table[i][j] = new Tile(color, 0);
    }

    //sets a position in the table to a black Tile, used to mark an empty slot
    public void setBlackTile(int i, int j){
        table[i][j] = new Tile(Color.BLACK, 0);
    }

    //for each cell of the table picks a tile from the bag and place it in the cell
    //note: add numbers of player logic
    private void placeTile(Tile[][] board, Bag bag){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                //newBoards[i][j] = bag.pickTile();
            }
        }
    }

}
