/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.item;

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
public class ItemDAO implements Serializable {

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

    public int getRow(String status) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(id) FROM Items where status = ? and quantity > ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public int getAdminRow() throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(id) FROM Items where quantity >= ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, 0);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public int getSearchValueRow(String status, String search) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(id) FROM Items where status = ? and quantity > ? and itemName LIKE ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setString(3, "%" + search + "%");
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public int getSearchCategoryRow(String status, String category) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(id) FROM Items where status = ? and quantity > ? and category = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setString(3, category);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public int getSearchRangeRow(String status, double rangeOfMoney) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(id) FROM Items where status = ? and quantity > ? and price <= ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setDouble(3, rangeOfMoney);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public ItemDTO loadPriceItem(int id) throws NamingException, SQLException {
        ItemDTO item = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, quantity, price "
                    + "from Items "
                    + "where id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                item = new ItemDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
            }
        } finally {
            closeConnection();
        }
        return item;
    }

    public ArrayList<ItemDTO> loadItemIndex(int numberOfPage, int amountOfPage, String status) throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where status = ? and quantity > ? order by createDate DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setInt(3, numberOfPage);
            ptmt.setInt(4, amountOfPage);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public ArrayList<ItemDTO> loadShoppingItems() throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, quantity, image, price "
                    + "from Items "
                    + "where status = ? and quantity > ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "active");
            ptmt.setInt(2, 0);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public ArrayList<ItemDTO> loadItemAdmin(int numberOfPage, int amountOfPage) throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id,  itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where quantity >= ? order by createDate DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, 0);
            ptmt.setInt(2, numberOfPage);
            ptmt.setInt(3, amountOfPage);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public ArrayList<ItemDTO> searchValueIndexByName(int numberOfPage, int amountOfPage, String status, String search) throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where status = ? and quantity > ? and itemName LIKE ? order by createDate DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setString(3, "%" + search + "%");
            ptmt.setInt(4, numberOfPage);
            ptmt.setInt(5, amountOfPage);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public ArrayList<ItemDTO> searchValueIndexByRange(int numberOfPage, int amountOfPage, String status, double rangeOfMoney) throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where status = ? and quantity > ? and price <= ? order by createDate DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, 0);
            ptmt.setDouble(3, rangeOfMoney);
            ptmt.setInt(4, numberOfPage);
            ptmt.setInt(5, amountOfPage);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public ArrayList<ItemDTO> searchValueIndexByCategory(int numberOfPage, int amountOfPage, String status, String category) throws NamingException, SQLException {
        ArrayList<ItemDTO> items = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where status = ? and quantity > ? and category = ? order by createDate DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "active");
            ptmt.setInt(2, 0);
            ptmt.setString(3, category);
            ptmt.setInt(4, numberOfPage);
            ptmt.setInt(5, amountOfPage);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
            }
        } finally {
            closeConnection();
        }
        return items;
    }

    public boolean createProduct(String itemName, String status, int quantity, Timestamp createDate, String image, String description, double price, String category) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into Items(itemName, status, quantity, createDate, image, description, price, category) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, itemName);
            ptmt.setString(2, status);
            ptmt.setInt(3, quantity);
            ptmt.setTimestamp(4, createDate);
            ptmt.setString(5, image);
            ptmt.setString(6, description);
            ptmt.setDouble(7, price);
            ptmt.setString(8, category);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateProduct(int id, String itemName, int quantity, String image, String description, double price, String category) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Update Items set "
                    + "itemName = ?, "
                    + "quantity = ?, "
                    + "image = ?, "
                    + "description = ?, "
                    + "price = ?, "
                    + "category = ? "
                    + "where id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, itemName);
            ptmt.setInt(2, quantity);
            ptmt.setString(3, image);
            ptmt.setString(4, description);
            ptmt.setDouble(5, price);
            ptmt.setString(6, category);
            ptmt.setInt(7, id);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean deleteProduct(String productName) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "UPDATE Items SET status = ? WHERE itemName = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "Inactive");
            ptmt.setString(2, productName);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean restoreProduct(String productName) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "UPDATE Items SET status = ? WHERE itemName = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "active");
            ptmt.setString(2, productName);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ItemDTO loadItemUpdate(int id) throws NamingException, SQLException {
        ItemDTO item = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select id, itemName, status, quantity, createDate, image, description, price, category "
                    + "from Items "
                    + "where quantity >= ? and id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, 0);
            ptmt.setInt(2, id);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                item = new ItemDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9));
            }
        } finally {
            closeConnection();
        }
        return item;
    }

    public boolean updateQuantity(int id, int quantity) throws NamingException, SQLException {
        try {
            con = Utilities.makeConnection();
            String sql = "Update Items set "
                    + "quantity = ? "
                    + "where id = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, quantity);
            ptmt.setInt(2, id);
            int result = ptmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
