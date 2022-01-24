/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPkg;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public static final String DB_URL = "jdbc:mysql://localhost:3306/" + "gamedb?zeroDateTimeBehavior=convertToNull";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "";
    Connection con;
    Vector<ContactPerson> contactperson;
    
    public ContactDAO() {
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
    
    public Vector<ContactPerson> getContacts() {
         contactperson = new Vector<ContactPerson>();
        try {
            Statement stat = con.createStatement();
            String query = "select * from user";
            ResultSet st = stat.executeQuery(query);
            while(st.next()) {
                int id = st.getInt(1);
                String username = st.getString(2);
                String password = st.getString(3);
                String total_score = st.getString(4);
                ContactPerson contact = new ContactPerson(id,username,password,total_score);
                contactperson.add(contact);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactperson;
    }
    
    public static void main(String[] args) {
        ContactDAO cd = new ContactDAO();
        cd.connect();
        Vector<ContactPerson> tbData = cd.getContacts();
        cd.closeConnection();
        for(ContactPerson i : tbData) {
            System.out.println(i.getUser_id()+" "+i.getUsername()+" "+i.getPassword()+" "+i.getTotal_score());
        }
    }
}
