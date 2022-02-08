package ClientServerNew;

import DataBase.GamePkg.GameDAO;
import DataBase.PlayerPkg.PlayerDAO;
import DataBase.UserPkg.ContactDAO;
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
import Game.MultiplayerGame;
import java.util.Map;

public class Server {
    
    private ServerSocket serverSock;
    private Socket internalSock;
    private static Server server;
    private GameDAO gameDAO;
    private ContactDAO gameCotact;
    private PlayerDAO playerDAO;
    
    public static Server getServer() {
        return server;
    }
    private static int a = 1;
    private Hashtable<String, ClientHandler> clientsTable = new Hashtable<String, ClientHandler>();
    
    public Server() {
        
        System.out.println("tic.tac.toe.Server.<init>()");
        if (a == 1) {
            server = this;
            a++;
        }
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
        startServer();
        
    }
    
    public void startServer() {
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
    
    public void stopServer() {
        for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
            hashEntry.getValue().writeToClient.println("exit");
            hashEntry.getValue().closeConnection();
        }
    }
    
    private class ClientHandler extends Thread {
        
        private Socket internalSockHandler;
        private PrintWriter writeToClient;
        private BufferedReader readFromClient;
        private String userName;
//        private String messageFromClient;
        private GameWithComputer gameWithComputer;
        ContactDAO databaseContactDAO;
        private MultiplayerGame multiplayerGame;
        private String multiClientUserName;
        
        private int moveType;
        
        public ClientHandler(Socket internalSocket) {
            internalSockHandler = internalSocket;
            // isOn = true;
            try {
                databaseContactDAO = new ContactDAO();
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

        // private Move stringToMove()
        private void handleLoginRequest(String userName, String password) {
            // database
            int result = databaseContactDAO.getUserIdByName(userName, password);
            System.out.println("rsult from db: " + result);
            this.userName = userName;
            if (result != 0 && result != -1) {
                clientsTable.put(String.valueOf(userName), this);
            }
            
            String messageToClient = new String(String.valueOf(result));
            writeToClient.println(messageToClient);
        }
        
        private void handleSignupRequest(String userName, String password) {
            // database
            int resultFromLogin = databaseContactDAO.getUserByNameBoolean(userName, password);
            String messageToClient = new String(String.valueOf(resultFromLogin));
            writeToClient.println(messageToClient);
        }
        
        private void handleInvitaionRequest(String userNameToInvite) {
            ClientHandler otherClient = clientsTable.get(userNameToInvite);
            if (otherClient != null) {
                String messageToOtherClient = new String("invite." + userName);
                otherClient.writeToClient.println(messageToOtherClient);
                
            }
        }
        
        private void handleReplyRequest(String otherClientuserName) {
            ClientHandler otherClient = clientsTable.get(otherClientuserName);
            if (otherClient != null) {
                this.multiplayerGame = new MultiplayerGame();
                this.moveType = 2;
                this.multiClientUserName = otherClientuserName;
                
                otherClient.multiClientUserName = userName;
                otherClient.multiplayerGame = this.multiplayerGame;
//                String messageToClient = new String("reply." + userName + ".");
                String messageToOtherClient = new String("multistart." + userName);
                
                otherClient.writeToClient.println(messageToOtherClient);
                
            }
        }
        
        private void handleStateRequest() {
            String messageToClient = new String("state.");
            int x = clientsTable.size();
            System.out.println("size: " + x);
            for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
                
                if (hashEntry.getKey().equals(userName) == false) {
                    messageToClient = messageToClient + hashEntry.getKey() + ".";
                }
                
            }
            
            writeToClient.flush();
            
            writeToClient.println(messageToClient);
            System.out.println(messageToClient + " " + messageToClient.getClass().getName());
            
        }
        
        private void handleAIgameRequest(String computerStarts) {
            gameDAO = new GameDAO();
            gameCotact = new ContactDAO();
            playerDAO = new PlayerDAO();
            try {
                int gameId = gameDAO.createNewGame();
                System.out.println(gameId + " : " + userName);
                int userId = gameCotact.getUserIdByName(userName);
                System.out.println("user id:" + userId);
                playerDAO.createNewUser(userId, gameId);
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (computerStarts.equals(new String("1"))) {
                
                gameWithComputer = new GameWithComputer(true);
                String messageToClient = new String("game." + gameWithComputer.getComputerMove().toString());
                writeToClient.println(messageToClient);
            } else {
                gameWithComputer = new GameWithComputer(false);
                System.out.println("Else computer");
            }
            
        }
        
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
            } else if (gameStatus == 1) {
                messageToClient = new String("over.1");
                writeToClient.println(messageToClient);
            } else if (gameStatus == -1) {
                messageToClient = new String("over.-1");
                writeToClient.println(messageToClient);
            }
            
        }
        
        private void handleMultigameMoveRequest(String rowIndex, String columnIndex) {
            Move move = Move.stringToMove(rowIndex, columnIndex);
            move.setType(moveType);
            int gameStatus = multiplayerGame.playMove(move);
            ClientHandler otherClient = clientsTable.get(multiClientUserName);
            if (gameStatus == 3) {
                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                otherClient.writeToClient.println(messageToOtherClient);
                System.out.println("after send");
            } else if (gameStatus == 0) {
                this.moveType = otherClient.moveType = 1;
                String messageToClient = new String("over.0");
                writeToClient.println(messageToClient);
                
                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                otherClient.writeToClient.println(messageToOtherClient);
                
                messageToOtherClient = new String("over.0");
                otherClient.writeToClient.println(messageToClient);
                
            } else if (gameStatus == this.moveType) {
                this.moveType = otherClient.moveType = 1;
                String messageToClient = new String("over.1");
                writeToClient.println(messageToClient);
                
                String messageToOtherClient = new String("game." + rowIndex + "." + columnIndex);
                otherClient.writeToClient.println(messageToOtherClient);
                
                messageToOtherClient = new String("over.-1");
                otherClient.writeToClient.println(messageToOtherClient);
            }
            
        }
        
        private void handleLogoutRequest() {
            clientsTable.remove(userName);
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
        
        private void handleClientRequest(String messageFromClient) {
            System.out.println("received " + messageFromClient);
            String[] tokens = parseClientMessage(messageFromClient);
            if (tokens[0].equals(new String("login"))) {
                handleLoginRequest(tokens[1], tokens[2]);
            } else if (tokens[0].equals(new String("signup"))) {
                handleSignupRequest(tokens[1], tokens[2]);
            } else if (tokens[0].equals(new String("invite"))) {
                handleInvitaionRequest(tokens[1]);
            } else if (tokens[0].equals(new String("reply"))) {
                handleReplyRequest(tokens[1]);
            } else if (tokens[0].equals(new String("AIrequest"))) {
                handleAIgameRequest(tokens[1]);
            } else if (tokens[0].equals(new String("AIgame"))) {
                
                handleAIgameMoveRequest(tokens[1], tokens[2]);
            } else if (tokens[0].equals(new String("multigame"))) {
                handleMultigameMoveRequest(tokens[1], tokens[2]);
            } else if (tokens[0].equals(new String("state"))) {
                handleStateRequest();
            } else if (tokens[0].equals(new String("logout"))) {
                handleLogoutRequest();
            } else if (tokens[0].equals(new String("quit"))) {
                handleQuitRequest();
            }
            
        }
        
        @Override
        public void run() {
            
            try {
                while (true) {
                    
                    String messageFromClient = new String(readFromClient.readLine());
                    handleClientRequest(messageFromClient);
                    System.out.println("AAA" + messageFromClient);
                    
                    System.out.println("under handleClientRequest");
                }
            } catch (IOException e) {
                
                e.printStackTrace();
                System.out.println("connection to client closed");
            }
            
            System.out.println("thread closed");
            
        }
    }
    
    public String[] serverRequestState() {
        String[] state = new String[clientsTable.size()];
        int i = 0;
        for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
            
            state[i] = new String(hashEntry.getKey());
            i++;
        }
        return state;
    }
}

// for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
// System.out.print(hashEntry.getKey() + " " + hashEntry.getValue());
// }
