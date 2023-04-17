package it.polimi.ingsw.model;


public class Shelfie {
    private Tile[][] structure;

    //creates a new Shelfie
    public Shelfie() {
        this.structure = new Tile[6][5];
    }

    //returns the current Shelfie
    public Tile[][] getStructure() {
        return structure;
    }

    public Tile getCell(int i, int j) {return structure[i][j];}

    //receives a Tile and modify the Shelfie
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

    //recursive algorithm that checks the tiles around and calls back the function if they are equal increasing the counter
    public int DFS(Tile structure[][], int i, int j, boolean visited[][], int counter){
        int[] row = new int[] {-1, 0, 0, 1};
        int[] col = new int[] {0, -1, 1, 0};
        counter++;
        visited[i][j] = true;
        for (int k = 0; k < 4; ++k)
            if (isSafe(structure, i + row[k], j + col[k], visited) && structure[i][j].getColor() == structure[i + row[k]][j + col[k]].getColor())
                DFS(structure, i + row[k], j + col[k], visited, counter);
        return counter;
    }

    //boolean algorithm that returns true if the cell given is not null and has not been visited before
    boolean isSafe(Tile structure[][], int i, int j, boolean visited[][]) {
        return (i >= 0) && (i < 6) && (j >= 0) && (j < 5) && (structure[i][j] != null && !visited[i][j]);
    }
}





