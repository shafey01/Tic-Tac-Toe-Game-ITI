/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class Exit1Controller implements Initializable {

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
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        exit2.getChildren().setAll(pane);
    }

    @FXML
    private void closeButtonAction() {
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

}
