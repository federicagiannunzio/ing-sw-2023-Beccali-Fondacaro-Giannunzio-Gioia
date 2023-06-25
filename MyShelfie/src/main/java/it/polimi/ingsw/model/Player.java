package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
        private final String username;
        private final int id;
        private int points;
        private boolean yellowChair;
        private boolean endGame;
        private boolean commonFlag1;
        private boolean commonFlag2;
        private Shelfie myShelfie;
        public ArrayList<Tile> myTiles;
        private personalGoal pGoal;


        /**
        * constructor of the player class
        *
        * @param username player's username
        * @param id player's id
        * @param first true if the player is the first, false otherwise
        *
        */
        public Player (String username, int id, boolean first) {
            this.username = username;
            this.id = id;
            this.myShelfie = new Shelfie();
            this.yellowChair = first;
            this.endGame = false;
            this.myTiles = new ArrayList<>();
            this.points = 0;
            this.commonFlag1 = true;
            this.commonFlag2 = true;
        }


        /**
        * Insert a tile in the shelfie
        *
        * @param k position (in the arrayList) of the tile that the player wants to insert
        * @param j the column in which the player wants to insert the tile
        *
        */
        public void insertTile(int k, int j) throws FullColumnException, emptyArrayException {

            if(myTiles.isEmpty()){
                throw new emptyArrayException();
            }

            myShelfie.addTile(myTiles.get(k), j);
            myTiles.remove(k);
        }

        public Tile popTile(int index){
            Tile t;
            t = this.myTiles.get(index);
            this.myTiles.remove(index);
            return t;
        }
        /**
        * set the goal associated to this player
        *
        * @param personalGoal goal of the player
        */

        public void setPersonalGoal(personalGoal personalGoal) {

            this.pGoal = personalGoal;
        }

        /**
         * if this player is the first to finish the game, set endGame at 1
         *
         */
        public void setEndGame() {

            this.endGame = true;
        }

        /**
         * sets the points to this player
         *
         * @param p number of points that has to be set
        * */
        public void setPoints(int p) {
            this.points = p;
        }

        /**
         * set if player is first player
         *
         *  @param isFirst boolean to say if the player is the first player
        * */
        public void setFirst(boolean isFirst) {
        this.yellowChair = isFirst;
        }

        public void setCommonFlag1(boolean flag){this.commonFlag1 = flag;}
        public void setCommonFlag2(boolean flag){this.commonFlag2 = flag;}

        public boolean getCommonFlag1(){return commonFlag1;}
        public boolean getCommonFlag2(){return commonFlag2;}


        /**
        * returns player's id
        *
        * @return id of the Player
        */
        public int getId() {
            return id;
        }

        /**
        * returns player's username
        *
        * @return username of the Player
        */
        public String getUsername() {

            return username;
        }

        /**
        * returns the total of the points
        *
        * @return points
        * */
        public int getPoints() {
            return points;
        }

        /**
        * returns true if the player ends the game
        *
        * @return endGame (boolean True or False)
        */
        public boolean isEndGame() {

            return endGame;
        }

        /**
        * return true if the player starts the game
        *
        * @return yellowChair (boolean True or False)
        * */
        public boolean isYellowChair() {
            return yellowChair;
        }

        /**
        * returns this Player's shelfie
        *
        * @return myShelfie
        */
        public Shelfie getMyShelfie() {
            return myShelfie;
        }

        /**
        * returns this Player's personal goal
        *
        * @return player's personal goal
        */
        public personalGoal getPersonalGoal() {
            return pGoal;
        }

        /**
        * returns this Player's picked tiles from the board
        *
        * @return player's picked tiles (in the arrayList)
        */
        public ArrayList<Tile> getMyTiles() {
            return myTiles;
        }

}



