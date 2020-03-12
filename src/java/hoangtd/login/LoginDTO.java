/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.login;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class LoginDTO implements Serializable{
    private String username;
    private String fullname;
    private String role;

    public LoginDTO(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
