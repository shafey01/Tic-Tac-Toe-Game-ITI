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
    /////////////////////////////////
    // private boolean invitationMonitor;
    /////////////////// string invid, string repval
    // public String invitationID;
    // public boolean replyMonitior;
    // public String replyValue;
    // public String replyID;
    // public boolean exitMonitor;
    public boolean isConnectionSuccess;

    public Client(ClientController controller) {

        // id = _id;
        this.controller = controller;
        // initMonitors();
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

    // private void initMonitors() {
    // invitationMonitor = replyMonitior = exitMonitor = false;
    // }

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

        else if (tokens[0].equals(new String("reply"))) {

            controller.replyControl(tokens[1], tokens[2]);
        }

        else if (tokens[0].equals(new String("exit"))) {
            // handleLogoutRequest();
        }
    }

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

    public void sendLogoutRequest() {
        String messageToServer = new String("logout");
        writeToServer.println(messageToServer);
        closeConnection();
    }

    public void sendInviteRequest(String idToInvite) {
        String messageToServer = new String("invite." + idToInvite);
        writeToServer.println(messageToServer);
    }

    public void sendReplyRequest(String idToReply, String isAccepted) {
        String messageToServer = new String("reply." + idToReply + "." + isAccepted);
        writeToServer.println(messageToServer);
    }

    public void sendQuitRequest() {
        String messageToServer = new String("quit");
        writeToServer.println(messageToServer);
        closeConnection();
    }

}