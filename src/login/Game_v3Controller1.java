/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class Game_v3Controller1 implements Initializable {

    public static Game_v3Controller1 gameControl;

    @FXML
    private Button Exit_btn;

    @FXML
    private Label Oscore;

    @FXML
    private Label Xscore;

    @FXML
    private Button btn_00;

    @FXML
    private Button btn_01;

    @FXML
    private Button btn_02;

    @FXML
    private Button btn_10;

    @FXML
    private Button btn_11;

    @FXML
    private Button btn_12;

    @FXML
    private Button btn_20;

    @FXML
    private Button btn_21;

    @FXML
    private Button btn_22;

    @FXML
    private ImageView playerO_Img;

    @FXML
    private ImageView playerX_Img;

    @FXML
    private Button sound_btn;
    @FXML
    private ImageView img_view;
    @FXML
    private ImageView img_view2;

    @FXML
    private ImageView img_view20;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gameControl = this;

    }

    @FXML
    void move_00(ActionEvent event) {

        System.out.println("login.Game_v3Controller.move_00()");
        ClientController.getCONTROL().AIMove(new String("0"), new String("0"));
        Image image = new Image(getClass().getResourceAsStream("1.png"));
        img_view.setImage(image);

    }

    @FXML
    void move_01(ActionEvent event) {
        ClientController.getCONTROL().AIMove(new String("0"), new String("1"));

    }

    @FXML
    void move_02(ActionEvent event) {
        ClientController.getCONTROL().AIMove("0", "2");

    }

    @FXML
    void move_10(ActionEvent event) {
        ClientController.getCONTROL().AIMove("1", "0");

    }

    @FXML
    void move_11(ActionEvent event) {
        ClientController.getCONTROL().AIMove("1", "1");

    }

    @FXML
    void move_12(ActionEvent event) {
        ClientController.getCONTROL().AIMove("1", "2");

    }

    @FXML
    void move_20(ActionEvent event) {
        ClientController.getCONTROL().AIMove("2", "0");
        Image image = new Image(getClass().getResourceAsStream("1.png"));
        img_view20.setImage(image);

    }

    @FXML
    void move_21(ActionEvent event) {
        ClientController.getCONTROL().AIMove("2", "1");

    }

    @FXML
    void move_22(ActionEvent event) {
        ClientController.getCONTROL().AIMove("2", "2");

    }

    public void aiMove(String rowIndex, String columnIndex) {
        System.err.println("aimove");
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {

//
            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("02"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("11"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("12"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("20"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("21"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (idAi.equals(new String("22"))) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img_view2.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }

    }

}
