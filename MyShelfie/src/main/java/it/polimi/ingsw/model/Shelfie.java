package src.main.java.it.polimi.ingsw.model;


/**
 * Class shelfie (object where to put the tiles chosen by a player)
 * @author piegioia
 */
public class Shelfie {
    private Tile[][] structure;

    /**
     * creates a new shelfie
     */
    public Shelfie() {
        this.structure = new Tile[6][5];
    }

    /**
     * this method is a getter of the current shelfie
     * @return is returned the current shelfie
     */
    public Tile[][] getStructure() {
        return structure;
    }

    /**
     * method used to select a certain cell of the shelfie
     * @param i counter for rows
     * @param j counter for columns
     * @return is returned the selected tile
     */
    public Tile getCell(int i, int j) {return structure[i][j];}

    /**
     * receives a Tile and modify the Shelfie
     * @param t tile selected from the board
     * @param j counter for columns
     * @throws fullColumnException is thrown when the column is full
     */
    public void addTile(Tile t, int j) throws fullColumnException{
        boolean placed = false;
            for (int i = 5; i >= 0 && !placed; i--) {
                if (this.structure[i][j] == null) {
                    this.structure[i][j] = t;
                    placed = true;
                }
            }
            if(!placed)
                throw new fullColumnException();
    }

    public void setShelfTile(int i, int j, Tile t){
        this.structure[i][j] = t;
    }

    /**
     * this method calculates the points that must be given to a player due to the groups of adjacent tiles in his shelfie
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
                    counter = 0;
                    counter = DFS(structure, i, j, visited, counter);
                    if(counter == 2)
                        points[5]++; //sarebbe 4?
                    if(counter == 3)
                        points[0]++;
                    else if(counter == 4)
                        points[1]++;
                    else if(counter == 5)
                        points[2]++;
                    else if(counter >= 6)
                        points[3]++;
                }
            }
        }
        return points;
    }
    /**
     * recursive algorithm that checks the tiles around and calls back the function if they are equal increasing the counter
     * @param structure starting tile from which the adjacencies are controlled
     * @param i counter for rows
     * @param j counter for columns
     * @param visited boolean that expresses whether a tile has already been checked or not
     * @param counter keeps track of the number of identical adjacent tiles
     * @return is returned the number of tiles
     */
    public int DFS(Tile structure[][], int i, int j, boolean visited[][], int counter){
        int[] row = new int[] {-1, 0, 0, 1};
        int[] col = new int[] {0, -1, 1, 0};

        for (int k = 0; k < 4; ++k) {
            if (isSafe(structure, i + row[k], j + col[k], visited) && structure[i][j].getColor() == structure[i + row[k]][j + col[k]].getColor()) {
                visited[i][j] = true;
                counter = DFS(structure, i + row[k], j + col[k], visited, counter) + 1;

            }
        }
        return counter;

    }

    /**
     * boolean algorithm that returns true if the cell given is not null and has not been visited before
     * @param structure given tile of which we must check if it is null or if it has already been visited
     * @param i counter for rows
     * @param j counter for columns
     * @param visited boolean that expresses whether a tile has already been checked or not
     * @return is returned true if the cell given is not null and has not been visited before, otherwise false
     */
    boolean isSafe(Tile structure[][], int i, int j, boolean visited[][]) {
        return (i >= 0) && (i < 6) && (j >= 0) && (j < 5) && (structure[i][j] != null && !visited[i][j]);
    }
}





