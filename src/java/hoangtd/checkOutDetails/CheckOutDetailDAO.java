/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.checkOutDetails;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class CheckOutDetailDAO implements Serializable{
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
    public boolean checkOutItem(int id, String productName, int quantity, double price, double total) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into CheckOutDetails(CheckOutID, ProductName, Quantity, Price, Total) "
                    + "values(?, ?, ?, ?, ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.setString(2, productName);
            ptmt.setInt(3, quantity);
            ptmt.setDouble(4, price);
            ptmt.setDouble(5, total);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public ArrayList<CheckOutDetailDTO> loadDetailHistories(int id) throws NamingException, SQLException {
        ArrayList<CheckOutDetailDTO> details = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select ProductName, Quantity, Price, Total "
                    + "from CheckOutDetails "
                    + "where CheckOutID = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            rs = ptmt.executeQuery();
            details = new ArrayList<>();
            while (rs.next()) {
                details.add(new CheckOutDetailDTO(rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4)));
            }
        } finally {
            closeConnection();
        }
        return details;
    }
    public ArrayList<Integer> getIds(String itemName) throws NamingException, SQLException {
        ArrayList<Integer> ids = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select CheckOutID "
                    + "from CheckOutDetails "
                    + "where ProductName LIKE ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + itemName + "%");
            rs = ptmt.executeQuery();
            ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } finally {
            closeConnection();
        }
        return ids;
    }
}
