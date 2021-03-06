package ClientServerNew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import login.CreateAccountController;
import login.Exit1Controller;
import login.FXMLDocumentController;
import login.FriendController;
import static login.FriendController.friendControl;
import login.Game_v3Controller1;
import login.LeaderBordeController;
import login.LoadingController;
import login.Login;
import login.MainMenuController;
import login.SettingController;

public class ClientController {

    private BufferedReader readClientInput;
    private Client currentClient;
    private int moveType;
    private static ClientController CONTROL;
    private static int a = 1;
    public static Boolean settingflag = false;
    public static Boolean leaderflag = false;
    public static Boolean friendflag = false;
    public static Boolean exitflag = false;
    public static Boolean gameflag = false;
    public static Boolean gameflagForimage;

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

    public void invitationControl(String userNameToInvite) throws IOException {

        System.out.println("invitation from " + userNameToInvite);
        System.out.println("Do you want to accept");
        int isAccepted = 0;
        try {
            isAccepted = FriendController.friendControl.inviteAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
//    int isAccepted = 1;
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
//        LeaderBordeController.LeaderBordeController.inviteStatus(new String("1"));

    }

    public void replyControl(String replyID, String isAccepted) {
        System.out.println("reply from" + replyID + " " + isAccepted);
    }

    public void gameMovesControl(String rowIndex, String columnIndex) throws InterruptedException {
        // release lock
        if (MainMenuController.mainmenucotrller.ai_button == 1) {
            Game_v3Controller1.gameControl.aiMove(rowIndex, columnIndex);
        }

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

//        FriendController.friendControl.serverCloseToFriend("1");
        System.out.println("........." + friendflag + leaderflag + settingflag + exitflag);
        if (friendflag == true && leaderflag == false && settingflag == false && exitflag == false) {
            FriendController.friendControl.serverCloseToFriend("1");
        }

        if (friendflag == false && leaderflag == false && settingflag == true && exitflag == false) {
            SettingController.settingcont.serverCloseToSettings("1");
        }

        if (friendflag == false && leaderflag == true && settingflag == false && exitflag == false) {
            LeaderBordeController.LeaderBordeController.serverCloseToLeader("1");
        }
        if (exitflag == true && friendflag == false && leaderflag == false && settingflag == false) {
            Exit1Controller.exitcont.serverCloseToExit("1");
        }

        if (gameflag == true && exitflag == false && friendflag == false && leaderflag == false && settingflag == false) {
            Game_v3Controller1.gameControl.serverCloseToGame("1");
        }
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
        } else if (loginReply == -2) {

               try {
                System.out.println("inside send login request");

                FXMLDocumentController.logincontroller.sendToControllerLogin(-2);
            } catch (Exception e) {
                e.printStackTrace();
            }


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

    public void multiCloseControl() {

        try {
            Game_v3Controller1.gameControl.close("1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMultiCloseRequest() {

        currentClient.sendMultiCloseRequest();

    }

    public void LogOut() {

        currentClient.sendLogoutRequest();
    }

}
