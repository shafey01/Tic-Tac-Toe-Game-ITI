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
import login.Game_v3Controller;
import login.Game_v3Controller1;
import login.LeaderBordeController;

public class ClientController {

    private BufferedReader readClientInput;
    private Client currentClient;
    private int moveType;
    private static ClientController CONTROL;
    private static int a = 1;

    public static int getA() {
        return a;
    }
//    private FriendController friend;
    String[] state;

    public ClientController() throws IOException {
        readClientInput = new BufferedReader(new InputStreamReader(System.in));
        currentClient = new Client(this);
        if (a == 1) {
            CONTROL = this;
            a++;
        }
        System.out.println("1" + this);

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
    public void invitationControl(String userNameToInvite) throws IOException {

        System.out.println("invitation from " + userNameToInvite);
        System.out.println("Do you want to accept");
        int isAccepted = FriendController.friendControl.inviteAction();
        try {

///alert from GUI
            if (isAccepted == 1) {
                Game_v3Controller1.gameControl.gameFlag = "normal";
                Game_v3Controller1.gameControl.XOFLAG = "o";

                currentClient.sendReplyRequest(userNameToInvite);
                FriendController.friendControl.inviteStatus("1");

            } else {

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void gameStartControl(String acceptUserName) throws IOException {

//Game from GUI
        System.out.println("Start game with " + acceptUserName);
        Game_v3Controller1.gameControl.gameFlag = "normal";
        FriendController.friendControl.inviteStatus(new String("1"));
        LeaderBordeController.LeaderBordeController.inviteStatus(new String("1"));

    }

    public void replyControl(String replyID, String isAccepted) {
        System.out.println("reply from" + replyID + " " + isAccepted);
    }

    public void gameMovesControl(String rowIndex, String columnIndex) throws InterruptedException {
        // release lock
//        Game_v3Controller1.gameControl.aiMove(rowIndex, columnIndex);
        Game_v3Controller1.gameControl.otherPlayerMove(rowIndex, columnIndex);
        System.out.println("AI played in " + rowIndex + columnIndex);
    }

    public void gameOverControl(String gameStatus) {
        if (gameStatus.equals(new String("0"))) {
            System.out.println("Game Over Nobody wins");
            Game_v3Controller1.gameControl.gameStatuswithAi("0");
        } else if (gameStatus.equals(new String("1"))) {
            System.out.println("Game over you win");
            Game_v3Controller1.gameControl.gameStatuswithAi("1");

        } else {
            System.out.println("Game over AI wins");
            Game_v3Controller1.gameControl.gameStatuswithAi("-1");

        }
    }

    public void stateControl(String[] state) {
        // release lock

        this.state = state;

    }

    public String[] sendState2() {
        return this.state;
    }

    public void exitControl() {
        System.out.println("Server Exit");
    }

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

        String computerStarts = "0";
        Game_v3Controller1.gameControl.gameFlag = "ai";
        currentClient.sendAIgameRequest(computerStarts);

    }

    public void AIMove(String rowIndex, String columnIndex) {

        currentClient.sendAIgameMove(new String(rowIndex + "." + columnIndex));

    }

    public void MultiplayerMove(String rowIndex, String columnIndex) {

        currentClient.sendMultigameMove(new String(rowIndex + "." + columnIndex));
    }

    public void sendStateRequest() {
        currentClient.sendState();

    }

    public void LogOut() {

        currentClient.sendLogoutRequest();
    }

}
