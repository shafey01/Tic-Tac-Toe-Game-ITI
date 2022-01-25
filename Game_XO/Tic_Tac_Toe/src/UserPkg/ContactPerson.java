/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPkg;

/**
 *
 * @author user
 */
public class ContactPerson {
    int user_id;
    String username;
    String password;
    int total_score;
    public ContactPerson(String username, String password) {
        this.username = username;
        this.password = password;
        this.total_score = 0;
    }
    public ContactPerson(int user_id, String username, String password, int total_score) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.total_score = total_score;
    }

    public ContactPerson() {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotal_score() {
        return total_score;
    }
    
    
}
