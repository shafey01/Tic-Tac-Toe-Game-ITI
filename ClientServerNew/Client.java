package ClientServerNew;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import java.io.BufferedReader;

public class Client {

    private Socket clientSocket;
    private String id;
    private PrintWriter writeToServer;
    private BufferedReader readFromServer;
    private Thread listenToServerThread;
    private ClientController controller;
    public boolean isConnectionSuccess;

    public Client(ClientController controller) {

        this.controller = controller;
        try {
            this.clientSocket = new Socket(InetAddress.getLocalHost(), 7001);
            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            isConnectionSuccess = true;
        } catch (IOException e) {
            isConnectionSuccess = false;
        }
        initListenToServerThread();

    }

    private void initListenToServerThread() {
        listenToServerThread = new Thread() {
            String messageFromServer;

            @Override
            public void run() {
                try {
                    while (true) {
                        messageFromServer = readFromServer.readLine();
                        handleServerReply(messageFromServer);
                    }

                } catch (IOException e) {
                    System.out.println("connection to server closed");
                }
                System.out.println("thread closed");
            }
        };
    }

    private String[] parseServerMessage(String clientMessage) {

        return clientMessage.split("\\.");

    }

    private void handleServerReply(String messageFromServer) {
        System.out.println("received " + messageFromServer);
        String[] tokens = parseServerMessage(messageFromServer);

        if (tokens[0].equals(new String("invite"))) {
            controller.invitationControl(tokens[1]);
        }

        else if (tokens[0].equals(new String("multistart"))) {

            controller.gameStartControl(tokens[1]);
            controller.readNextInput();
        }
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        else if (tokens[0].equals(new String("game"))) {
            controller.gameMovesControl(tokens[1], tokens[2]);
            controller.readNextInput();
        }

        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        else if (tokens[0].equals(new String("over"))) {
            controller.gameOverControl(tokens[1]);
            controller.readNextInput();
        }
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        else if (tokens[0].equals(new String("exit"))) {
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            controller.exitControl();
            closeConnection();
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        }

        else if (tokens[0].equals(new String("state"))) {
            System.out.println(tokens[1]);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void closeConnection() {

        writeToServer.close();
        try {
            readFromServer.close();
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int sendLoginRequest(String username, String password) {
        String messageToServer = new String("login" + "." + username + "." + password);
        writeToServer.println(messageToServer);
        int defaultLoginServerReply = 0;
        try {
            String messageFromServer = readFromServer.readLine();
            if (messageFromServer.equals(new String("1"))) {
                listenToServerThread.start();
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultLoginServerReply;
    }

    public int sendSignupRequest(String username, String password) {
        String messageToServer = new String("signup" + "." + username + "." + password);
        writeToServer.println(messageToServer);
        int defaultSignupServerReply = 0;
        try {
            String messageFromServer = readFromServer.readLine();
            if (messageFromServer.equals(new String("1"))) {
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultSignupServerReply;
    }

    public void sendInviteRequest(String idToInvite) {
        String messageToServer = new String("invite." + idToInvite);
        writeToServer.println(messageToServer);
    }

    public void sendReplyRequest(String idToReply) {
        String messageToServer = new String("reply." + idToReply);
        writeToServer.println(messageToServer);
    }

    public void sendAIgameRequest(String computerStarts) {
        String messageToServer = new String("AIrequest." + computerStarts);
        writeToServer.println(messageToServer);
    }

    public void sendAIgameMove(String move) {
        String messageToServer = new String("AIgame." + move);
        writeToServer.println(messageToServer);
    }

    public void sendMultigameMove(String move) {
        String messageToServer = new String("multigame." + move);
        writeToServer.println(messageToServer);
    }

    public void sendLogoutRequest() {
        String messageToServer = new String("logout");
        writeToServer.println(messageToServer);
        closeConnection();
    }

    public void sendQuitRequest() {
        String messageToServer = new String("quit");
        writeToServer.println(messageToServer);
        closeConnection();
    }

    public void sendStateRequest() {
        String messageToServer = new String("state");
        writeToServer.println(messageToServer);
    }

}