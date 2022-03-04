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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button ai_bt;

    @FXML
    private BorderPane main;

    @FXML
    private Button online_bt;
    public int ai_button;
    public static MainMenuController mainmenucotrller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainmenucotrller = this;
        ai_button = 0;
    }

    @FXML
    private void loadend(ActionEvent event) throws IOException {
ClientController.getCONTROL().gameflag = false;
ClientController.getCONTROL().settingflag = true;
ClientController.getCONTROL().exitflag = false;
ClientController.getCONTROL().friendflag = false;
ClientController.getCONTROL().leaderflag = false;
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
ClientController.getCONTROL().gameflag = true;
ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().exitflag = false;
ClientController.getCONTROL().friendflag = false;
ClientController.getCONTROL().leaderflag = false;
        ai_button = 1;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
        main.getChildren().setAll(pane);
        ClientController.getCONTROL().AIRequest();
    }

    public void serverCloseMain(String s) {

        if (s.equals(new String("1"))) {

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("server closed !!!!!!!!");
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

                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            main.getChildren().setAll(pane);

                            // get a handle to the stage
//    Stage stage = (Stage) closeButton.getScene().getWindow();
//    // do what you have to do
//    stage.close();
//                            System.out.println("Status1 " + status);
                        } else {
//                            status.set(0);
                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            main.getChildren().setAll(pane);

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
//                    latchToWaitForJavaFx.countDown();
                }

            });

        }
    }

}
