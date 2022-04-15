package m2dl.pcr.rmi.partie2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMessenger extends Remote {
    ArrayList<Message> getMessages() throws RemoteException;

    void receive(Message message) throws RemoteException;

    void register(IEsclave esclave) throws RemoteException;

}
