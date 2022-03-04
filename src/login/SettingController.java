package login;

import ClientServerNew.ClientController;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

public class SettingController implements Initializable {

    public static SettingController settingcont;

    @FXML
    private AnchorPane howtoplay;

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
    private Button github_bt;

    @FXML
    private BorderPane setting;

    @FXML
    private AnchorPane topbar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingcont = this;
        // TODO
    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
ClientController.getCONTROL().settingflag = true;
ClientController.getCONTROL().exitflag = false;
ClientController.getCONTROL().gameflag = false;

ClientController.getCONTROL().friendflag = false;
ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().exitflag = false;
ClientController.getCONTROL().gameflag = false;

ClientController.getCONTROL().friendflag = false;
ClientController.getCONTROL().leaderflag = true;
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadgithub(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://github.com/shafey01/Tic-Tac-Toe-Game-ITI/tree/master"));
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().exitflag = true;
ClientController.getCONTROL().gameflag = false;

ClientController.getCONTROL().friendflag = false;
ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().exitflag = false;
ClientController.getCONTROL().gameflag = false;

ClientController.getCONTROL().friendflag = true;
ClientController.getCONTROL().leaderflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        setting.getChildren().setAll(pane);
    }

    public void inviteStatus(String s) throws IOException {

        if (s.equals(new String("1"))) {

            BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
            topbar.getChildren().setAll(pane);
        }

    }

    public void serverCloseToSettings(String s) {

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

//                            status.set(1);


                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            setting.getChildren().setAll(pane);


 
                        } else {
//                            status.set(0);
                            BorderPane pane;
                            pane = FXMLLoader.load(getClass().getResource("serverclose.fxml"));
                            setting.getChildren().setAll(pane);

 

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
