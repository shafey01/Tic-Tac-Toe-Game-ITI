/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class MenuController implements Initializable {

    @FXML
private Circle c1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setRotate(c1,true,360,10);
    }    
    
private void setRotate(Circle c, boolean reverse, int angle, int duration){
RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);
rotateTransition.setAutoReverse(reverse);
rotateTransition.setByAngle(angle);
rotateTransition.setDelay(Duration.seconds(0));
rotateTransition.setRate(3);
rotateTransition.setCycleCount(18);
rotateTransition.play();

}

}
