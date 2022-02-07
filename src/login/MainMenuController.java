/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button ai_bt;

    @FXML
    private AnchorPane main;

    @FXML
    private Button online_bt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        main.getChildren().setAll(pane);
    }

//    @FXML
//    void startGameAi(ActionEvent event) throws IOException {
//        BorderPane pane = FXMLLoader.load(getClass().getResource("Game_v3.fxml"));
//        main.getChildren().setAll(pane);
////        ClientController.getCONTROL().AIRequest();
//
//    }
//    @FXML
//    void GameAi(ActionEvent event) throws IOException {
//        BorderPane pane = FXMLLoader.load(getClass().getResource("Game_v3.fxml"));
//        main.getChildren().setAll(pane);
//    }
    @FXML
    void game(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
        main.getChildren().setAll(pane);
ClientController.getCONTROL().AIRequest();
    }

}
