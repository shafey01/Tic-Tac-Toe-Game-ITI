/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package server_gui;

import ClientServerNew.ClientController;
import ClientServerNew.Server;
import DataBase.UserPkg.ContactDAO;
import DataBase.UserPkg.ContactPerson;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import static login.FriendController.friendControl;

/**
 *
 * @author Mustafa Raed
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private TableView<ContactPerson> DB_TableView;

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
    private TextField playersInGame_TextField;

    @FXML
    private MenuItem refresh_menuItem;

    @FXML
    private MenuItem saveCopy_menuItem;

    @FXML
    private TableColumn<ContactPerson, Integer> score_column;

    @FXML
    private CheckMenuItem start_menuItem;

    @FXML
    private TableColumn<ContactPerson, String> state_column;

    @FXML
    private CheckMenuItem stop_menuItem;

    @FXML
    private TableColumn<ContactPerson, String> userName_column;

    FXMLDocumentController serverGui;
    Server server;
    ContactDAO c;
   
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        serverGui = this;
//        userName_column = new TableColumn<>("user Name");
//
//        score_column = new TableColumn<>("Total Score");
//
//        state_column = new TableColumn<>("State");
//
//        userName_column.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
         System.out.println("here");
        
        

       // stateShow();


    }
@FXML
    private void refresh_menuItemAction(ActionEvent event)  {
        System.out.println("Button Action\n");
    }
    
  


    private void stateShow() {
 

       c = new ContactDAO();
        

        Vector<ContactPerson> contactPerson = c.getUsers();
       

        userName_column.setCellValueFactory(new PropertyValueFactory<>("username"));
        score_column.setCellValueFactory(new PropertyValueFactory<>("total_score"));
        state_column.setCellValueFactory(new PropertyValueFactory<>("State"));
        DB_TableView.getColumns().add(userName_column);
        DB_TableView.getColumns().add(score_column);
        DB_TableView.getColumns().add(state_column);
         
        System.out.println("here");
        String[] state = Server.getServer().serverRequestState();  
             
        for (ContactPerson i : contactPerson) {

            if (Arrays.asList(state).contains(i.getUsername())) {
            
          DB_TableView.getItems().add(new ContactPerson(i.getUsername(), i.getTotal_score(), i.getState()));
            
            }
        }



    }


}
 
    

