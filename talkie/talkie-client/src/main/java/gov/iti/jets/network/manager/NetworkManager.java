package gov.iti.jets.network.manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NetworkManager {
    private static Registry registry;
    private static int port = 2400;

    static {
        try {
            registry = LocateRegistry.getRegistry(port);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static Registry getRegistry() {
        return registry;
    }
}
