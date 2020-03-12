/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.templetes;

import hoangtd.item.ItemDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class PagingTemplete implements Serializable{

    public PagingTemplete() {
    }
    public int getQuantityPage(int amountOfArticle) throws NamingException, SQLException {
        ItemDAO dao = new ItemDAO();
        int row = dao.getRow("active");
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
    public int getQuantityAdminPage(int amountOfArticle) throws NamingException, SQLException {
        ItemDAO dao = new ItemDAO();
        int row = dao.getAdminRow();
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
    public int getQuantitySearchValuePage(int amountOfArticle, String search) throws NamingException, SQLException {
        ItemDAO dao = new ItemDAO();
        int row = dao.getSearchValueRow("active", search);
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
    public int getQuantitySearchCategoryPage(int amountOfArticle, String category) throws NamingException, SQLException {
        ItemDAO dao = new ItemDAO();
        int row = dao.getSearchCategoryRow("active", category);
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
    public int getQuantitySearchRangePage(int amountOfArticle, double rangeOfMoney) throws NamingException, SQLException {
        ItemDAO dao = new ItemDAO();
        int row = dao.getSearchRangeRow("active", rangeOfMoney);
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
}
