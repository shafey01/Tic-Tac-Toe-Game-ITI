/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.UserPkg;

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
public class ContactDAO {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/gamedb";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "shafey";
    public static final String PASS = "shafey";
    Connection con;
    PreparedStatement pst = null;
    Vector<ContactPerson> contactPerson;

    public ContactDAO() {
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

    public Vector<ContactPerson> getUsers() {
 System.out.println("DataBase.UserPkg.ContactDAO.getUsers()");
        this.connect();
        contactPerson = new Vector<ContactPerson>();
        try {
           
            Statement stat = con.createStatement();
            String query = "select * from user";
            ResultSet st = stat.executeQuery(query);
            while (st.next()) {

                ContactPerson contact = new ContactPerson(st.getInt("user_id"), st.getString("username"), st.getString("password"), st.getInt("total_score"),"online");
                contactPerson.add(contact);
            }
            stat.close();
            this.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contactPerson;
    }

    public Vector<ContactPerson> getUserByName(String name) {
        contactPerson = new Vector<ContactPerson>();
        try {
            pst = con.prepareStatement("select * from user where username = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                ContactPerson contact = new ContactPerson(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getInt("total_score"));
                contactPerson.add(contact);
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactPerson;
    }

    public void createNewUser(String userName, String password) throws SQLException {

        ContactPerson newPerson = new ContactPerson(userName, password);

        PreparedStatement pst = con.prepareStatement("INSERT INTO user(username, password, total_score) VALUES (  ? ,? , 0 )");
        pst.setString(1, newPerson.getUsername());
        pst.setString(2, newPerson.getPassword());
        pst.execute();

    }

    public void UpdateUserTolalScore(ContactPerson user) {
        try {
            String query = "UPDATE user SET total_score = ? WHERE user_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, user.getTotal_score());
            pst.setInt(2, user.getUser_id());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getUserByNameBoolean(String name, String password) {
        this.connect();

        try {
            pst = con.prepareStatement("select * from user where username = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (rs.next() == false) {

                createNewUser(name, password);
                pst.close();
                this.closeConnection();
                return 1;
            } else {
                pst.close();
                this.closeConnection();
                return 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);

            return -1;
        }

    }

    public int getUserIdByName(String name, String password) {
        this.connect();

        try {
            pst = con.prepareStatement("select * from user where username = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == false) {

                pst.close();
                this.closeConnection();
                System.out.println("first if");
                return 0;
            } else {

                if (rs.getString("password").equals(new String(password))) {
                   
                    pst.close();
                    this.closeConnection();

                    return 1;

                } else {
                    pst.close();
                    this.closeConnection();
                    System.out.println("else ");
                    return 0;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

public int getUserIdByName(String name) {
        this.connect();

        try {
            pst = con.prepareStatement("select * from user where username = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == false) {

                pst.close();
                this.closeConnection();
                System.out.println("first if");
                return 0;
            } else {

                int id = rs.getInt("user_id");
                    pst.close();
                    this.closeConnection();
                    System.out.println("else ");
                    return id;
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }


//    public static void main(String[] args) throws SQLException {
////        ContactDAO cd = new ContactDAO();
////        ContactPerson user = new ContactPerson();
////        cd.connect();
////        Vector<ContactPerson> tbData = cd.getUserByName("menna");
//////        ContactPerson person = new ContactPerson();
//////        person.setUsername("menna");
//////        person.setPassword("1234");
//////        cd.createNewUser(person);
//////        user.setTotal_score(14);
//////        user.setUser_id(1);
//////        cd.UpdateUserTolalScore(user);
////        cd.closeConnection();
////        for (ContactPerson i : tbData) {
////            System.out.println(i.getUser_id() + " " + i.getUsername() + " " + i.getPassword() + " " + i.getTotal_score());
////        }
//    }
}
