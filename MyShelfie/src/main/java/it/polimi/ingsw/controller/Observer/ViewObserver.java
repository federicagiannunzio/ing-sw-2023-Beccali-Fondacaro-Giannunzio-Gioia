package it.polimi.ingsw.controller.Observer;

public interface ViewObserver {

    void sendUsername(String username);

    void sendPlayersNumber(int playersNum);

    void sendFirstPlayerChosen(String usernameFirst);

    void sendTilesNumberChosen(int numberOfTiles);

    void sendTilePosition(int i, int j);

    void sendShelfieColumn(int column);

    void sendChoiceBeginTurn(int choice);

    void sendArrayTileIndex(int index);


}
