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
     * sets the number of tiles on the board (at the beginning of the game) base don the number of players
     *
     * @param n number of players
     */
    public void inizializeRemainingTiles(int n) {
        switch (n) {
            case 2 -> this.remainingTiles = 29;
            case 3 -> this.remainingTiles = 37;
            case 4 -> this.remainingTiles = 45;
        }
    }

    /**
     * returns the tile in a specific position of the board
     *
     * @param i row of the tile that the Player wants to get
     * @param j column of the tile that the Player wants to get
     */
    public Tile getTile(int i, int j) { return table[i][j];}

    /**
     * check if the tile that the players wants to pick from the table is available,
     *
     * @param i row of the tile that the Player wants to get
     * @param j column of the tile that the Player wants to get
     * @throws  notAvailableTileException
     */
    public Tile takeTileFromBoard(int i, int j){
        if(!checkTile(i,j)) {
            try {
                throw new notAvailableTileException();
            } catch (notAvailableTileException e) {
                throw new RuntimeException(e);
            }
        }
        Tile t = table[i][j];
        table[i][j].setColor(Color.BLACK);
        remainingTiles--;
        return t;
    }

    //note: add numbers of player logic
    /**
     * for each cell of the table picks a tile from the bag and place it in the cell
     *
     * @param n index ...
     * @throws IOException if there's an error in the opening of the file
     */
    public void fillTheBoard(int n) throws IOException {
        JSONArray available = openAvailableCellJson(n);
        for(int i = 0; i < available.length(); i++) {

            String position = available.get(i).toString();
            JSONObject pos = new JSONObject(position);
            int row = pos.getInt("r");
            int column = pos.getInt("c");

            table[row][column] = gameBag.pickTile();
        }
    }

    //maybe we should move this method in the controller
    /**
     *
     * */
    public JSONArray openAvailableCellJson(int index) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("MyShelfie/src/main/resources/availableCells.json")));
        JSONArray jsonArray = new JSONArray(data);

        String str = jsonArray.get(index).toString();
        JSONObject object1 = new JSONObject(str);
        JSONArray available = object1.getJSONArray("available");
        return available;
    }

    /**
     * checks if there are no more available Tiles, if so refreshes the board and returns it otherwise return null
     *
     */
    public void refreshBoard() {
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
                for(int j=0; i<9; j++) {
                    if(table[i][j].getColor() == Color.BLACK) {
                        table[i][j] = gameBag.pickTile();
                    }
                }
            }
        }
    }

    /**
     * receives a position in the board and checks if the Tile in that spot can be taken
     *
     * @param i row of the position of the tile that will be checked
     * @param j column of the position of the tile that will be checked
     */
    public boolean checkTile(int i, int j){

        if(table[i][j].getColor() == Color.BLACK){
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
