

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class Server {
    private ServerSocket serverSock;
    private Socket internalSock;

    private Hashtable<Integer, ClientHandler> clientsTable = new Hashtable<Integer, ClientHandler>();

    private class ClientHandler extends Thread {
        ObjectInputStream objIn;
        ObjectOutputStream objOut;

        public ClientHandler(Socket internalSock) {
            try {
                objIn = new ObjectInputStream(internalSock.getInputStream());
                objOut = new ObjectOutputStream(internalSock.getOutputStream());
                clientsTable.put(1, this);
                start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                Object clientRequest;
                try {
                    clientRequest = objIn.readObject();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public Server() {
        try {
            serverSock = new ServerSocket(5000);
            while (true) {
                internalSock = serverSock.accept();
                new ClientHandler(internalSock);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void sendToAll() {
        for (Entry<Integer, ClientHandler> client : clientsTable.entrySet()) {
            client.objOut.writeObject();
        }
    }

}
