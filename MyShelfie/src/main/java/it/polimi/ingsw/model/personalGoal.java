package it.polimi.ingsw.model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class personalGoal {
    private final int id;
    private int tilesCount;
    private ArrayList<Color> achieved;
    private final Tile[][] body;

    /**
     * constructor of the class personalGoal
     *
     * @param id identifies uniquely the goal
     * */
    public personalGoal (int id) {
        this.id = id;
        this.tilesCount = 0;
        this.achieved = new ArrayList<>();
        this.body = new Tile[6][5];
    }

    /**
     * this method should be called at the beginning of the game
     * it sets the right combination of tiles for that goal
     *
     * @param id identifies uniquely the goal
     * */
    public void createTheGoal(int id) throws IOException, Exception {
        try {
            String data = new String(Files.readAllBytes(Paths.get("./goals1.json")));
            JSONArray jsonArray = new JSONArray(data);

            String str = jsonArray.get(id - 1).toString();
            JSONObject object1 = new JSONObject(str);

            JSONArray tiles = object1.getJSONArray("tiles");
            for (int i = 0; i < tiles.length(); i++) {

                String str2 = tiles.get(i).toString();
                JSONObject object2 = new JSONObject(str2);

                String color = object2.getString("color");
                int row = object2.getInt("row");
                int column = object2.getInt("column");

                switch (color) {
                    case "purple":
                        this.body[row][column] = new Tile(Color.PURPLE, 0);
                        break;
                    case "blue":
                        this.body[row][column] = new Tile(Color.BLUE, 0);
                        break;
                    case "green":
                        this.body[row][column] = new Tile(Color.GREEN, 0);
                        break;
                    case "white":
                        this.body[row][column] = new Tile(Color.WHITE, 0);
                        break;
                    case "yellow":
                        this.body[row][column] = new Tile(Color.YELLOW, 0);
                        break;
                    case "cyan":
                        this.body[row][column] = new Tile(Color.CYAN, 0);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("This ID is not valid");
            throw new Exception();
        }
    }

    /**
     * returns goal's id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * returns the number of tiles that the player already matched
     *
     * @return tilesCount
     */
    public int getTilesCount() {
        return tilesCount;
    }

    /**
     * returns the array that contains the colors that the player already matched
     *
     * @return achieved
     */
    public ArrayList<Color> getAchieved() {
        return achieved;
    }

    /**
     * return personal goal's shelf
     *
     * @return body
     * */
    public Tile[][] getBody() {
        return body;
    }


    //(not sure this method should stay here but rather in the controller)
    /**
     * it should be called at the end of a turn to update the total of that player's points
     *
     * @param pshelfie player's shelf
     * @return amount of points related to the personal goal
     */
    public int checkpoints(Shelfie pshelfie) {
        int newpoints = 0;

        for(int i=0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                if(body[i][j] != null && pshelfie.getCell(i,j) != null && body[i][j].getColor().equals(pshelfie.getCell(i,j).getColor()) && !achieved.contains(body[i][j].getColor())) {
                    this.tilesCount++;
                    achieved.add(body[i][j].getColor());
                }
            }
        }

            switch(this.tilesCount) {
             case 1:
               newpoints = 1;
               break;
             case 2:
               newpoints = 2;
               break;
             case 3:
               newpoints = 4;
               break;
             case 4:
               newpoints = 6;
               break;
             case 5:
               newpoints = 9;
               break;
             case 6:
               newpoints = 12;
               break;
             default:
               newpoints = 0;
               break;
            }
            return newpoints;
        }
}
