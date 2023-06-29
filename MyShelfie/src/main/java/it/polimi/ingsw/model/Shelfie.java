package it.polimi.ingsw.model;


import java.io.Serializable;

/**
 * Class shelfie (object where to put the tiles chosen by a player)
 * @author piegioia
 */
public class Shelfie implements Serializable {
    private Tile[][] structure;

    /**
     * Creates a new shelfie
     */
    public Shelfie() {
        this.structure = new Tile[6][5];
    }
    public Tile[][] getStructure() {
        return structure;
    }
    public Tile getCell(int i, int j) {return structure[i][j];}

    /**
     * receives a Tile and modify the shelf
     * @param t tile selected from the board
     * @param j counter for columns
     */
    public void addTile(Tile t, int j){
        boolean placed = false;
            for (int i = 5; i >= 0 && !placed; i--) {
                if (this.structure[i][j] == null) {
                    this.structure[i][j] = t;
                    placed = true;
                }
            }
    }

    /**
     * Insert a tile in a specific position of the shelf
     *
     * @param i the row of the shelf in which the tile will be inserted
     * @param j the column of the shelf in which the tile will be inserted
     * @param t the tile that will be inserted
     */
    public void setShelfTile(int i, int j, Tile t){
        this.structure[i][j] = t;
    }

    /**
     * this method calculates the points that must be given to a player due to the groups of adjacent tiles in his shelf
     * @return are returned the number of points that the player gets for the adjacency of several tiles of the same color
     */
    public int[] adjacentItem(){
        int[] points = new int[5];
        int i, j, counter;
        boolean[][] visited = new boolean[6][5];
        for(i = 0; i < 6; i++){
            for(j = 0; j < 5;j++){
                visited[i][j] = false;
            }
        }
        for(i = 0; i < 6; i++){
            for(j = 0; j < 5;j++){
                if(structure[i][j] != null && !visited[i][j]){
                    counter = 1;
                    counter = DFS(structure, i, j, visited, counter);
                    if(counter == 2)
                        points[0]++;
                    if(counter == 3)
                        points[1]++;
                    else if(counter == 4)
                        points[2]++;
                    else if(counter == 5)
                        points[3]++;
                    else if(counter >= 6)
                        points[4]++;
                }
            }
        }
        return points;
    }
    /**
     * Recursive algorithm that checks the tiles around and calls back the function if they are equal increasing the counter
     * @param structure starting tile from which the adjacency are controlled
     * @param i counter for rows
     * @param j counter for columns
     * @param visited boolean that expresses whether a tile has already been checked or not
     * @param counter keeps track of the number of identical adjacent tiles
     * @return is returned the number of tiles
     */
    public int DFS(Tile structure[][], int i, int j, boolean visited[][], int counter){
        int[] row = new int[] {-1, 0, 0, 1};
        int[] col = new int[] {0, -1, 1, 0};
        visited[i][j] = true;
        for (int k = 0; k < 4; ++k) {
            if (isSafe(structure, i + row[k], j + col[k], visited) && structure[i][j].getColor() == structure[i + row[k]][j + col[k]].getColor()) {
                counter = DFS(structure, i + row[k], j + col[k], visited, counter) + 1;

            }
        }
        return counter;

    }

    /**
     * Boolean algorithm that returns true if the cell given is not null and has not been visited before
     * @param structure given tile of which we must check if it is null or if it has already been visited
     * @param i counter for rows
     * @param j counter for columns
     * @param visited boolean that expresses whether a tile has already been checked or not
     * @return is returned true if the cell given is not null and has not been visited before, otherwise false
     */
    public boolean isSafe(Tile structure[][], int i, int j, boolean visited[][]) {
        return (i >= 0) && (i < 6) && (j >= 0) && (j < 5) && (structure[i][j] != null && !visited[i][j]);
    }

    public boolean isComplete(){
        int i;
        for(i = 0; i < 5; i++){
            if(structure[0][i] == null)
                return false;
        }
        return true;
    }
}





