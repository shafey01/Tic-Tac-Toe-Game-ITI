/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class Game_v3Controller implements Initializable {

//    public static Game_v3Controller gameControl;
    @FXML
    private Button Exit_btn;

    @FXML
    private Label Oscore;

    @FXML
    private Label Xscore;

//    @FXML
//    private Button btn_00;
//
//    @FXML
//    private Button btn_01;
//
//    @FXML
//    private Button btn_02;
//
//    @FXML
//    private Button btn_10;
//
//    @FXML
//    private Button btn_11;
//
//    @FXML
//    private Button btn_12;
//
//    @FXML
//    private Button btn_20;
//
//    @FXML
//    private Button btn_21;
//
//    @FXML
//    private Button btn_22;

    @FXML
    private ImageView playerO_Img;

    @FXML
    private ImageView playerX_Img;

    @FXML
    private Button sound_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        gameControl = this;
    }
//
//    @FXML
//    void move_00(ActionEvent event) {
//
//        System.out.println("login.Game_v3Controller.move_00()");
//        ClientController.getCONTROL().AIMove(new String("0"), new String("0"));
//
//    }
//
//    @FXML
//    void move_01(ActionEvent event) {
//        ClientController.getCONTROL().AIMove(new String("0"), new String("1"));
//
//    }
//
//    @FXML
//    void move_02(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("0", "2");
//
//    }
//
//    @FXML
//    void move_10(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("1", "0");
//
//    }
//
//    @FXML
//    void move_11(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("1", "1");
//
//    }
//
//    @FXML
//    void move_12(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("1", "2");
//
//    }
//
//    @FXML
//    void move_20(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("2", "0");
//
//    }
//
//    @FXML
//    void move_21(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("2", "1");
//
//    }
//
//    @FXML
//    void move_22(ActionEvent event) {
//        ClientController.getCONTROL().AIMove("2", "2");
//
//    }
//
//    @FXML
//    public void aiMove(String rowIndex, String columnIndex) {
//
//        String idAi = new String(rowIndex + columnIndex);
//        System.err.println(idAi);
//        if (idAi.equals(new String("00"))) {
//
////
//        } else if (idAi.equals(new String("01"))) {
//
//        } else if (idAi.equals(new String("02"))) {
//
//        } else if (idAi.equals(new String("10"))) {
//
//        } else if (idAi.equals(new String("11"))) {
//
//        } else if (idAi.equals(new String("12"))) {
//
//        } else if (idAi.equals(new String("20"))) {
//
//        } else if (idAi.equals(new String("21"))) {
//
//        } else if (idAi.equals(new String("22"))) {
//
//        }
//
//    }

}
