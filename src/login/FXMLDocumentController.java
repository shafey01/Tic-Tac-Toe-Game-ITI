/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login;

import ClientServerNew.Client;
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

    Client client;

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

        try {
            client = new Client();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void loadCreate(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    @FXML
    private void loadenter(ActionEvent event) throws IOException {

        String userNameLogin = new String();
        String passwordLogin = new String();

        userNameLogin = userNameTextFieldSignIn.getText();
        passwordLogin = passwordTextFieldSignIn.getText();

        client.sendRequestToServer("login", userNameLogin, passwordLogin);

        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void sendToControllerLogin(int s) throws IOException {
        System.out.println("before if");

        if (s == 1) {
//redirect menu page
            System.out.println("Login Success");

//            BorderPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//            createaccount.getChildren().setAll(pane);
        } else if (s == 0) {
//type this message in the same and reload
            System.out.println("Wrong user name or password");

        } else {
//type this message in the same and reload
            System.err.println("Please try again Later");
        }

    }

}
