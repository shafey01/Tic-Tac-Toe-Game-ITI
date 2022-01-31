/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login;

import java.io.IOException;
import static java.lang.System.out;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Mustafa Raed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Hyperlink CreateAccount;

    @FXML
    private Button login_bt;

    @FXML
    private PasswordField passwordTextFieldSignIn;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField userNameTextFieldSignIn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadCreate(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    @FXML
    private void loadenter(ActionEvent event) throws IOException {
        String A = userNameTextFieldSignIn.getText();
        System.out.println(A);

//AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//rootPane.getChildren().setAll(pane);
    }

}
