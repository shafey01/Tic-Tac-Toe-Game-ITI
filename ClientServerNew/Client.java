package ClientServerNew;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import java.io.BufferedReader;

public class Client {

    // private Boolean isOn;
    private Socket clientSocket;
    private String id;
    private PrintWriter writeToServer;
    private BufferedReader readFromServer;
    // private String messageToServer;
    // private String messageFromServer;
    private Thread listenToServer;
    /////////////////////////////////
    // private boolean receiveMonitor;
    public boolean connectionSuccess;

    //////////////////////////// remove throws and add try catch
    public Client(String _id) {
        // isOn = true;
        // receiveMonitor = false;
        id = _id;
        try {
            ///////////////////////////////////////////
            this.clientSocket = new Socket(InetAddress.getLocalHost(), 7001);
            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            connectionSuccess = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            ////////////////
            connectionSuccess = false;
            e.printStackTrace();
        }
        //////////////// commented
        initListenToServerThread();

    }

    private void initListenToServerThread() {
        listenToServer = new Thread() {
            String messageFromServer;

            @Override
            public void run() {
                try {
                    while (true) {
                        messageFromServer = readFromServer.readLine();

                        //////////////////////////////////////
                        handleServerReply(messageFromServer);

                    }

                } catch (IOException e) {
                    // e.printStackTrace();
                    System.out.println("connection to server closed");
                }
                System.out.println("thread closed");

            }

        };
    }

    ///////////////////////////////////////////// private

    private String[] parseServerMessage(String clientMessage) {

        return clientMessage.split("\\.");

    }

    private void handleServerReply(String messageFromServer) {
        System.out.println("received " + messageFromServer);
        String[] tokens = parseServerMessage(messageFromServer);

        if (tokens[0].equals(new String("invite"))) {
            // handleInvitaionRequest(tokens[1]);
        }

        else if (tokens[0].equals(new String("exit"))) {
            // handleLogoutRequest();
        }
    }

    // ====================================================================
    ////////// private
    private void closeConnection() {

        writeToServer.close();
        try {
            readFromServer.close();
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // isOn = false;
    }

    public void sendLogoutRequest() {
        String messageToServer = new String("logout");
        writeToServer.println(messageToServer);
        // closeConnection();
    }

    public int sendLoginRequest(String username, String password) {
        String messageToServer = new String("login" + "." + username + "." + password);
        writeToServer.println(messageToServer);
        int defaultLoginReply = 0;
        try {
            String messageFromServer = readFromServer.readLine();
            if (messageFromServer.equals(new String("1"))) {
                listenToServer.start();
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultLoginReply;
    }

    public int sendSignupRequest(String username, String password) {
        String messageToServer = new String("signup" + "." + id);
        writeToServer.println(messageToServer);
        int defaultSignupReply = 0;
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
        return defaultSignupReply;
    }

    public void sendInviteRequest(String idToInvite) {
        String messageToServer = new String("invite." + idToInvite);
        writeToServer.println(messageToServer);
    }

    public void sendQuitRequest() {
        String messageToServer = new String("quit");
        writeToServer.println(messageToServer);
        closeConnection();
    }

}
