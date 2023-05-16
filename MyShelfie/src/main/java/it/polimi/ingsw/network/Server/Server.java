package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.network.Events.Event;

public class Server{
    private final GameController gameController;

    public Server(GameController gameController){
        this.gameController = gameController;
    }

    public void addClient(String user, ClientHandler clientHandler){
        if(!gameController.isInGame()){
            System.out.println("F");
            gameController.playerJoin(user, clientHandler);
        }else{
            gameController.getVirutalViewByUser(user).showLoginResult(true, false, null);
            clientHandler.disconnect();
        }
    }

    public void update(Event event){
        gameController.update(event);
    }
}
