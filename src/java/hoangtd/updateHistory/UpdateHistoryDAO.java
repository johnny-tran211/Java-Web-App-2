

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.updateHistory;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class UpdateHistoryDAO implements Serializable{
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ptmt != null) {
            ptmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
    public boolean updateDeleteDate(String email, String itemName, Timestamp updateDate, String status) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into UpdateHistory(Email, ItemName, UpdateDate, Status) "
                    + "values(?, ?, ?, ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, email);
            ptmt.setString(2, itemName);
            ptmt.setTimestamp(3, updateDate);
            ptmt.setString(4, status);

            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public ArrayList<UpdateHistoryDTO> loadHistoryDate(String itemName) throws NamingException, SQLException {
        ArrayList<UpdateHistoryDTO> lists = null;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT Email, ItemName, UpdateDate, Status FROM UpdateHistory where ItemName = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, itemName);
            rs = ptmt.executeQuery();
            lists = new ArrayList<>();
            while (rs.next()) {
                lists.add(new UpdateHistoryDTO(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4)));
            }
        } finally {
            closeConnection();
        }
        return lists;
    }
}
