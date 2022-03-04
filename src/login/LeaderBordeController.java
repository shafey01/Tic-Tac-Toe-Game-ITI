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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class LeaderBordeController implements Initializable {

    ContactDAO c;
    ClientController control;

   

    public static LeaderBordeController LeaderBordeController;

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
    private Button refresh;

    @FXML
    private TableColumn<ContactPerson, String> userNameColumn;
    @FXML
    private TableColumn<ContactPerson, Integer> score;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LeaderBordeController = this;
        userNameColumn = new TableColumn<>("user Name");
        score = new TableColumn<>("Total Score");
//userNameColumn.setText("asdf");
        userNameColumn.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        score.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        score.setCellValueFactory(new PropertyValueFactory<>("total_score"));

        leaderBordeTableView.getColumns().add(userNameColumn);
        leaderBordeTableView.getColumns().add(score);
        leaderBoradShow();

    }

    @FXML
    void refresh_Action(ActionEvent event) {
        leaderBoradShow();
    }

    @FXML
    private void loadsetting(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = true;
ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().leaderflag = false;
        ClientController.getCONTROL().exitflag = false;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadleaderborde(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().exitflag = false;
        ClientController.getCONTROL().leaderflag = true;
        BorderPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadexite2(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().friendflag = false;
        ClientController.getCONTROL().leaderflag = false;
        ClientController.getCONTROL().exitflag = true;

        BorderPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    @FXML
    private void loadfriend(ActionEvent event) throws IOException {
        ClientController.getCONTROL().settingflag = false;
        ClientController.getCONTROL().leaderflag = false;
ClientController.getCONTROL().gameflag = false;

        ClientController.getCONTROL().exitflag = false;

        ClientController.getCONTROL().friendflag = true;
        BorderPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
        leaderborde.getChildren().setAll(pane);
    }

    public void data() {

    }

    public void leaderBoradShow() {
        c = new ContactDAO();

        leaderBordeTableView.getItems().clear();
        Vector<ContactPerson> contactPerson = c.getUsers();
        System.out.println(contactPerson.get(0).getUsername());

        for (ContactPerson i : contactPerson) {
            leaderBordeTableView.getItems().add(new ContactPerson(i.getUsername(), i.getTotal_score()));
        }

    }

    public void inviteStatus(String s) throws IOException {

        if (s.equals(new String("1"))) {

            BorderPane pane = FXMLLoader.load(getClass().getResource("Game.fxml"));
            topbar.getChildren().setAll(pane);
        }

    }

public void serverCloseToLeader(String s) {

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
