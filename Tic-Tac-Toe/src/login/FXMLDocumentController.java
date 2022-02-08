/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package login;

import ClientServerNew.Client;
import ClientServerNew.ClientController;
import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mustafa Raed
 */
public class FXMLDocumentController implements Initializable {

    public ClientController client;

    public static FXMLDocumentController logincontroller;
    @FXML
    private Text wrong_text_Login;
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


    @FXML
    private Label loginStatusLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logincontroller = this;

        try {

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
        try {
            client = new ClientController();

        } catch (Exception e) {
            e.printStackTrace();
        }
        client.getCONTROL().sendLoginRequest(userNameLogin, passwordLogin);
        System.out.println("5" + client);
        System.out.println("6" + client.getCONTROL());
    }

 

    public void sendToControllerLogin(int s) throws IOException {
        System.out.println("before if");

        if (s == 1) {
//redirect menu page
            System.out.println("Login Success");

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                        rootPane.getChildren().setAll(pane);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (s == 0) {
//type this message in the same and reload

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
//                        wrong_text_Login.setText("Wrong user name or password");
                        loginStatusLabel.setText("Wrong user name or password");
                        userNameTextFieldSignIn.setStyle("-fx-border-color: red;-fx-border-width: 0 0 1 0;");
                        passwordTextFieldSignIn.setStyle("-fx-border-color: red;-fx-border-width: 0 0 1 0;");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            System.out.println("Wrong user name or password");

        } else {
//type this message in the same and reload

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
//                        wrong_text_Login.setText("Please try again Later");
                        loginStatusLabel.setText("Please try again Later");
                        userNameTextFieldSignIn.setStyle("-fx-border-color: red;-fx-border-width: 0 0 1 0;");
                        passwordTextFieldSignIn.setStyle("-fx-border-color: red;-fx-border-width: 0 0 1 0;");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            System.err.println("Please try again Later");
        }

    }

}
