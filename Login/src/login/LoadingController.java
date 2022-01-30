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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LoadingController implements Initializable {

    @FXML
private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      new SplashScreen().start();
    }    

class SplashScreen extends Thread {

public void run(){
try{
Thread.sleep(5000);
Platform.runLater(new Runnable() {

public void run(){
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
stage.setScene(scene);
stage.show();

rootPane.getScene().getWindow().hide();

}

});


}
catch(InterruptedException ex){


}

}
}
    
}
