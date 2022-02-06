/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Circle c1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();

    }

    class SplashScreen extends Thread {

        public void run() {
            try {
                setRotate(c1, true, 360, 10);
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {

                    public void run() {
                        BorderPane pane = null;
                        try {
                            pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(LoadingController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene scene = new Scene(pane);
                        Stage stage = new Stage();
                        Image icon = new Image(getClass().getResourceAsStream("hobbies-and-free-time.png"));

                        stage.getIcons().add(icon);
                        stage.setTitle("Tic Tac Toe");
                        stage.setFullScreen(true);
                        stage.setScene(scene);
                        stage.show();

                        rootPane.getScene().getWindow().hide();

                    }

                });

            } catch (InterruptedException ex) {

            }

        }
    }

    private void setRotate(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);
        rotateTransition.setAutoReverse(reverse);
        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(3);
        rotateTransition.setCycleCount(18);
        rotateTransition.play();

    }
}
