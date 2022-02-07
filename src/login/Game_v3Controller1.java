/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.io.File;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Mustafa Raed
 */
public class Game_v3Controller1 implements Initializable {

    public static Game_v3Controller1 gameControl;
    public static String musicFileLose;
    public static String musicFileWin;
    public static String musicFileClick;
    public MediaPlayer mediaPlayer;
    public Boolean mute = false;
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
    private ImageView img_view00;

    @FXML
    private ImageView img_view01;

    @FXML
    private ImageView img_view02;

    @FXML
    private ImageView img_view10;

    @FXML
    private ImageView img_view11;

    @FXML
    private ImageView img_view12;

    @FXML
    private ImageView img_view20;

    @FXML
    private ImageView img_view21;

    @FXML
    private ImageView img_view22;

    @FXML
    private ImageView youwin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gameControl = this;

        musicFileLose = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\loseSound.mp3";
        musicFileWin = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\winSound.mp3";
        musicFileClick = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\clickSound.mp3";
        playSound(musicFileClick);

    }

    public void changeViewForPlayer(ImageView img) {
        Image image = new Image(getClass().getResourceAsStream("1.png"));
        img.setImage(image);
    }

    public void changeViewForAi(ImageView img) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                BorderPane pane;
                try {
                    Image image = new Image(getClass().getResourceAsStream("2.png"));
                    img.setImage(image);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    void move_00(ActionEvent event) {

        System.out.println("login.Game_v3Controller.move_00()");
        playSound(musicFileClick);
        ClientController.getCONTROL().AIMove(new String("0"), new String("0"));
        changeViewForPlayer(img_view00);
    }

    @FXML
    void move_01(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove(new String("0"), new String("1"));
        changeViewForPlayer(img_view01);

    }

    @FXML
    void move_02(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("0", "2");
        changeViewForPlayer(img_view02);

    }

    @FXML
    void move_10(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("1", "0");
        changeViewForPlayer(img_view10);

    }

    @FXML
    void move_11(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("1", "1");
        changeViewForPlayer(img_view11);

    }

    @FXML
    void move_12(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("1", "2");
        changeViewForPlayer(img_view12);

    }

    @FXML
    void move_20(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("2", "0");

        changeViewForPlayer(img_view20);

    }

    @FXML
    void move_21(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("2", "1");
        changeViewForPlayer(img_view21);

    }

    @FXML
    void move_22(ActionEvent event) {
        playSound(musicFileClick);

        ClientController.getCONTROL().AIMove("2", "2");
        changeViewForPlayer(img_view22);

    }

    public void aiMove(String rowIndex, String columnIndex) {
        System.err.println("aimove");
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {

            changeViewForAi(img_view00);
            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");

            changeViewForAi(img_view01);

        } else if (idAi.equals(new String("02"))) {
            changeViewForAi(img_view02);

            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            changeViewForAi(img_view10);

        } else if (idAi.equals(new String("11"))) {
            changeViewForAi(img_view11);

        } else if (idAi.equals(new String("12"))) {
            changeViewForAi(img_view12);

        } else if (idAi.equals(new String("20"))) {
            changeViewForAi(img_view20);

        } else if (idAi.equals(new String("21"))) {
            changeViewForAi(img_view21);

        } else if (idAi.equals(new String("22"))) {
            changeViewForAi(img_view22);

        }
    }

    public void playSound(String musicFile) {

        if (mute != true) {
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }

    @FXML
    void muteSound(ActionEvent event) {
        mute = !mute;
    }

    public void gameStatuswithAi(String status) {
        if (status.equals(new String("0"))) {
//no body wins
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("noWin.gif"));
                        youwin.setImage(image);
                        playSound(musicFileLose);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (status.equals(new String("1"))) {
//player win
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("win.gif"));
                        youwin.setImage(image);
                        playSound(musicFileWin);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else if (status.equals(new String("-1"))) {
//ai wins
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("aiWin.gif"));
                        youwin.setImage(image);
                        playSound(musicFileLose);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }

    }

}
