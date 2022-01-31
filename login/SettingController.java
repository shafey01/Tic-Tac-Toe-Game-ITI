package login;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SettingController implements Initializable {

    @FXML
    private AnchorPane Setting;

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
    private RadioButton music_bt;

    @FXML
    private BorderPane setting;

    @FXML
    private AnchorPane topbar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadgithub(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://github.com/shafey01/Tic-Tac-Toe-Game-ITI/tree/master"));
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        setting.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        setting.getChildren().setAll(pane);
    }

}
