/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.GamePkg;

import DataBase.UserPkg.ContactDAO;
import DataBase.UserPkg.ContactPerson;
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

    public static final String DB_URL = "jdbc:mysql://localhost:3306/gamedb";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "shafey";
    public static final String PASS = "shafey";

    Connection con;
    PreparedStatement pst = null;
    Vector<GameTable> gameTable;

    public GameDAO() {
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
            while (st.next()) {

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

    public int createNewGame() throws SQLException {
        this.connect();
        GameTable newGame = new GameTable();
        String generatedColumns[] = {"id"};
        PreparedStatement pst = con.prepareStatement("INSERT INTO game(map) VALUES ( ? )", generatedColumns);
        pst.setString(1, Arrays.deepToString(newGame.getMap()));
        int affectedRows = pst.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                newGame.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
//            pst.execute();
            pst.close();
            this.closeConnection();

        }
        return newGame.getId();
    }

    public void updateGameMap(int[][] map,int gameId) throws SQLException {
        this.connect(); 
        PreparedStatement pst = con.prepareStatement("UPDATE game SET map = ? WHERE id = ?");
        pst.setString(1, Arrays.deepToString(map));
        pst.setInt(2, gameId);

        pst.execute();
        pst.close();
        this.closeConnection();

    }

    public void setGameWinner(int gameId, int winnerId) throws SQLException {
        this.connect();
        PreparedStatement pst = con.prepareStatement("UPDATE game SET winnerId = ? WHERE id = ?");
        pst.setInt(1, winnerId);
        pst.setInt(2, gameId);

        pst.execute();
        pst.close();
        this.closeConnection();
    }

    private int[][] stringToArray(String string) {
        String[] rows = string.split("\\[|],|\\]");
        String[][] matrix = new String[rows.length][];
        int[][] Array = new int[3][3];
        int r = 0, i = 0;
        for (String row : rows) {
            if (!(row.equals("")) && row.length() != 1) {
                matrix[r++] = row.split(",\\s");
            }
        }
        for (r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Array[r][c] = Integer.parseInt(matrix[r][c]);
            }
        }
        return Array;
    }

    public static void main(String[] args) throws SQLException {

        GameDAO c = new GameDAO();
        int x = c.createNewGame();
        System.out.println(x);

    }
}
