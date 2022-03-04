/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class Exit1Controller implements Initializable {

    public static Exit1Controller exitcont;

    @FXML
    private ImageView bt_exit;

    @FXML
    private ImageView bt_friend;

    @FXML
    private ImageView bt_leaderborde;

    @FXML
    private Button bt_loadexite2;

    @FXML
    private Button bt_loadfriend;

    @FXML
    private Button bt_loadleaderborde;

    @FXML
    private Button bt_setting;

    @FXML
    private AnchorPane exit;

    @FXML
    private Button switchMode_bt;

    @FXML
    private BorderPane exit2;

    @FXML
    private Button exit_bt;

    @FXML
    private Button switchAccount_bt;

    @FXML
    private AnchorPane topbar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitcont = this;
        // TODO
    }

    @FXML
    private void loadaccount(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadSwitchMode(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = true;
        ClientController.getCONTROL().gameflag = false;
        ClientController.getCONTROL().exitflag = false;
        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
        ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().exitflag = false;
        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().leaderflag = true;
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
        ClientController.getCONTROL().exitflag = true;
        ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
        ClientController.getCONTROL().exitflag = false;
        ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().friendflag = true;
        ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void closeButtonAction() {
ClientController.getCONTROL().LogOut();
        // get a handle to the stage
        Stage stage = (Stage) exit_bt.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void inviteStatus(String s) throws IOException {

        if (s.equals(new String("1"))) {

            BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
            topbar.getChildren().setAll(pane);
        }

    }

    public void serverCloseToExit(String s) {

        if (s.equals(new String("1"))) {
            System.out.println("........................before if");

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Sorry Server Closed !!!!!!!!");
                        alert.setContentText(" ");
                        alert.initStyle(StageStyle.UNDECORATED);
//                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//                    alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
                        dialogPane.getStyleClass().add("myDialog");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOK) {
                            System.out.println("OK");

//                            status.set(1);
                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            topbar.getChildren().setAll(pane);

                        } else {
                            System.out.println("OK from else");

//                            status.set(0);
                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            topbar.getChildren().setAll(pane);

                        }

                    } catch (Exception ex) {
                        System.out.println("catchhhhhhhhhh");

                        ex.printStackTrace();
                    }
//                    latchToWaitForJavaFx.countDown();
                }

            });

        }
    }

}
