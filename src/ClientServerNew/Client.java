package ClientServerNew;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;
import login.CreateAccountController;
import login.FXMLDocumentController;
import static login.FXMLDocumentController.logincontroller;

public class Client {

    Boolean isOn;
    Socket clientSocket;
    String id;
    String password;
    String userName;
    public int status2 = 0;

    PrintWriter writeToServer;
    BufferedReader readFromServer;
    BufferedReader readClientInput;
    String messageToServer;
    String messageFromServer;
    String clientInput;

    public Client() throws IOException {
        isOn = true;

        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
        this.clientSocket = new Socket(ip, 7001);
        readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        readClientInput = new BufferedReader(new InputStreamReader(System.in));
//        signup = new CreateAccountController();
//        login = new FXMLDocumentController();
        initListenToServerThread();
//        readInputFromClient();

    }

    public void initListenToServerThread() {
        new Thread() {
            @Override
            public void run() {

                while (isOn) {
                    try {
                        messageFromServer = readFromServer.readLine();
                        System.out.println(messageFromServer);
                        String[] message = parseClientMessage(messageFromServer);

                        if (message[0].equals(new String("login"))) {

                            loginStatus(message);
                        } else if (message[0].equals(new String("signup"))) {
                            signupStatus(message);
                        }

                    } catch (IOException e) {
                        // e.printStackTrace();
                        System.out.println("connection to server closed");
                    }

                }
                System.out.println("thread closed");
            }

        }.start();
    }

//    public void readInputFromClient() throws IOException {
//        while (isOn) {
//            System.out.println("Enter your input:");
//            clientInput = readClientInput.readLine();
//            sendRequestToServer(clientInput);
//
//        }
//    }
    public void sendRequestToServer(String clientInput, String userName, String password) {
        if (!userName.isEmpty() && !password.isEmpty()) {

            if (clientInput.equals(new String("login"))) {

                sendLoginRequest(userName, password);
            } else if (clientInput.equals(new String("signup"))) {

                sendSignupRequest(userName, password);
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

    public void sendLoginRequest(String userName, String password) {

        messageToServer = new String("login" + "." + userName + "." + password);
        writeToServer.println(messageToServer);
    }

    public void sendSignupRequest(String userName, String password) {
        messageToServer = new String("signup" + "." + userName + "." + password);
        writeToServer.println(messageToServer);
    }

    public void sendInviteRequest(String idToInvite) {
        messageToServer = new String("invite." + idToInvite);
        writeToServer.println(messageToServer);
    }

    public String[] parseClientMessage(String clientMessage) {

        return clientMessage.split("\\.");

    }

    public void signupStatus(String[] message) throws IOException {

        if (message[0].equals(new String("signup"))) {

            if (message[1].equals(new String("1"))) {

                System.out.println("signup success ");

                CreateAccountController.createacount.sendToController(1);

            } else if (message[1].equals(new String("0"))) {

                System.out.println("the user name is used try another name");
                CreateAccountController.createacount.sendToController(0);

            } else if (message[1].equals(new String("-1"))) {
                System.out.println("Error, please try later");
                CreateAccountController.createacount.sendToController(-1);

            }
        }

    }

    public void loginStatus(String[] message) throws IOException {

        if (message[1].equals(new String("0"))) {

            System.out.println("Invalid user name or password");
            FXMLDocumentController.logincontroller.sendToControllerLogin(0);

        } else if (message[1].equals(new String("-1"))) {
            System.out.println("Error, please try later");
            FXMLDocumentController.logincontroller.sendToControllerLogin(-1);

        } else {
            System.out.println("Loged in success");
            FXMLDocumentController.logincontroller.sendToControllerLogin(1);

        }

    }

}
