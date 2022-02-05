/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import DataBase.UserPkg.ContactDAO;
import DataBase.UserPkg.ContactPerson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class LeaderBordeController implements Initializable {

    ContactDAO c;

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
    private TableView<String> leaderBordeTableView;

    @FXML
    private BorderPane leaderborde;

    @FXML
    private TableColumn<ContactPerson, Integer> scoreColumn;

    @FXML
    private AnchorPane topbar;

    @FXML
    private TableColumn<ContactPerson, String> userNameColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//userNameColumn.setText("asdf");
        c = new ContactDAO();
        Vector<ContactPerson> contactPerson = c.getUsers();
        System.out.println(contactPerson.get(0).getUsername());
//        leaderBordeTableView.getItems().set(1, contactPerson.get(0).getUsername());

    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    public void data() {

    }

//    public static void main(String[] args) {
//        ContactDAO c;
//        c = new ContactDAO();
//        Vector<ContactPerson> contactPerson = c.getUsers();
//
//        for (ContactPerson i : contactPerson) {
//            System.out.println(i.getUser_id() + " " + i.getUsername() + " " + i.getPassword() + " " + i.getTotal_score());
//        }
//
//    }
}
