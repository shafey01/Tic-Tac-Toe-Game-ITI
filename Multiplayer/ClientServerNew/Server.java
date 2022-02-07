package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

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
        private GameWithComputer gameWithComputer;
        private MultiplayerGame multiplayerGame;
        private int moveType;
        private String multiClientID;

        public ClientHandler(Socket internalSocket) {
            internalSockHandler = internalSocket;
            // isOn = true;
            try {

                readFromClient = new BufferedReader(new InputStreamReader(internalSockHandler.getInputStream()));
                writeToClient = new PrintWriter(internalSockHandler.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();

            }
            moveType = 1;
        }

        private String[] parseClientMessage(String clientMessage) {

            return clientMessage.split("\\.");
        }

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

                String messageToOtherClient = new String("invite." + clientID);
                otherClient.writeToClient.println(messageToOtherClient);

            }
        }

        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        private void handleReplyRequest(String otherClientID) {

            ClientHandler otherClient = clientsTable.get(otherClientID);
            if (otherClient != null) {
                this.multiplayerGame = new MultiplayerGame();
                this.moveType = 2;
                this.multiClientID = otherClientID;
                otherClient.multiClientID = clientID;
                otherClient.multiplayerGame = this.multiplayerGame;
                String messageToOtherClient = new String("multistart." + clientID);
                otherClient.writeToClient.println(messageToOtherClient);
            }

        }
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        private void handleAIgameRequest(String computerStarts) {

            if (computerStarts.equals(new String("1"))) {
                gameWithComputer = new GameWithComputer(true);
                String messageToClient = new String("game." + gameWithComputer.getComputerMove().toString());
                writeToClient.println(messageToClient);
            } else {
                gameWithComputer = new GameWithComputer(false);
            }
        }

        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$removeAI
        private void handleAIgameMoveRequest(String rowIndex, String columnIndex) {
            String messageToClient;
            int gameStatus = gameWithComputer.playMove(Move.stringToMove(rowIndex, columnIndex));
            if (gameStatus == 3) {
                gameStatus = gameWithComputer.AIplayMove();
                messageToClient = new String("game." + gameWithComputer.getComputerMove().toString());
                writeToClient.println(messageToClient);
            }

            if (gameStatus == 0) {
                messageToClient = new String("over.0");
                writeToClient.println(messageToClient);
            }

            else if (gameStatus == 1) {
                messageToClient = new String("over.1");
                writeToClient.println(messageToClient);
            }

            else if (gameStatus == -1) {
                messageToClient = new String("over.-1");
                writeToClient.println(messageToClient);
            }

        }
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        private void handleMultigameMoveRequest(String rowIndex, String columnIndex) {
            Move move = Move.stringToMove(rowIndex, columnIndex);
            move.setType(moveType);
            int gameStatus = multiplayerGame.playMove(move);
            ClientHandler otherClient = clientsTable.get(multiClientID);
            if (gameStatus == 3) {
                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                System.out.println("will send to " + multiClientID + " " + otherClient + " " + messageToOtherClient);
                otherClient.writeToClient.println(messageToOtherClient);
                System.out.println("after send");
            }

            else if (gameStatus == 0) {
                String messageToClient = new String("over.0");
                writeToClient.println(messageToClient);

                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                otherClient.writeToClient.println(messageToOtherClient);

                messageToOtherClient = new String("over.0");
                otherClient.writeToClient.println(messageToClient);

            }

            else if (gameStatus == this.moveType) {
                String messageToClient = new String("over.1");
                writeToClient.println(messageToClient);

                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                otherClient.writeToClient.println(messageToOtherClient);

                messageToOtherClient = new String("over.-1");
                otherClient.writeToClient.println(messageToOtherClient);
            }

        }
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        private void handleLogoutRequest() {
            clientsTable.remove(clientID);
            closeConnection();
        }

        private void handleQuitRequest() {
            closeConnection();
        }

        private void handleStateRequest() {
            String messageToClient = new String("state.");
            for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
                // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                if (hashEntry.getKey().equals(clientID) == false) {
                    messageToClient = messageToClient + hashEntry.getKey() + "/";
                }
                // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

            }
            writeToClient.println(messageToClient);
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

        private void handleClientRequest(String messageFromClient) {
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
                handleReplyRequest(tokens[1]);
            }

            else if (tokens[0].equals(new String("AIrequest"))) {
                handleAIgameRequest(tokens[1]);
            }

            else if (tokens[0].equals(new String("AIgame"))) {
                handleAIgameMoveRequest(tokens[1], tokens[2]);
            }
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            else if (tokens[0].equals(new String("multigame"))) {
                handleMultigameMoveRequest(tokens[1], tokens[2]);
            }
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            else if (tokens[0].equals(new String("logout"))) {
                handleLogoutRequest();
            }

            else if (tokens[0].equals(new String("quit"))) {
                handleQuitRequest();
            }

            else if (tokens[0].equals(new String("state"))) {
                handleStateRequest();
            }

        }

        @Override
        public void run() {
            try {
                while (true) {
                    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                    String messageFromClient = new String(readFromClient.readLine());
                    handleClientRequest(messageFromClient);
                    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
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
