package it.polimi.ingsw.network.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {

    public ServerRMI() {
        try {
            RMIInterfaceImpl implementation = new RMIInterfaceImpl();
            try {
                Registry registry = LocateRegistry.createRegistry(5050);
                registry.bind("RMIInterface", implementation);
            } catch (RemoteException | AlreadyBoundException e) {
                System.out.println("Server: RMI setup error");
            }
        } catch (RemoteException e) {
            System.out.println("Server: remote exception error");
        }
    }
}
