/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package server_gui;

import ClientServerNew.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mustafa Raed
 */
public class Server_GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
  Image icon = new Image(getClass().getResourceAsStream("/Img/icons8_server_128px.png"));

        stage.getIcons().add(icon);
       

        stage.setTitle("Tic Tac Toe / Server");
        new Thread() {

            @Override
            public void run() {

                new Server();

            }

        }.start();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

 Scene scene = new Scene(root);
      

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
