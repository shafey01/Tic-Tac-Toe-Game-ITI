/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import DataBase.UserPkg.ContactDAO;
import DataBase.UserPkg.ContactPerson;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class FriendController implements Initializable {

    ContactDAO c;
    ClientController clientcontrol;
    public static FriendController friendControl;

    public String[] state;

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
    private Button refresh;

    @FXML
    private Button bt_setting;

    @FXML
    private BorderPane friend;

    @FXML
    private Button invite_bt;

    @FXML
    private TableView<ContactPerson> leaderBordeTableView;

    @FXML
    private TableColumn<ContactPerson, String> userNameColumn;
    @FXML
    private TableColumn<ContactPerson, Integer> score;

    @FXML
    private TableColumn<ContactPerson, String> stateBoard;

    @FXML
    private AnchorPane topbar;

    @FXML
    private TextField userNameTexetField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        friendControl = this;
        userNameColumn = new TableColumn<>("user Name");

        score = new TableColumn<>("Total Score");

        stateBoard = new TableColumn<>("State");
//userNameColumn.setText("asdf");
        userNameColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        score.setCellValueFactory(new PropertyValueFactory<>("total_score"));
        stateBoard.setCellValueFactory(new PropertyValueFactory<>("State"));
        leaderBordeTableView.getColumns().add(userNameColumn);
        leaderBordeTableView.getColumns().add(score);
        leaderBordeTableView.getColumns().add(stateBoard);

        System.out.println("2" + ClientController.getCONTROL());

        stateShow();

    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        friend.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        friend.getChildren().setAll(pane);
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        friend.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        friend.getChildren().setAll(pane);
    }

    public void stateShow() {
        c = new ContactDAO();
        ClientController.getCONTROL().sendStateRequest();

        Vector<ContactPerson> contactPerson = c.getUsers();

        leaderBordeTableView.getItems().clear();
        // state = ClientController.getCONTROL().sendState2();
        String[] state = ClientController.getCONTROL().sendState2();

        for (ContactPerson i : contactPerson) {

            if (Arrays.asList(state).contains(i.getUsername())) {

                leaderBordeTableView.getItems().add(new ContactPerson(i.getUsername(), i.getTotal_score(), i.getState()));

            }
        }

    }

    @FXML
    void inviteButton(ActionEvent event) throws IOException {

        String userName = userNameTexetField.getText();

//        ClientController.getCONTROL().gameStartControl(userName);


ClientController.getCONTROL().invitationControl(userName);

        userNameTexetField.setText("");

    }

    public void inviteStatus(String s) throws IOException {

        if (s.equals(new String("1"))) {

            BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
            topbar.getChildren().setAll(pane);
        }

    }

//    public void game() throws IOException {
//
//        BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
//        topbar.getChildren().setAll(pane);
//    }

    @FXML
    void refresh_Action(ActionEvent event) {
        stateShow();
    }
}