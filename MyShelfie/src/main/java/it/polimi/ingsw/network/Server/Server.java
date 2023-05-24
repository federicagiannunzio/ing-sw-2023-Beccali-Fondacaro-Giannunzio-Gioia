package it.polimi.ingsw.network.Server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.Events.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server{
    private final GameController gameController;
    private final Map<ClientHandler, String> clientHandlerMap;
    private Object disconnectLock;

    public Server(GameController gameController){
        this.gameController = gameController;
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        this.disconnectLock = new Object();
    }

    public void addClient(String user, ClientHandler clientHandler){
        if(!gameController.isInGame()){
            clientHandlerMap.put(clientHandler, user);
            gameController.playerJoin(user, clientHandler);
        }else{
            gameController.getVirutalViewByUser(user).showLoginResult(true, false, null);
            clientHandler.disconnect();
        }
    }

    public void update(Event event){
        gameController.update(event);
    }

    public void disconnectClient(ClientHandler clientHandler){
        String user = clientHandlerMap.get(clientHandler);
        boolean flag = false;
        synchronized (disconnectLock){
            if(user != null){
                clientHandlerMap.remove(clientHandler);
                if(gameController.getTurnController() != null){
                    for(Player p : gameController.getPlayers())
                        if(user == p.getUsername())
                            flag = true;
                    if(!flag)
                        return;
                    gameController.getTurnController().removeVirtualViewMap(user);
                }
                gameController.removePlayer(user);
                gameController.virtualViewMap.remove(user);

            }
            //if(gameController.isInGame()){
                gameController.broadcastGenericMessage("The player " + user + " has disconnected from the game.");
                gameController.endGame();
                clientHandlerMap.clear();
            //}
        }

    }
}
