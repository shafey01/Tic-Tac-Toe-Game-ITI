/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import ClientServerNew.ClientController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public Boolean mute;
    public static int viewFlag;
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
    @FXML
    private AnchorPane topbar;
//.setGraphic(new ImageView(this.getClass().getResource("/img/icons8_invite_50px.png").toString()));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gameControl = this;
        mute = false;
        viewFlag = 0;

        musicFileLose = "/sounds/loseSound.mp3";
        musicFileWin = "/sounds/winSound.mp3";
        musicFileClick = "/sounds/clickSound.mp3";
        playSound(musicFileClick);
        block_view.setVisible(false);

    }

    @FXML
    void close_fc(ActionEvent event) throws IOException {

        

    }


    void exit2(ActionEvent event) {
javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                    topbar.getChildren().setAll(pane);

                    //  playSound(musicFileLose);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void changeViewForPlayer(ImageView img) {

        if (XOFLAG.equals(new String("x"))) {
            Image image = new Image(getClass().getResourceAsStream("/Img/1.png"));
            img.setImage(image);

        } else {
            Image image = new Image(getClass().getResourceAsStream("/Img/2.png"));
            img.setImage(image);

        }
    }

    public void changeViewForAi(ImageView img) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {
                    if (XOFLAG.equals(new String("x"))) {
                        Image image = new Image(getClass().getResourceAsStream("/Img/2.png"));
                        img.setImage(image);

                    } else {
                        Image image = new Image(getClass().getResourceAsStream("/Img/1.png"));
                        img.setImage(image);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void changeBlockView() {

        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                BorderPane pane;
                try {

                    if (viewFlag == 1) {
                        block_view.setVisible(true);
                        System.out.println("nochange");
                    } else {
                        System.out.println("change");
                        block_view.setVisible(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @FXML
    void move_00(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_00.setDisable(true);
            changeViewForPlayer(img_view00);

            ClientController.getCONTROL().AIMove(new String("0"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_00.setDisable(true);

            changeViewForPlayer(img_view00);
            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("0"));

        }

    }

    @FXML
    void move_01(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_01.setDisable(true);
            changeViewForPlayer(img_view01);

            ClientController.getCONTROL().AIMove(new String("0"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_01.setDisable(true);
            changeViewForPlayer(img_view01);

            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("1"));

        }

    }

    @FXML
    void move_02(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_02.setDisable(true);
            changeViewForPlayer(img_view02);

            ClientController.getCONTROL().AIMove(new String("0"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_02.setDisable(true);
            changeViewForPlayer(img_view02);

            ClientController.getCONTROL().MultiplayerMove(new String("0"), new String("2"));

        }

    }

    @FXML
    void move_10(ActionEvent event) {
        changeBlockView();
        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_10.setDisable(true);
            changeViewForPlayer(img_view10);

            ClientController.getCONTROL().AIMove(new String("1"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_10.setDisable(true);
            changeViewForPlayer(img_view10);

            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("0"));

        }

    }

    @FXML
    void move_11(ActionEvent event) {
        changeBlockView();
        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_11.setDisable(true);
            changeViewForPlayer(img_view11);

            ClientController.getCONTROL().AIMove(new String("1"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_11.setDisable(true);
            changeViewForPlayer(img_view11);

            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("1"));

        }

    }

    @FXML
    void move_12(ActionEvent event) {
        changeBlockView();
        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_12.setDisable(true);
            changeViewForPlayer(img_view12);

            ClientController.getCONTROL().AIMove(new String("1"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_12.setDisable(true);
            changeViewForPlayer(img_view12);

            ClientController.getCONTROL().MultiplayerMove(new String("1"), new String("2"));

        }

    }

    @FXML
    void move_20(ActionEvent event) {
        changeBlockView();

        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_20.setDisable(true);
            changeViewForPlayer(img_view20);

            ClientController.getCONTROL().AIMove(new String("2"), new String("0"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_20.setDisable(true);
            changeViewForPlayer(img_view20);

            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("0"));

        }

    }

    @FXML
    void move_21(ActionEvent event) {
        changeBlockView();
        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_21.setDisable(true);
            changeViewForPlayer(img_view21);

            ClientController.getCONTROL().AIMove(new String("2"), new String("1"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_21.setDisable(true);
            changeViewForPlayer(img_view21);

            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("1"));

        }

    }

    @FXML
    void move_22(ActionEvent event) {
        changeBlockView();
        playSound(musicFileClick);

        if (gameFlag.equals(new String("ai"))) {
            viewFlag = 1;
            btn_22.setDisable(true);
            changeViewForPlayer(img_view22);

            ClientController.getCONTROL().AIMove(new String("2"), new String("2"));
        } else if (gameFlag.equals(new String("normal"))) {
            viewFlag = 1;
            btn_22.setDisable(true);
            changeViewForPlayer(img_view22);

            ClientController.getCONTROL().MultiplayerMove(new String("2"), new String("2"));

        }

    }

    @FXML
    void muteSound2(ActionEvent event) {
        mute = !mute;
        if (mute == true) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BorderPane pane;
                    try {
                        Image image = new Image(getClass().getResource("/Img/mute.png").toString());

                        muteSoundImage.setImage(image);
                        mute = true;
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
//                        Image image = new Image(getClass().getResourceAsStream("icons8_speaker_64px.png"));
                        Image image = new Image(getClass().getResource("/Img/icons8_speaker_64px.png").toString());

                        muteSoundImage.setImage(image);
                        mute = false;

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
    }

//    @FXML
//    void exit(ActionEvent event) throws IOException {
//
//        BorderPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        topbar.getChildren().setAll(pane);
//
//    }

    public void aiMove(String rowIndex, String columnIndex) throws InterruptedException {
        Thread.sleep(1000);
        System.err.println("aimove");
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {
            viewFlag = 0;
            btn_00.setDisable(true);
            changeViewForAi(img_view00);
            changeBlockView();

            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");
            viewFlag = 0;
            System.out.println("view flag " + viewFlag);
            btn_01.setDisable(true);
            changeViewForAi(img_view01);
            changeBlockView();

        } else if (idAi.equals(new String("02"))) {
            viewFlag = 0;
            btn_02.setDisable(true);

            changeViewForAi(img_view02);
            changeBlockView();

            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            viewFlag = 0;

            btn_10.setDisable(true);
            changeViewForAi(img_view10);
            changeBlockView();

        } else if (idAi.equals(new String("11"))) {
            viewFlag = 0;

            btn_11.setDisable(true);
            changeViewForAi(img_view11);
            changeBlockView();

        } else if (idAi.equals(new String("12"))) {
            viewFlag = 0;
            btn_12.setDisable(true);

            changeViewForAi(img_view12);
            changeBlockView();

        } else if (idAi.equals(new String("20"))) {
            viewFlag = 0;

            btn_20.setDisable(true);
            changeViewForAi(img_view20);
            changeBlockView();

        } else if (idAi.equals(new String("21"))) {
            viewFlag = 0;

            btn_21.setDisable(true);
            changeViewForAi(img_view21);
            changeBlockView();

        } else if (idAi.equals(new String("22"))) {
            viewFlag = 0;

            btn_22.setDisable(true);
            changeViewForAi(img_view22);
            changeBlockView();

        }
    }

    public void otherPlayerMove(String rowIndex, String columnIndex) {
        String idAi = new String(rowIndex + columnIndex);
        System.out.println(idAi);
        if (idAi.equals(new String("00"))) {
            viewFlag = 0;
            btn_00.setDisable(true);
            changeViewForAi(img_view00);
            changeBlockView();

            System.out.println("00");
        } else if (idAi.equals(new String("01"))) {
            System.out.println("01");
            viewFlag = 0;

            btn_01.setDisable(true);
            changeViewForAi(img_view01);
            changeBlockView();

        } else if (idAi.equals(new String("02"))) {
            viewFlag = 0;
            btn_02.setDisable(true);

            changeViewForAi(img_view02);
            changeBlockView();

            System.out.println("02");

        } else if (idAi.equals(new String("10"))) {
            viewFlag = 0;

            btn_10.setDisable(true);
            changeViewForAi(img_view10);
            changeBlockView();

        } else if (idAi.equals(new String("11"))) {
            viewFlag = 0;

            btn_11.setDisable(true);
            changeViewForAi(img_view11);
            changeBlockView();

        } else if (idAi.equals(new String("12"))) {
            viewFlag = 0;
            btn_12.setDisable(true);

            changeViewForAi(img_view12);
            changeBlockView();

        } else if (idAi.equals(new String("20"))) {
            viewFlag = 0;

            btn_20.setDisable(true);
            changeViewForAi(img_view20);
            changeBlockView();

        } else if (idAi.equals(new String("21"))) {
            viewFlag = 0;

            btn_21.setDisable(true);
            changeViewForAi(img_view21);
            changeBlockView();

        } else if (idAi.equals(new String("22"))) {
            viewFlag = 0;

            btn_22.setDisable(true);
            changeViewForAi(img_view22);
            changeBlockView();

        }

    }

    public void playSound(String musicFile) {

        if (mute == false) {

            AudioClip mApplause = new AudioClip(this.getClass().getResource("/sounds/clickSound.mp3").toExternalForm());
            mApplause.play();
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
                        AudioClip mApplause = new AudioClip(this.getClass().getResource("/sounds/loseSound.mp3").toExternalForm());
                        mApplause.play();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Draw");
                        alert.setContentText(" ");
                        alert.initStyle(StageStyle.UNDECORATED);
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());

                        dialogPane.getStyleClass().add("myDialog");
                        dialogPane.setGraphic(new ImageView(this.getClass().getResource("/Img/icons8_fight_30px.png").toString()));

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOK) {
                            System.out.println("OK");
                        } else if (result.get() == buttonTypeCancel) {
                            System.out.println("Cancel");
                        }

                        //    playSound(musicFileLose);
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
                        AudioClip mApplause = new AudioClip(this.getClass().getResource("/sounds/winSound.mp3").toExternalForm());
                        mApplause.play();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Win");
                        alert.setContentText(" ");
                        alert.initStyle(StageStyle.UNDECORATED);
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());

                        dialogPane.getStyleClass().add("myDialog");
                        dialogPane.setGraphic(new ImageView(this.getClass().getResource("/Img/icons8_trophy_32px.png").toString()));

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOK) {
                            System.out.println("OK");
                        } else if (result.get() == buttonTypeCancel) {
                            System.out.println("Cancel");
                        }

                        //    playSound(musicFileWin);
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
                        AudioClip mApplause = new AudioClip(this.getClass().getResource("/sounds/loseSound.mp3").toExternalForm());
                        mApplause.play();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("loser");
                        alert.setContentText(" ");
                        alert.initStyle(StageStyle.UNDECORATED);
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());

                        dialogPane.getStyleClass().add("myDialog");
                        dialogPane.setGraphic(new ImageView(this.getClass().getResource("/Img/icons8_loser_32px_1.png").toString()));

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTypeOK) {
                            System.out.println("OK");
                        } else if (result.get() == buttonTypeCancel) {
                            System.out.println("Cancel");
                        }

                        //  playSound(musicFileLose);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }

    }

}
