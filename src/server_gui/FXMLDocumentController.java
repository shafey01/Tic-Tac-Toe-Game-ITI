/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package server_gui;

import ClientServerNew.Server;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Mustafa Raed
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private TableView<?> DB_TableView;

    @FXML
    private MenuItem Properties_menuItem;

    @FXML
    private MenuItem aboutServer_menuItem;

    @FXML
    private TextField activePlayers_TextField;

    @FXML
    private MenuItem close_menuItem;

    @FXML
    private MenuItem connectWithUs_menuItem;

    @FXML
    private MenuItem copy_menuItem;

    @FXML
    private MenuItem delete_menuItem;

    @FXML
    private TextField highestScore_TextField;

    @FXML
    private MenuItem new_menuItem;

    @FXML
    private TextField numberOfPlayers_TextField;

    @FXML
    private MenuItem open_menuItem;

    @FXML
    private TableColumn<?, ?> password_column;

    @FXML
    private TextField playersInGame_TextField;

    @FXML
    private MenuItem refresh_menuItem;

    @FXML
    private MenuItem saveCopy_menuItem;

    @FXML
    private TableColumn<?, ?> score_column;

    @FXML
    private CheckMenuItem start_menuItem;

    @FXML
    private TableColumn<?, ?> state_column;

    @FXML
    private CheckMenuItem stop_menuItem;

    @FXML
    private TableColumn<?, ?> userName_column;
Server server;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//server = new Server();
    }    
    
}
