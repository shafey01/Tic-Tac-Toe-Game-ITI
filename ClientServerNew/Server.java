package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
// import java.util.Map;

import Game.GameWithComputer;
import Game.Move;

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
        private GameWithComputer gameWithComputer;

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

        private String[] parseClientMessage(String clientMessage) {

            return clientMessage.split("\\.");
        }

        // private Move stringToMove()

        private void handleLoginRequest(String _clientID) {
            // database
            clientID = _clientID;
            clientsTable.put(clientID, this);
            String messageToClient = new String("1");
            writeToClient.println(messageToClient);
        }

        private void handleSignupRequest() {
            // database
            String messageToClient = new String("1");
            writeToClient.println(messageToClient);
        }

        private void handleInvitaionRequest(String otherClientID) {
            ClientHandler otherClient = clientsTable.get(otherClientID);
            if (otherClient != null) {
                String messageToClient = new String("invite." + clientID);
                otherClient.writeToClient.println(messageToClient);
                // writeToClient.println(new String("1"));
            }
        }

        private void handleReplyRequest(String otherClientID, String isAccepted) {
            ClientHandler otherClient = clientsTable.get(otherClientID);
            if (otherClient != null) {
                String messageToClient = new String("reply." + clientID + "." + isAccepted);
                clientsTable.get(otherClientID).writeToClient.println(messageToClient);
            }
        }

        private void handleAIgameRequest(String computerStarts) {

            if (computerStarts.equals(new String("1"))) {
                gameWithComputer = new GameWithComputer(true);
                String messageToClient = new String("AIgame." + gameWithComputer.getComputerMove().toString());
                writeToClient.println(messageToClient);
            } else {
                gameWithComputer = new GameWithComputer(false);
            }
        }

        private void handleAIgameMoveRequest(String rowIndex, String columnIndex) {
            String messageToClient;
            int gameStatus = gameWithComputer.playMove(Move.stringToMove(rowIndex, columnIndex));
            if (gameStatus == 3) {
                messageToClient = new String("AIgame." + gameWithComputer.getComputerMove().toString());
            }

            else if (gameStatus == 0) {
                messageToClient = new String("AIover.0");
            }

            else if (gameStatus == 1) {
                messageToClient = new String("AIover.1");
            }

            else {
                messageToClient = new String("AIover.-1");
            }
            writeToClient.println(messageToClient);
        }

        private void handleLogoutRequest() {
            clientsTable.remove(clientID);
            closeConnection();
        }

        private void handleQuitRequest() {
            closeConnection();
        }

        private void closeConnection() {
            writeToClient.close();
            try {
                readFromClient.close();
                internalSockHandler.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // isOn = false;
        }

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

            else if (tokens[0].equals(new String("reply"))) {
                handleReplyRequest(tokens[1], tokens[2]);
            }

            else if (tokens[0].equals(new String("AIrequest"))) {
                handleAIgameRequest(tokens[1]);
            }

            else if (tokens[0].equals(new String("AIgame"))) {
                handleAIgameMoveRequest(tokens[1], tokens[2]);
            }

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
