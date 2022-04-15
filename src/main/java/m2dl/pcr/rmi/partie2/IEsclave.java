package m2dl.pcr.rmi.partie2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEsclave extends Remote {
    void update(Message message) throws RemoteException;
    String getName() throws RemoteException;
}