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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class FriendController implements Initializable {
 @FXML
private AnchorPane friend;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
@FXML
private void loadsetting(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("Setting.fxml"));
friend.getChildren().setAll(pane);
}
@FXML
private void loadleaderborde(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("LeaderBorde.fxml"));
friend.getChildren().setAll(pane);
}
@FXML
private void loadexite2(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("Exit1.fxml"));
friend.getChildren().setAll(pane);
}
@FXML
private void loadfriend(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
friend.getChildren().setAll(pane);
}
}
