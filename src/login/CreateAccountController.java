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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class CreateAccountController implements Initializable {

    @FXML
    private ImageView back_bt;

    @FXML
    private Button create_bt;

    @FXML
    private BorderPane createaccount;

    @FXML
    private PasswordField passwordTextField_CA;

    @FXML
    private TextField userNameTextField_CA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadaccount(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        createaccount.getChildren().setAll(pane);
    }

    @FXML
    private void loadend(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        createaccount.getChildren().setAll(pane);
    }
}
