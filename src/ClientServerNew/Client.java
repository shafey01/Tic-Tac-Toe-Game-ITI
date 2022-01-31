package ClientServerNew;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;

public class Client {

    Boolean isOn;
    Socket clientSocket;
    String id;
    String password;
    PrintWriter writeToServer;
    BufferedReader readFromServer;
    BufferedReader readClientInput;
    String messageToServer;
    String messageFromServer;
    String clientInput;

    public Client(String _id) throws IOException {
        isOn = true;
        id = _id;
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
        this.clientSocket = new Socket(ip, 7001);
        readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        readClientInput = new BufferedReader(new InputStreamReader(System.in));
        initListenToServerThread();
        readInputFromClient();

    }

    public void initListenToServerThread() {
        new Thread() {
            @Override
            public void run() {

                while (isOn) {
                    try {
                        messageFromServer = readFromServer.readLine();
                        System.out.println(messageFromServer);
                    } catch (IOException e) {
                        // e.printStackTrace();
                        System.out.println("connection to server closed");
                    }

                }
                System.out.println("thread closed");
            }

        }.start();
    }

    public void readInputFromClient() throws IOException {
        while (isOn) {
            System.out.println("Enter your input:");
            clientInput = readClientInput.readLine();
            sendRequestToServer(clientInput);

        }
    }

    public void sendRequestToServer(String clientInput) {

        if (clientInput.equals(new String("login"))) {
            sendLoginRequest();
        } else if (clientInput.equals(new String("signup"))) {
            sendSignupRequest();
        } else if (clientInput.equals(new String("invite"))) {
            System.out.println("enter other user id: ");
            String idToInvite = null;
            try {
                idToInvite = readClientInput.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sendInviteRequest(idToInvite);
        } else if (clientInput.equals(new String("logout"))) {
            sendLogoutRequest();

            // sleep(2000);
        }

    }

    public void closeConnection() {

        writeToServer.close();
        try {
            readFromServer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        isOn = false;
    }

    public void sendLogoutRequest() {
        messageToServer = "logout";
        writeToServer.println(messageToServer);
        closeConnection();
    }

    public void sendLoginRequest() {
        messageToServer = new String("login" + "." + id);
        writeToServer.println(messageToServer);
    }

    public void sendSignupRequest() {
        messageToServer = new String("signup" + "." + id);
        writeToServer.println(messageToServer);
    }

    public void sendInviteRequest(String idToInvite) {
        messageToServer = new String("invite." + idToInvite);
        writeToServer.println(messageToServer);
    }

}
