public class Bag {
    Tile[] bag = new Tile[131];

    //generates a bag at the start of every game
    public Bag(){
        for(int i=0; i<22; i++){
            Tile bag[i] = new Tile(Colors.YELLOW, i);
        }
        for(int i=22; i<44; i++){
            Tile bag[i] = new Tile(Colors.WHITE, i);
        }
        for(int i=44; i<66; i++){
            Tile bag[i] = new Tile(Colors.GREEN, i);
        }
        for(int i=66; i<88; i++){
            Tile bag[i] = new Tile(Colors.CYAN, i);
        }
        for(int i=88; i<110; i++){
            Tile bag[i] = new Tile(Colors.PURPLE, i);
        }
        for(int i=110; i<132; i++){
            Tile bag[i] = new Tile(Colors.BLUE, i);
        }
    }

    //picks a Tile from the bag and removes it from the array
    public Tile pickTile(Tile[] bag){
        int counter = bag.length;

        //takes the last tile of the array (which has already been shuffled)
        Tile show = bag[counter];

        //decrease the lenght of the array
        counter--;
        return show;
    }

    //shuffles the array of tiles generated at the start of every game
    public void shuffle(){
        int counter = bag.length;

        //while there are elements in the array
        while (counter > 0){

            //pick a random index
            int index = Math.floor(Math.random() * counter);

            //decrease counter by 1
            counter--;

            //swap the last element with it
            int temp = bag[counter];
            bag[counter] = bag[index];
            bag[index] = temp;
        }
        return bag;
    }
}
