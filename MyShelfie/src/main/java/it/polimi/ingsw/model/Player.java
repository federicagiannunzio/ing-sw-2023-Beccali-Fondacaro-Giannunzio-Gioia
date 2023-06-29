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
        * Constructor of the player class
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
        public void insertTile(int k, int j) throws emptyArrayException {

            if(myTiles.isEmpty()){
                throw new emptyArrayException();
            }

            myShelfie.addTile(myTiles.get(k), j);
            myTiles.remove(k);
        }

        /**
         * Removes a tile from the array that contains the tiles that the player has picked.
         *
         * @param index the index of the cell of the array
         * @return t the removed tile
         * */
        public Tile popTile(int index){
            Tile t;
            t = this.myTiles.get(index);
            this.myTiles.remove(index);
            return t;
        }

        /**
        * Set the goal associated to this player
        *
        * @param personalGoal goal of the player
        */
        public void setPersonalGoal(personalGoal personalGoal) {

            this.pGoal = personalGoal;
        }

        /**
         * If this player is the first to finish the game, set endGame at 1
         *
         */
        public void setEndGame() {

            this.endGame = true;
        }

        /**
         * Sets the points to this player
         *
         * @param p number of points that has to be set
        * */
        public void setPoints(int p) {
            this.points = p;
        }

        /**
         * Sets if player is first player
         *
         * @param isFirst boolean to say if the player is the first player
        * */
        public void setFirst(boolean isFirst) {
        this.yellowChair = isFirst;
        }

        /**
         * Sets {@link #commonFlag1} true if the player completes the first common goal
         *
         * @param isFirst boolean to say if the player is the first player
         * */
        public void setCommonFlag1(boolean flag){this.commonFlag1 = flag;}
        public void setCommonFlag2(boolean flag){this.commonFlag2 = flag;}

        public boolean getCommonFlag1(){return commonFlag1;}
        public boolean getCommonFlag2(){return commonFlag2;}
        public int getId() {
            return id;
        }
        public String getUsername() {

            return username;
        }
        public int getPoints() {
            return points;
        }
        public boolean isEndGame() {

            return endGame;
        }
        public boolean isYellowChair() {
            return yellowChair;
        }
        public Shelfie getMyShelfie() {
            return myShelfie;
        }
        public personalGoal getPersonalGoal() {
            return pGoal;
        }
        public ArrayList<Tile> getMyTiles() {
            return myTiles;
        }

}



