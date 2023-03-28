public class Player {
    public class Player {
        private final static int dim = 2;
        private String username;
        private int id;
        private int points;
        private boolean yellowChair;
        private boolean endGame;
        private Shelfie myShelfie;
        private Tile myTiles[];


        public Player (String username, int id, Shelfie shelfie) {
            this.endGame = 0;
            this.yellowChair = 0;
            this.points = 0;

            this.username = username;
            this.id = id;
            this.myTiles = new Tile[dim];
            this.myShelfie = shelfie;

        }

        //getter methods
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

        //this method, if this player is the first to finish the game, set endGame at 1
        public void setEndGame() {
            this.endGame = 1;
        }

        //this method, if thi player is the first one, set yellowChair at 1
        public void setYellowChair() {
            this.yellowChair = 1;
        }


        //THIS METHOD PICK TILES FROM THE BOARD AND SAVE THEM IN THE PLAYER'S ARRAY
        //it returns an array
        public Tile pickTiles(Tile[]: at) {

        }

        //THIS METHOD INSERT THE PICKED TILES IN THE PLAYER'S SHELFIE
        public void insertTile(Tile[]: t) {

        }







    }

}
