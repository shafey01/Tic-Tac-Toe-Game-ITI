/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.Client;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class CreateAccountController implements Initializable {

    Client SignupRequest;

    public static CreateAccountController createacount;

    @FXML
    private ImageView back_bt;
    @FXML
    private Text wrong_text;
    @FXML
    private Button create_bt;

    @FXML
    private BorderPane createaccount;

    @FXML
    public PasswordField passwordTextField_CA;

    @FXML
    public TextField userNameTextField_CA;

    @FXML
    private Button close_bt;

//
// Thread th  =     new Thread() {
//
//            @Override
//
//            public void run() {
//                Platform.runLater(new Runnable() {
//
//                    public void run() {
//                        userNameTextField_CA.setText(" ");
//                        passwordTextField_CA.setText(" ");
//
//                    }
//
//                });
//
//            }
//
//        };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createacount = this;
        try {
            SignupRequest = new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @FXML
    void close_fc(ActionEvent event) {
        Stage stage = (Stage) close_bt.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML

    private void getText(ActionEvent event) throws IOException {

        String user = userNameTextField_CA.getText();
        String pass = passwordTextField_CA.getText();

        SignupRequest.sendRequestToServer("signup", user, pass);

        System.out.println(user);
        System.out.println(pass);

    }

//    public void test() throws IOException {
//        System.out.println("test");
//
//
////        BorderPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
////        createaccount.getChildren().setAll(pane);
//    }
//
    public void sendToController(int s) throws IOException {
        System.out.println("before if");

        if (s == 1) {

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        createaccount.getChildren().setAll(pane);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            userNameTextField_CA.setText("");
            passwordTextField_CA.setText("");

            System.out.println("Success");
            System.out.println("Succesvvvvvvvvvvs");

        } else if (s == 0) {
//type this message in the same and reload
            System.out.println("user is already used");
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        wrong_text.setText("user is already used");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else {
//type this message in the same and reload
            System.out.println("Please try again");

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        wrong_text.setText("Please try again Later");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }

    }
}
