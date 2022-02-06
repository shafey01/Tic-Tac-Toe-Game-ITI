package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import login.CreateAccountController;
import login.FXMLDocumentController;
import login.FriendController;
import static login.FriendController.friendControl;

public  class ClientController {

    private BufferedReader readClientInput;
    private Client currentClient;
    private static ClientController CONTROL;
    private static int a=1;

    public static int getA() {
        return a;
    }
//    private FriendController friend;
     String[] state;
    public ClientController() throws IOException {
        readClientInput = new BufferedReader(new InputStreamReader(System.in));
        currentClient = new Client(this);
             if(a==1){
            CONTROL = this;
            a++  ; 
            }
                      System.out.println("1"+this);

        if (currentClient.isConnectionSuccess == false) {
            System.out.println("Error Connecting To Server");
        }
//        readInputFromClient();
    }
    
   
      
   
    public static ClientController getCONTROL() {
        return CONTROL;
    }


//public ClientController()
//{
//
//
//}

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

    public void gameMovesControl(String rowIndex, String columnIndex) {
        // release lock
        System.out.println("AI played in " + rowIndex + columnIndex);
    }

    public void gameOverControl(String gameStatus) {
        if (gameStatus.equals(new String("0"))) {
            System.out.println("Game Over Nobody wins");
        } else if (gameStatus.equals(new String("1"))) {
            System.out.println("Game over you win");
        } else {
            System.out.println("Game over AI wins");
        }
    }

    public void stateControl(String[] state) {
        // release lock
        System.out.println("Controller: " + state[0]);
        this.state = state;
      // friend = new FriendController();
      // friend.getFriendControl().getState(state);

     //   return state;
    }
    public String[] sendState2() {
return this.state;
}
//    private void readInputFromClient() throws IOException {
//
//        String clientInput;
//        while (true) {
//            System.out.println("Enter your input:");
//            clientInput = readClientInput.readLine();
//            sendRequestToServer(clientInput);
//
//        }
//    }
    public void sendLoginRequest(String userName, String password) {

        int loginReply = currentClient.sendLoginRequest(userName, password);

        System.out.println(loginReply);
        if (loginReply == 1) {
            try {
                System.out.println("inside send login request");

                FXMLDocumentController.logincontroller.sendToControllerLogin(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("login successful");
        } else {
            try {
                FXMLDocumentController.logincontroller.sendToControllerLogin(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("login failed");
        }

    }

    public void sendSignupRequest(String userName, String password) {

        int signupReply = currentClient.sendSignupRequest(userName, password);
        if (signupReply == 1) {

            try {
                CreateAccountController.createacount.sendToController(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("signup successful");
        } else {

            try {
                CreateAccountController.createacount.sendToController(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("signup failed");
        }

    }

    public void sendInviteRequest(String userNameToInvite) {

        try {

            currentClient.sendInviteRequest(userNameToInvite);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void AIRequest() {
        System.out.println("Do you want the computer to start?");
        String computerStarts = "1";
        currentClient.sendAIgameRequest(computerStarts);

    }

    public void AIMove(String rowIndex, String columnIndex) {

        currentClient.sendAIgameMove(new String(rowIndex + "." + columnIndex));

    }

    public void sendStateRequest() {
        currentClient.sendState();

    }

    public void LogOut() {

        currentClient.sendLogoutRequest();
    }

//    private void sendRequestToServer(String clientInput, String userName) throws IOException {
//        //        if (clientInput.equals(new String("login"))) {
//        //            String username, password;
//        //
//        //            int loginReply = currentClient.sendLoginRequest(username, password);
//        //            if (loginReply == 1) {
//        //                FXMLDocumentController.logincontroller.sendToControllerLogin(1);
//        //                System.out.println("login successful");
//        //            } else {
//        //                FXMLDocumentController.logincontroller.sendToControllerLogin(0);
//        //                System.out.println("login failed");
//        //            }
//        //        } else if (clientInput.equals(new String("signup"))) {
//        //            String username, password;
//        //            System.out.println("Enter username");
//        //            username = readClientInput.readLine();
//        //            System.out.println("Enter password");
//        //            password = readClientInput.readLine();
//        //            currentClient.sendSignupRequest(username, password);
//        //            int signupReply = currentClient.sendSignupRequest(username, password);
//        //            if (signupReply == 1) {
//        //                CreateAccountController.createacount.sendToController(1);
//        //                System.out.println("signup successful");
//        //            } else {
//        //                CreateAccountController.createacount.sendToController(0);
//        //                System.out.println("signup failed");
//        //            }
//        //        } else if (clientInput.equals(new String("invite"))) {
//        //        System.out.println("enter other user id: ");
//        //
//        //        try {
//        //            String idToInvite = readClientInput.readLine();
//        //            currentClient.sendInviteRequest(idToInvite);
//        //        } catch (IOException e) {
//        //            // TODO Auto-generated catch block
//        //            e.printStackTrace();
//        //        }
//        //    }
//
//        else if (clientInput.equals( new String("AIrequest"))) {
//            System.out.println("Do you want the computer to start?");
//        String computerStarts = "1";
//        currentClient.sendAIgameRequest(computerStarts);
//
//    }
//
//    else if (clientInput.equals ( 
//        new String("AIgame"))) {
//            System.out.println("Enter row index");
//        String rowIndex = readClientInput.readLine();
//        System.out.println("Enter column index");
//        String columnIndex = readClientInput.readLine();
//        currentClient.sendAIgameMove(new String(rowIndex + "." + columnIndex));
//    }
//
//    else if (clientInput.equals ( 
//        new String("logout"))) {
//            currentClient.sendLogoutRequest();
//    }
//
//    }
}
