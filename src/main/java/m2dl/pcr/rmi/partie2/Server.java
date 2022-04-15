package m2dl.pcr.rmi.partie2;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements IMessenger {

    private ArrayList<Message> messages;
    private ArrayList<IEsclave> clients;


    public Server() {
        this.messages = new ArrayList<Message>();
        this.clients = new ArrayList<IEsclave>();
    }

    public ArrayList<Message> getMessages() {

        return this.messages;
    }


    @Override
    public void receive(Message message) throws RemoteException {
        this.messages.add(message);
        this.updateEsclaves(message);
    }

    public void updateEsclaves(Message message) throws RemoteException {
        for (IEsclave esclave : clients) {
            if (!message.getSender().equals(esclave.getName())) {
                esclave.update(message);
            }
        }
    }

    @Override
    public void register(IEsclave esclave) {
        this.clients.add(esclave);
    }

    public static void main(String[] args) {

        try {
            m2dl.pcr.rmi.partie2.Server obj = new m2dl.pcr.rmi.partie2.Server();
            IMessenger stub = (IMessenger) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("IMessenger", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}