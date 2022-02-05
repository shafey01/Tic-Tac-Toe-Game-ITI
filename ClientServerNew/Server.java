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

        private Socket internalSockHandler;
        private PrintWriter writeToClient;
        private BufferedReader readFromClient;
        private String clientID;
        private String messageFromClient;
        private String messageToClient;
        // private boolean isOn;
        ///////////////////

        public ClientHandler(Socket internalSocket) {
            internalSockHandler = internalSocket;
            // isOn = true;
            try {

                readFromClient = new BufferedReader(new InputStreamReader(internalSockHandler.getInputStream()));
                writeToClient = new PrintWriter(internalSockHandler.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

        /////////////////////// private
        private String[] parseClientMessage(String clientMessage) {

            return clientMessage.split("\\.");

        }

        private void handleLoginRequest(String _clientID) {
            // database
            clientID = _clientID;
            clientsTable.put(clientID, this);
            messageToClient = new String("0");
            writeToClient.println(messageToClient);
        }

        private void handleSignupRequest() {
            // database
            messageToClient = new String("1");
            writeToClient.println(messageToClient);
        }

        private void handleInvitaionRequest(String otherClientID) {
            ClientHandler otherClient = clientsTable.get(otherClientID);
            if (otherClient != null) {
                messageToClient = new String("invite." + clientID);
                otherClient.writeToClient.println(messageToClient);
                writeToClient.println(new String("1"));
            }

            else {
                writeToClient.println("0");
            }
        }

        private void handleReplyRequest(String otherClientID, String isAccepted) {
            ClientHandler otherClient = clientsTable.get(otherClientID);
            if (otherClient != null) {
                messageToClient = new String("reply." + clientID + "." + isAccepted);
                otherClient.writeToClient.println(messageToClient);
            }

            else {
                writeToClient.println(new String("0"));
            }
        }

        private void closeConnection() {

            try {
                readFromClient.close();

                internalSockHandler.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            writeToClient.close();
            // isOn = false;
        }

        private void handleLogoutRequest() {
            clientsTable.remove(clientID);
            // closeConnection();
        }

        private void handleQuitRequest() {
            closeConnection();
        }

        //////////////////////////////////////// private
        private void handleClientRequest() {
            System.out.println("received " + messageFromClient);
            String[] tokens = parseClientMessage(messageFromClient);
            if (tokens[0].equals(new String("login"))) {
                handleLoginRequest(tokens[1]);
            }

            else if (tokens[0].equals(new String("signup"))) {
                handleSignupRequest();
            }

            else if (tokens[0].equals(new String("invite"))) {
                handleInvitaionRequest(tokens[1]);
            }
            ///////////////////////////////
            else if (tokens[0].equals(new String("reply"))) {
                handleReplyRequest(tokens[1], tokens[2]);
            }
            ///////////////////////////////////
            else if (tokens[0].equals(new String("logout"))) {
                handleLogoutRequest();
            }

            else if (tokens[0].equals(new String("quit"))) {
                handleQuitRequest();
            }
        }

        @Override
        public void run() {
            try {

                while (true) {
                    messageFromClient = readFromClient.readLine();
                    handleClientRequest();
                }
            } catch (IOException e) {

                // e.printStackTrace();
                System.out.println("connection to client closed");
            }

            System.out.println("thread closed");

        }
    }

}

// for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
// System.out.print(hashEntry.getKey() + " " + hashEntry.getValue());
// }
