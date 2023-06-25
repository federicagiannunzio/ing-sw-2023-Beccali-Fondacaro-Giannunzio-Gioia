package it.polimi.ingsw.model;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Board implements Serializable {
    private int remainingTiles;
    private Tile[][] table;
    private Bag gameBag;


    /**
     * constructor of the class Bag
     *
     * @param bag
     */
    public Board(Bag bag) {
        this.table = new Tile[9][9];
        this.gameBag = bag;
    }

    /**
     * returns the current board
     *
     * @return table
     */
    public Tile[][] getBoard(){
        return table;
    }

    /**
     * returns the current number of tiles on the board
     *
     * @return remainingTiles
     */
    public int getRemainingTiles(){
        return remainingTiles;
    }

    /**
     * sets the number of tiles on the board (at the beginning of the game). The number of tiles on the board is based on the number of players
     *
     * @param n number of players
     */
    public void initializeRemainingTiles(int n) {
        switch (n) {
            case 2 -> this.remainingTiles = 29;
            case 3 -> this.remainingTiles = 37;
            case 4 -> this.remainingTiles = 45;
        }
    }

    /**
     * Returns the tile in a specific position of the board
     *
     * @param i row of the tile that the Player wants to get
     * @param j column of the tile that the Player wants to get
     */
    public Tile getTile(int i, int j) { return table[i][j];}

    /**
     * Takes a tile from the board, checking if it is available
     *
     * @param i row of the tile that the Player wants to get
     * @param j column of the tile that the Player wants to get
     */
    public Tile takeTileFromBoard(int i, int j){
        if(!checkTile(i,j)) {
            return null;
        }

        Tile t = table[i][j];
        table[i][j].setColor(Color.BLACK);
        remainingTiles--;
        return t;
    }

    /**
     * Fills the board. For each cell of the table picks a tile from the bag and place it in the cell
     *
     * @param n number of players - 2
     * @throws IOException if there's an error in the opening of the file
     */
    public void fillTheBoard(int n) throws IOException {
        initializeRemainingTiles(n+2);
        JSONArray available = openAvailableCellJson(n);
        for(int i = 0; i < available.length(); i++) {
            String position = available.get(i).toString();
            JSONObject pos = new JSONObject(position);
            int row = pos.getInt("r");
            int column = pos.getInt("c");

            table[row][column] = gameBag.pickTile();
        }
    }

    /**
     * Sets all the tiles of the board to black
     *
     *
     * */
    public void initializeBlackBoard(int n) throws IOException {
        initializeRemainingTiles(n+2);
        JSONArray available = openAvailableCellJson(n);
        for(int i = 0; i < available.length(); i++) {
            String position = available.get(i).toString();
            JSONObject pos = new JSONObject(position);
            int row = pos.getInt("r");
            int column = pos.getInt("c");

            setTile(row, column, Color.BLACK);
        }
    }

    /**
     * Opens cell's JSON file that contains the coordinates of the cells that have to be filled o the board.
     * The file contains an array, each cell of the array contains different available cells based on the number of players
     * @param index the index of the cell of the JSON array in the file that we want to access.
     * */
    public JSONArray openAvailableCellJson(int index) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/availableCells.json")));
        JSONArray jsonArray = new JSONArray(data);

        String str = jsonArray.get(index).toString();
        JSONObject object1 = new JSONObject(str);
        JSONArray available = object1.getJSONArray("available");
        return available;
    }

    /**
     * Checks if there are no more available tiles, if so refreshes the board and returns it otherwise return null
     *
     */
    public boolean refreshBoard() {
        boolean refresh = false;
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                if(table[i][j] != null) {
                    if (i + 1 < 9 && j + 1 < 9 && i - 1 > 0 && j - 1 > 0 && table[i+1][j]==null && table[i][j+1]==null && table[i-1][j]==null && table[i][j-1]==null)
                        refresh = true;
                    if(i + 1 < 9 && j + 1 < 9 && i - 1 > 0 && j - 1 > 0 && table[i + 1][j]!=null && table[i][j+1]!=null && table[i-1][j]!=null && table[i][j-1]!=null && table[i + 1][j].getColor() == Color.BLACK && table[i][j + 1].getColor() == Color.BLACK && table[i - 1][j].getColor() == Color.BLACK && table[i][j - 1].getColor() == Color.BLACK)
                        refresh = true;
                }
            }
        }

        if(refresh){
            for(int i = 0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    if(table[i][j]!= null && table[i][j].getColor() == Color.BLACK) {
                        table[i][j] = gameBag.pickTile();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * receives a position in the board and checks if the Tile in that spot can be taken
     *
     * @param i row of the position of the tile that will be checked
     * @param j column of the position of the tile that will be checked
     */
    public boolean checkTile(int i, int j){

        if(table[i][j] == null || table[i][j].getColor() == Color.BLACK){
            return false;
        }

        int free = 0;

        if(i + 1 < 9 && (table[i+1][j]==null || table[i+1][j].getColor() == Color.BLACK )){
            free++;
        }
        if(j + 1 < 9  && (table[i][j+1]==null || table[i][j+1].getColor()==Color.BLACK) ){
            free++;
        }
        if(i - 1 > 0 && (table[i-1][j]==null || table[i-1][j].getColor() == Color.BLACK)){
            free++;
        }
        if(j - 1 > 0 && (table[i][j-1]==null || table[i][j-1].getColor() == Color.BLACK)){
            free++;
        }

        if(free > 0 && free < 4){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Check if the tiles that the player has picked are available by checking if there are adjacent tiles
     *
     * @param row contains the rows of the tiles picked by the player
     * @param col contains the columns of the tiles picked by the player
     * */
    public boolean adjacentTile(int[] row, int[] col) {
        int size = row.length;
        int counter = 0;
        if (size == 1)
            return true;
        if (size == 2) {
            if ((row[0] == row[1] && (col[0] == (col[1] + 1) || (col[0] == (col[1] - 1))) || col[0] == col[1] && (row[0] == (row[1] + 1) || row[0] == (row[1] - 1))))
                return true;
            else
                return false;
        }
        if (size == 3) {
            if((row[0] == row[1] && (col[0] == (col[1] + 1) || (col[0] == (col[1] - 1))) || col[0] == col[1] && (row[0] == (row[1] + 1) || row[0] == (row[1] - 1))))
                counter ++;
            if((row[0] == row[2] && (col[0] == (col[2] + 1) || (col[0] == (col[2] - 1))) || col[0] == col[2] && (row[0] == (row[2] + 1) || row[0] == (row[2] - 1))))
                counter++;
            if((row[1] == row[2] && (col[1] == (col[2] + 1) || (col[1] == (col[2] - 1))) || col[1] == col[2] && (row[1] == (row[2] + 1) || row[1] == (row[2] - 1))))
                counter++;
            if(counter == 2)
                return true;
            else
                return false;
            }
        return false;
        }

    /**
     * sets a position in the table to the Tile given
     *
     * @param i row of the position of the tile that will be set
     * @param j column of the position of the tile that will be set
     * @param color of the tile that will be set
     * */
    public void setTile(int i, int j, Color color){
        table[i][j] = new Tile(color, 0);
    }

    /**
     * sets a position in the table to a black Tile, used to mark an empty slot
     *
     * @param i row of the position of the tile that will be set to black
     * @param j column of the position of the tile that will be set to black
     */
    public void setBlackTile(int i, int j){
        table[i][j] = new Tile(Color.BLACK, 0);
    }

}
