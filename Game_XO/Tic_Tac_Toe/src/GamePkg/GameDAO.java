/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePkg;

import UserPkg.ContactDAO;
import UserPkg.ContactPerson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Menna
 */
public class GameDAO {
     public static final String DB_URL = "jdbc:mysql://localhost:3306/" + "gamedb?zeroDateTimeBehavior=convertToNull";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "";
    Connection con;
    PreparedStatement pst=null;
    Vector<GameTable> gameTable;
    
    public GameDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception ex) {
            ex.getMessage();
        }
    }
    
    public void connect() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<GameTable> getGameInfo() {
         gameTable = new Vector<GameTable>();
        try {
            Statement stat = con.createStatement();
            String query = "select * from game";
            ResultSet st = stat.executeQuery(query);
            while(st.next()) {
               
                GameTable game = new GameTable();
                game.setId(st.getInt("id"));
                game.setWinnerId(st.getInt("winnerId"));
                game.setMap(stringToArray(st.getString("map")));
                gameTable.add(game);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameTable;
    }
    public void createNewGame(GameTable newGame) throws SQLException{

        PreparedStatement pst= con.prepareStatement("INSERT INTO game(map) VALUES ( ? )");
        pst.setString(1 ,Arrays.deepToString(newGame.getMap()));

        pst.execute();
    
}
    public void updateGameMap(GameTable game) throws SQLException{

        PreparedStatement pst= con.prepareStatement("UPDATE game SET map = ? WHERE id = ?");
        pst.setString(1 ,Arrays.deepToString(game.getMap()));
        pst.setInt(2 ,game.getId());


        pst.execute();
    
}
    public void setGameWinner(GameTable game,int winnerId) throws SQLException{

        PreparedStatement pst= con.prepareStatement("UPDATE game SET winnerId = ? WHERE id = ?");
        pst.setInt(1 ,winnerId);
        pst.setInt(2 ,game.getId());


        pst.execute();
    
}
private int[][] stringToArray(String string){
    String[] rows = string.split("\\[|],|\\]");
    String[][] matrix = new String[rows.length][];
    int[][] Array = new int[3][3];
    int r = 0,i=0;
    for (String row : rows) {
        if(!(row.equals(""))&& row.length() != 1)
        matrix[r++] = row.split(",\\s");
    }
    for(r=0;r<3;r++)
    {
        for(int c=0;c<3;c++)
        {
          Array[r][c]  = Integer.parseInt(matrix[r][c]);
        }
    }
     return Array;
}
 public static void main(String[] args) throws SQLException {
        GameDAO cd = new GameDAO();
        cd.connect();
       GameTable game = new GameTable();
        cd.createNewGame(game);
 // Vector<GameTable> tbData = cd.getGameInfo();

        cd.closeConnection();
//        for(GameTable i : tbData) {
//            System.out.println(i.getId()+" "+i.getWinnerId()+" "+Arrays.deepToString(i.getMap()));
//        }
    }
}
