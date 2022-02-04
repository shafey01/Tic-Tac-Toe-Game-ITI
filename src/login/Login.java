/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package login;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mustafa Raed
 */
public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Loading.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("hobbies-and-free-time.png"));

        stage.getIcons().add(icon);
        Scene scene = new Scene(root);

        stage.setTitle("Tic Tac Toe");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
}
