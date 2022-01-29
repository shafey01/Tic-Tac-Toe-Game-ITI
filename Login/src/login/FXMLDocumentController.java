/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Mustafa Raed
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
 private AnchorPane rootPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
@FXML
private void loadCreate(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
rootPane.getChildren().setAll(pane);

}
@FXML
private void loadend(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
rootPane.getChildren().setAll(pane);
}
@FXML
private void loadenter(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("Friend.fxml"));
rootPane.getChildren().setAll(pane);

}

}
