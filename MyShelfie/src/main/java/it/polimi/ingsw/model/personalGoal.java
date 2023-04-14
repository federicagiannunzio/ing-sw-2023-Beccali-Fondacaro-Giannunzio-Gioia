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


    /*
    * constructor
    * */
    public personalGoal (int id) {
        this.id = id;
        this.tilesCount = 0;
        this.achieved = new ArrayList<>();
        this.body = new Tile[6][5];
    }

    /*
    *this method should only be called at the beginning of the game
    * it sets the right combination of tiles for that goal
    *
    * @param id is the id that identifies a specified goal
    * */
    public void createTheGoal(int id) throws IOException {

        String data = new String(Files.readAllBytes(Paths.get("./goals.json")));
        JSONArray jsonArray = new JSONArray(data);

        String str = jsonArray.get(id-1).toString();
        JSONObject object1 = new JSONObject(str);

        JSONArray tiles = object1.getJSONArray("tiles");
        for (int i=0; i < tiles.length(); i++) {

            String str2 = tiles.get(i).toString();
            JSONObject object2 = new JSONObject(str2);

            String color = object2.getString("color");
            int row = object2.getInt("row");
            int column = object2.getInt("column");

            switch (color) {
                case "purple" :
                    this.body[row][column].setColor(Color.PURPLE);
                    break;
                case "blue" :
                    this.body[row][column].setColor(Color.BLUE);
                    break;
                case "green" :
                    this.body[row][column].setColor(Color.GREEN);
                    break;
                case "white" :
                    this.body[row][column].setColor(Color.WHITE);
                    break;
                case "yellow" :
                    this.body[row][column].setColor(Color.YELLOW);
                    break;
                case "cyan" :
                    this.body[row][column].setColor(Color.CYAN);
                    break;
            }
        }
    }

    /*
    * returns goal's id
    *
    * */
    public int getId() {
        return id;
    }

    /*
    * return number of tiles that the player already matched
    * */
    public int getTilesCount() {
        return tilesCount;
    }

    /*
    * returns the array that contains the colors that the player already matched
    *
    * */
    public ArrayList<Color> getAchieved() {
        return achieved;
    }

    /*
    * return personal goal's shelf
    * */
    public Tile[][] getBody() {
        return body;
    }


    /*
    * (not sure this method should stay here but rather in the controller)
    * it should be called at the end of a turn to update the total of that player's points
    *
    * @param pshelfie player's shelf
    * @return amount of points related to the personal goal
    *
    *  */

    public int checkpoints(Shelfie pshelfie) {
        int newpoints = 0;

        for(int i=0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                if(body[i][j] != null && body[i][j].getColor().equals(pshelfie.getStructure()[i][j].getColor()) && !achieved.contains(body[i][j].getColor())) {
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
