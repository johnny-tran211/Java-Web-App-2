/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.history;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class CheckOutHistoryDAO implements Serializable{
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
    public boolean saveHistory(String email, Timestamp checkOutDate, String paymentMethod, double total) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into CheckOutHistory(Email, CheckOutDate, PaymentMethod, TotalPrice) "
                    + "values(?, ?, ?, ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, email);
            ptmt.setTimestamp(2, checkOutDate);
            ptmt.setString(3, paymentMethod);
            ptmt.setDouble(4, total);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public int lastIndex() throws NamingException, SQLException {
        int lastIndex = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT TOP 1 CheckOutID FROM CheckOutHistory order by CheckOutID DESC";
            ptmt = con.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                lastIndex = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return lastIndex;
    }
     public ArrayList<CheckOutHistoryDTO> loadHistories(String email) throws NamingException, SQLException {
        ArrayList<CheckOutHistoryDTO> histories = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select CheckOutID, Email, CheckOutDate, PaymentMethod, TotalPrice "
                    + "from CheckOutHistory "
                    + "where Email = ? order by CheckOutDate DESC";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, email);
            rs = ptmt.executeQuery();
            histories = new ArrayList<>();
            while (rs.next()) {
                histories.add(new CheckOutHistoryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5)));
            }
        } finally {
            closeConnection();
        }
        return histories;
    }
     public CheckOutHistoryDTO loadHistoriesByID(int id) throws NamingException, SQLException {
        CheckOutHistoryDTO history = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select CheckOutID, Email, CheckOutDate, PaymentMethod, TotalPrice "
                    + "from CheckOutHistory "
                    + "where CheckOutID = ? order by CheckOutDate DESC";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                history = new CheckOutHistoryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5));
            }
        } finally {
            closeConnection();
        }
        return history;
    }
     public ArrayList<CheckOutHistoryDTO> loadHistoriesByDate(String date) throws NamingException, SQLException {
        ArrayList<CheckOutHistoryDTO> histories = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select CheckOutID, Email, CheckOutDate, PaymentMethod, TotalPrice "
                    + "from CheckOutHistory "
                    + "where CheckOutDate between ? and ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, date + " 00:00:00");
            ptmt.setString(2, date + " 23:59:59");
            rs = ptmt.executeQuery();
            histories = new ArrayList<>();
            while (rs.next()) {
                histories.add(new CheckOutHistoryDTO(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getDouble(5)));
            }
        } finally {
            closeConnection();
        }
        return histories;
    }
}
