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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<ContactPerson> leaderBordeTableView;

    @FXML
    private BorderPane leaderborde;

    @FXML
    private AnchorPane topbar;

    @FXML
    private TableColumn<ContactPerson, String> userNameColumn;
    @FXML
    private TableColumn<ContactPerson, Integer> score;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        userNameColumn = new TableColumn<>("user Name");
        score = new TableColumn<>("Total Score");
//userNameColumn.setText("asdf");
        userNameColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");

        leaderBoradShow();

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

    public void leaderBoradShow() {
        c = new ContactDAO();
        Vector<ContactPerson> contactPerson = c.getUsers();
        System.out.println(contactPerson.get(0).getUsername());

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        score.setCellValueFactory(new PropertyValueFactory<>("total_score"));

        leaderBordeTableView.getColumns().add(userNameColumn);
        leaderBordeTableView.getColumns().add(score);

        for (ContactPerson i : contactPerson) {
            leaderBordeTableView.getItems().add(new ContactPerson(i.getUsername(), i.getTotal_score()));
        }

    }

}
