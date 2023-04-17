package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Player {
        private final String username;
        private final int id;
        private int points;
        private boolean yellowChair;
        private boolean endGame;
        private Shelfie myShelfie;
        private ArrayList<Tile> myTiles;
        private personalGoal pGoal;


        /*
        * constructor
        *
        * @param username player's username
        * @param id player's id
        * @param first true if the player is the first, false otherwise
        *
        * */
        public Player (String username, int id, boolean first) {
            this.username = username;
            this.id = id;
            this.myShelfie = new Shelfie();
            this.yellowChair = first;
            this.endGame = false;
            this.myTiles = new ArrayList<>();
            this.points = 0;
        }

        /*pick tiles from the board and save them in an arraylist
        *
        * @param num the number of tiles that the player wants to pick
        *
        * */
        public void pickTiles(int num) {
            Tile currTile;

            for(int i = 0; i<num; i++) {
                //currTile = come faccio a chiamare il metodo della board?
                //myTiles.add(currTile);
            }

        }


        /*
        * Insert a tile in the shelfie
        *
        * @param k position (in the arrayList) of the tile that the player wants to insert
        * @param j the column in which the player wants to insert the tile
        *
        *  */
        public void insertTile(int k, int j) throws fullColumnException, emptyArrayException {

            if(myTiles.isEmpty()){
                throw new emptyArrayException();
            }

            myShelfie.addTile(myTiles.get(k), j);
            myTiles.remove(k);
        }

        /*
        *set the goal associated to this player
        *
        * @param personalGoal goal of the player
        *
        *  */

        public void setPersonalGoal(personalGoal personalGoal) {

            this.pGoal = personalGoal;
        }

        /*
         * if this player is the first to finish the game, set endGame at 1
         *
         * */
        public void setEndGame() {

            this.endGame = true;
        }

        /**/
        public void setPoints(int p) {
            this.points = p;
        }

        /*
        * (not sure that this method should exist)
        * it returns the tile that the player put in that position
        *
        * @param row is the row that the player wants to check
        * @param col is the column that the player wants to check
        *
        * @return the tile
        * */

        //public Tile getTileByPosition(int row, int col)  {
        //    Tile tile;
            //should call a hypothetical method in "Shelfie" class tha return the tile in that position
        //    return tile;
        //}


        /*
        * returns player's id
        *
        * @returns id
        *
        * */
        public int getId() {

            return id;
        }

        /*
        * returns player's username
        *
        * @return player's username
        *
        * */
        public String getUsername() {

            return username;
        }

        /*
        * returns the total of the points
        *
        * @return points
        *
        * */
        public int getPoints() {

            return points;
        }


        /*
        * returns true if the player ends the game
        *
        * @return true or false
        */
        public boolean isEndGame() {

            return endGame;
        }

        /*
        * return true if the player starts the game
        *
        * @return true or false
        * */
        public boolean isYellowChair() {

            return yellowChair;
        }

        /*
        *
        * @return player's shelf
        * */
        public Shelfie getMyShelfie() {

            return myShelfie;
        }

        /*
        * @return player's personal goal
        *
        * */
        public personalGoal getPersonalGoal() {
            return pGoal;
        }

        /*
        * @return player's picked tiles (in the arrayList)
        * */
        public ArrayList<Tile> getMyTiles() {
            return myTiles;
        }
}



