/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PlayerDAO {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/" + "gamedb?zeroDateTimeBehavior=convertToNull";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "";
    Connection con;
    PreparedStatement pst = null;
    Vector<PlayerTable> playerTable;

    public PlayerDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<PlayerTable> getPlayerInfo() {
        playerTable = new Vector<PlayerTable>();
        try {
            Statement stat = con.createStatement();
            String query = "select * from player";
            ResultSet st = stat.executeQuery(query);
            while (st.next()) {

                PlayerTable player = new PlayerTable();
                player.setGameId(st.getInt("gameId"));
                player.setPlayerId(st.getInt("playerId"));
                player.setGameScore(st.getInt("gameScore"));
                playerTable.add(player);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerTable;
    }
    
    public Vector<PlayerTable> getPlayerByName(int game) {
         playerTable = new Vector<PlayerTable>();
        try {
            pst= con.prepareStatement("select * from player where gameId = ?");
            pst.setInt(1, game);          
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {

                PlayerTable player = new PlayerTable(rs.getInt("gameId"),rs.getInt("playerId"),rs.getInt("gameScore"));
                playerTable.add(player);
            }
          pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerTable;
    }

    public void createNewUser(PlayerTable newPlayer) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO player(gameId, playerId, gameScore) VALUES (  ? ,? , 0 )");
            pst.setInt(1, newPlayer.getGameId());
            pst.setInt(2, newPlayer.getPlayerId());
            pst.execute();
        }catch(Exception ex) {
            ex.getMessage();
        }
    }
    
    public static void main(String[] args) {
        PlayerDAO pd = new PlayerDAO();
        pd.connect();
        Vector<PlayerTable> tdPlayer = pd.getPlayerInfo();
        pd.closeConnection();
        for(PlayerTable i : tdPlayer) {
            System.out.println(i.getGameId()+" "+i.getPlayerId()+" "+i.getGameScore());
        }
    }
}
