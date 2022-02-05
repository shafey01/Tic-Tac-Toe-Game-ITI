package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientController {
    BufferedReader readClientInput;
    Client currentClient;

    public ClientController() throws IOException {
        readClientInput = new BufferedReader(new InputStreamReader(System.in));
        currentClient = new Client(this);
        if (currentClient.isConnectionSuccess == false) {
            System.out.println("Error Connecting To Server");
        }
        readInputFromClient();
    }

    public void invitationControl(String invitationID) {

        System.out.println("invitation from " + invitationID);
        System.out.println("Do you want to accept");
        try {
            String isAccepted = readClientInput.readLine();
            currentClient.sendReplyRequest(invitationID, isAccepted);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void replyControl(String replyID, String isAccepted) {
        System.out.println("reply from" + replyID + " " + isAccepted);
    }

    private void readInputFromClient() throws IOException {

        String clientInput;
        while (true) {
            System.out.println("Enter your input:");
            clientInput = readClientInput.readLine();
            sendRequestToServer(clientInput);

        }
    }

    private void sendRequestToServer(String clientInput) throws IOException {

        if (clientInput.equals(new String("login"))) {
            String username, password;
            System.out.println("Enter username");
            username = readClientInput.readLine();
            System.out.println("Enter password");
            password = readClientInput.readLine();
            int loginReply = currentClient.sendLoginRequest(username, password);
            if (loginReply == 1) {
                System.out.println("login successful");
            }

            else {
                System.out.println("login failed");
            }
        }

        else if (clientInput.equals(new String("signup"))) {
            String username, password;
            System.out.println("Enter username");
            username = readClientInput.readLine();
            System.out.println("Enter password");
            password = readClientInput.readLine();
            currentClient.sendSignupRequest(username, password);
            int signupReply = currentClient.sendSignupRequest(username, password);
            if (signupReply == 1) {
                System.out.println("signup successful");
            }

            else {
                System.out.println("signup failed");
            }
        }

        else if (clientInput.equals(new String("invite"))) {
            System.out.println("enter other user id: ");

            try {
                String idToInvite = readClientInput.readLine();
                currentClient.sendInviteRequest(idToInvite);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        else if (clientInput.equals(new String("logout"))) {
            currentClient.sendLogoutRequest();
        }

    }
}
