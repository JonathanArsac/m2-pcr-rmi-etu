package m2dl.pcr.rmi.partie2;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Client implements IEsclave {

    private String name;

    private Client(String name) {
        if (null == name || "".equals(name)) {
            this.name = "Robot";
        } else {
            this.name = name;
        }
    }

    public static void main(String[] args) {


        String host = (args.length < 1) ? null : args[0];
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            m2dl.pcr.rmi.partie2.Client client = new m2dl.pcr.rmi.partie2.Client(name);
            IEsclave esclave = (IEsclave) UnicastRemoteObject.exportObject(client, 0);

            Registry registry = LocateRegistry.getRegistry(host);
            IMessenger messenger = (IMessenger) registry.lookup("IMessenger");

            messenger.register(esclave);

            while (true) {
                scanner = new Scanner(System.in);

                //  prompt for the user's name
                System.out.print("~ ");

                // get their input as a String
                String text = scanner.nextLine();
                if (Objects.equals(text, "fin")) {
                    ArrayList<Message> responses = messenger.getMessages();
                    for (Message message : responses) {
                        System.out.println("response: " + message.toString());
                    }
                }
                messenger.receive(new Message(client.name, text));
            }


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) throws RemoteException {
        System.err.println(message.toString());
    }

    @Override
    public String getName() throws RemoteException{
        return this.name;
    }
}
