/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.login;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Dell
 */
public class LoginDAO implements Serializable{
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    public void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(ptmt != null){
            ptmt.close();
        }
        if(con != null){
            con.close();
        }
    }
    public LoginDTO checkLogin(String username, String password) throws NamingException, SQLException{
        LoginDTO user = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select username, fullname, role "
                    + "from Login "
                    + "where username = ? and password = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            rs = ptmt.executeQuery();
            if(rs.next()){
                user = new LoginDTO(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        }finally{
            closeConnection();
        }
        return user;
    }
}
