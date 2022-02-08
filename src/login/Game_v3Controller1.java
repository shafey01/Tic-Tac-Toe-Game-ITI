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
    public static int viewFlag = 0;
    public static String gameFlag = "";
    public static String XOFLAG = "";
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
    @FXML
    private ImageView block_view;
    @FXML
    private ImageView muteSoundImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gameControl = this;

        musicFileLose = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\loseSound.mp3";
        musicFileWin = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\winSound.mp3";
        musicFileClick = "C:\\Users\\MBR\\Desktop\\master\\Tic-Tac-Toe-Game-ITI\\src\\login\\clickSound.mp3";
        playSound(musicFileClick);
        block_view.setVisible(false);

    }

    public void changeViewForPlayer(ImageView img) {

        if (XOFLAG.equals(new String("x"))) {
            Image image = new Image(getClass().getResourceAsStream("1.png"));
            img.setImage(image);

        } else {
            Image image = new Image(getClass().getResourceAsStream("2.png"));
            img.setImage(image);

        }
    }

    public void changeViewForAi(ImageView img) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
                try {
                    if (XOFLAG.equals(new String("x"))) {
                        Image image = new Image(getClass().getResourceAsStream("2.png"));
                        img.setImage(image);

                    } else {
                        Image image = new Image(getClass().getResourceAsStream("1.png"));
                        img.setImage(image);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void changeBlockView() {
//        if (viewFlag == 1) {
//            block_view.setVisible(true);
//            System.out.println("nochange");
//        } else {
//            System.out.println("change");
//            block_view.setVisible(false);
//        }

    }

    @FXML
    void move_00(ActionEvent event) {
        changeBlockView();

        System.out.println("login.Game_v3Controller.move_00()");
        playSound(musicFileClick);
        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view00);
            viewFlag = 1;
            btn_00.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("0"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view00);
            viewFlag = 1;
            btn_00.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("0"));

        }

    }

    @FXML
    void move_01(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view01);
            viewFlag = 1;
            btn_01.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("0"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view01);
            viewFlag = 1;
            btn_01.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("1"));

        }

    }

    @FXML
    void move_02(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view02);
            viewFlag = 1;
            btn_01.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("0"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view02);
            viewFlag = 1;
            btn_01.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("2"));

        }

    }

    @FXML
    void move_10(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view10);
            viewFlag = 1;
            btn_10.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("1"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view10);
            viewFlag = 1;
            btn_10.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("0"));

        }

    }

    @FXML
    void move_11(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view11);
            viewFlag = 1;
            btn_11.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("1"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view11);
            viewFlag = 1;
            btn_11.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("1"));

        }

    }

    @FXML
    void move_12(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view12);
            viewFlag = 1;
            btn_12.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("1"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view12);
            viewFlag = 1;
            btn_12.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("2"));

        }

    }

    @FXML
    void move_20(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view20);
            viewFlag = 1;
            btn_20.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("2"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view20);
            viewFlag = 1;
            btn_20.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("0"));

        }

    }

    @FXML
    void move_21(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view21);
            viewFlag = 1;
            btn_21.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("2"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view21);
            viewFlag = 1;
            btn_21.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("1"));

        }

    }

    @FXML
    void move_22(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            changeViewForPlayer(img_view22);
            viewFlag = 1;
            btn_22.setDisable(true);
            ClientController.getCONTROL().AIMove(new String("2"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            changeViewForPlayer(img_view22);
            viewFlag = 1;
            btn_22.setDisable(true);
            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("2"));

        }

    }

    public void aiMove(String rowIndex, String columnIndex) throws InterruptedException {
        Thread.sleep(1000);
        System.err.println("aimove");
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {
            viewFlag = 0;
            changeViewForAi(img_view00);
            btn_00.setDisable(true);

            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");
            viewFlag = 0;

            changeViewForAi(img_view01);
            btn_01.setDisable(true);

        } else if (idAi.equals(new String("02"))) {
            viewFlag = 0;
            btn_02.setDisable(true);

            changeViewForAi(img_view02);

            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            viewFlag = 0;

            changeViewForAi(img_view10);
            btn_10.setDisable(true);

        } else if (idAi.equals(new String("11"))) {
            viewFlag = 0;

            changeViewForAi(img_view11);
            btn_11.setDisable(true);

        } else if (idAi.equals(new String("12"))) {
            viewFlag = 0;
            btn_12.setDisable(true);

            changeViewForAi(img_view12);

        } else if (idAi.equals(new String("20"))) {
            viewFlag = 0;

            changeViewForAi(img_view20);
            btn_20.setDisable(true);

        } else if (idAi.equals(new String("21"))) {
            viewFlag = 0;

            changeViewForAi(img_view21);
            btn_21.setDisable(true);

        } else if (idAi.equals(new String("22"))) {
            viewFlag = 0;

            changeViewForAi(img_view22);
            btn_22.setDisable(true);

        }
    }

    public void otherPlayerMove(String rowIndex, String columnIndex) {
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {
            viewFlag = 0;
            changeViewForAi(img_view00);
            btn_00.setDisable(true);

            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");
            viewFlag = 0;

            changeViewForAi(img_view01);
            btn_01.setDisable(true);

        } else if (idAi.equals(new String("02"))) {
            viewFlag = 0;
            btn_02.setDisable(true);

            changeViewForAi(img_view02);

            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            viewFlag = 0;

            changeViewForAi(img_view10);
            btn_10.setDisable(true);

        } else if (idAi.equals(new String("11"))) {
            viewFlag = 0;

            changeViewForAi(img_view11);
            btn_11.setDisable(true);

        } else if (idAi.equals(new String("12"))) {
            viewFlag = 0;
            btn_12.setDisable(true);

            changeViewForAi(img_view12);

        } else if (idAi.equals(new String("20"))) {
            viewFlag = 0;

            changeViewForAi(img_view20);
            btn_20.setDisable(true);

        } else if (idAi.equals(new String("21"))) {
            viewFlag = 0;

            changeViewForAi(img_view21);
            btn_21.setDisable(true);

        } else if (idAi.equals(new String("22"))) {
            viewFlag = 0;

            changeViewForAi(img_view22);
            btn_22.setDisable(true);

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
        if (mute == false) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("mute.png"));
                        muteSoundImage.setImage(image);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } else {

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResourceAsStream("icons8_speaker_64px.png"));
                        muteSoundImage.setImage(image);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
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
