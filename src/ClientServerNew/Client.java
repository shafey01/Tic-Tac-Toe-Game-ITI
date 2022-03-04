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
                System.out.println("run");
                try {
                    while (true) {
                        messageFromServer = readFromServer.readLine();
                        System.out.println(".run 1()");
                        try {
                            handleServerReply(messageFromServer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(".run 2()");

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

    private void handleServerReply(String messageFromServer) throws InterruptedException, IOException {
        System.out.println("received " + messageFromServer);
        String[] tokens = parseServerMessage(messageFromServer);

        if (tokens[0].equals(new String("invite"))) {
            controller.invitationControl(tokens[1]);
        } else if (tokens[0].equals(new String("multistart"))) {

            controller.gameStartControl(tokens[1]);

        } else if (tokens[0].equals(new String("game"))) {
            controller.gameMovesControl(tokens[1], tokens[2]);
        } else if (tokens[0].equals(new String("over"))) {
            controller.gameOverControl(tokens[1]);
        } else if (tokens[0].equals(new String("state"))) {

            String[] state = new String[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {

                state[i - 1] = new String(tokens[i]);

            }

            controller.stateControl(state);

        } else if (tokens[0].equals(new String("multiclose"))) {
            controller.multiCloseControl();
        } else if (tokens[0].equals(new String("exit"))) {
            controller.exitControl();
            closeConnection();
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
            } else if (messageFromServer.equals(new String("-2"))) {
                return -2;
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

    public void sendInviteRequest(String userNameToInvite) {
        String messageToServer = new String("invite." + userNameToInvite);
        writeToServer.println(messageToServer);
    }

    public void sendReplyRequest(String userNameToReply) {
        String messageToServer = new String("reply." + userNameToReply);
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

    public void sendLeaderBoardRequest() {

        String messageToServer = new String("LeaderBoard");
        writeToServer.println(messageToServer);

    }

    public void sendState() {
        String messageToServer = new String("state.");
        writeToServer.println(messageToServer);

    }

    public void sendMultiCloseRequest() {

        String messageToServer = new String("multiclose.");
        writeToServer.println(messageToServer);
    }

}
