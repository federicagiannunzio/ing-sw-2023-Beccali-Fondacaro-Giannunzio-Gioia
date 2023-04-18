package it.polimi.ingsw.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Board {
    private int remainingTiles;
    private Tile[][] table;
    private Bag gameBag;


    //creates a new Board for the current game
    public Board(Bag bag) {
        this.table = new Tile[9][9];
        this.gameBag = bag;
    }

    //returns the current Board
    public Tile[][] getBoard(){
        return table;
    }

    //returns the number of the remaining tiles on the board
    public int getRemainingTiles(){
        return remainingTiles;
    }

    public void inizializeRemainingTiles(int n) {
        switch (n) {
            case 2 -> this.remainingTiles = 29;
            case 3 -> this.remainingTiles = 37;
            case 4 -> this.remainingTiles = 45;
        }
    }

    public Tile getTile(int i, int j) { return table[i][j];}

    //removes a Tile from the Board and returns it
    public Tile takeTileFromBoard(int i, int j) throws notAvailableTileException{
        if(!checkTile(i,j)) {
            throw new notAvailableTileException();
        }
        Tile t = table[i][j];
        table[i][j] = new Tile(Color.BLACK, 0);
        remainingTiles--;
        return t;
    }

    //for each cell of the table picks a tile from the bag and place it in the cell
    //note: add numbers of player logic
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
    public JSONArray openAvailableCellJson(int index) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("./availableCells.json")));
        JSONArray jsonArray = new JSONArray(data);

        String str = jsonArray.get(index).toString();
        JSONObject object1 = new JSONObject(str);
        JSONArray available = object1.getJSONArray("available");
        return available;
    }

    //checks if there are no more available Tiles, if so refreshes the board and returns it otherwise return null
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

    //da sistemare
    //receives a position in the board and checks if the Tile in that spot can be taken
    public boolean checkTile(int i, int j){

        if(table[i][j].getColor() == Color.BLACK){
            return false;
        }

        if(i + 1 < 9 && (table[i+1][j]==null || table[i+1][j].getColor() == Color.BLACK )){
            return true;
        }
        if(j + 1 < 9  && (table[i][j+1]==null || table[i][j+1].getColor()==Color.BLACK) ){
            return true;
        }
        if(i - 1 > 0 && (table[i-1][j]==null || table[i-1][j].getColor() == Color.BLACK)){
            return true;
        }
        if(j - 1 > 0 && (table[i][j-1]==null || table[i][j-1].getColor() == Color.BLACK)){
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

}
