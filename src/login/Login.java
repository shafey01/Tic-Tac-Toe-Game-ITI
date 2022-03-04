/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package login;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mustafa Raed
 */
public class Login extends Application {

    // String musicFile = "C:\\Users\\go\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\sound1.mp3";     // For example
//    Media sound = new Media(new File(musicFile).toURI().toString());
    //   MediaPlayer mediaPlayer = new MediaPlayer(sound);
    public static Login cont;

    @Override
    public void start(Stage stage) throws Exception {
        cont = this;

        Parent root = FXMLLoader.load(getClass().getResource("Loading.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/Img/hobbies-and-free-time.png"));

        stage.getIcons().add(icon);
        Scene scene = new Scene(root);

        stage.setTitle("Tic Tac Toe");
        //stage.initStyle(StageStyle.UNDECORATED);
//        stage.setFullScreen(true);
        stage.setMaximized(true);
//        mediaPlayer.play();

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
