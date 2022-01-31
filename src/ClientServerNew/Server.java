package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
// import java.util.Map;

public class Server {

    private ServerSocket serverSock;
    private Socket internalSock;

    private Hashtable<String, ClientHandler> clientsTable = new Hashtable<String, ClientHandler>();

    Server() {
        System.out.println("tic.tac.toe.Server.<init>()");
        try {
            serverSock = new ServerSocket(7001);
            while (true) {
                internalSock = serverSock.accept();
                new ClientHandler(internalSock).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private class ClientHandler extends Thread {

        Socket internalSockHandler;
        PrintWriter writeToClient;
        BufferedReader readFromClient;
        String clientID;
        String messageFromClient;
        String messageToClient;
        boolean isOn;

        public ClientHandler(Socket internalSocket) {
            internalSockHandler = internalSocket;
            isOn = true;
            try {

                readFromClient = new BufferedReader(new InputStreamReader(internalSockHandler.getInputStream()));
                writeToClient = new PrintWriter(internalSockHandler.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

        public String[] parseClientMessage(String clientMessage) {

            return clientMessage.split("\\.");

        }

        public void handleLoginRequest(String _clientID) {
            // database
            clientID = _clientID;
            clientsTable.put(clientID, this);
            messageToClient = new String("hello " + clientID);
            writeToClient.println(messageToClient);
        }

        public void handleSignupRequest() {
            // database
            messageToClient = new String("signup");
            writeToClient.println(messageToClient);
        }

        public void handleInvitaionRequest(String otherClientId) {
            if (clientsTable.get(otherClientId) != null) {
                messageToClient = "invitation from " + clientID;
                clientsTable.get(otherClientId).writeToClient.println(messageToClient);
            } else {
                writeToClient.println("not connected yet");
            }
        }

        public void closeConnection() {

            try {
                readFromClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                internalSockHandler.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writeToClient.close();
            isOn = false;
        }

        public void handleLogoutRequest() {
            clientsTable.remove(clientID);
            closeConnection();
        }

        public void handleClientInput() {
            System.out.println("received " + messageFromClient);
            String[] tokens = parseClientMessage(messageFromClient);
            if (tokens[0].equals(new String("login"))) {
                handleLoginRequest(tokens[1]);
            } else if (tokens[0].equals(new String("signup"))) {
                handleSignupRequest();
            } else if (tokens[0].equals(new String("invite"))) {
                handleInvitaionRequest(tokens[1]);
            } else {
                handleLogoutRequest();
            }
        }

        @Override
        public void run() {
            try {

                while (isOn) {
                    messageFromClient = readFromClient.readLine();
                    handleClientInput();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("thread closed");

        }
    }

}

// for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
// System.out.print(hashEntry.getKey() + " " + hashEntry.getValue());
// }
