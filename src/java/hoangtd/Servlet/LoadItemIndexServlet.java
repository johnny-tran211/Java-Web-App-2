/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.item.ItemDAO;
import hoangtd.item.ItemDTO;
import hoangtd.templetes.CheckValidation;
import hoangtd.templetes.PagingTemplete;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "LoadItemIndexServlet", urlPatterns = {"/LoadItemIndexServlet"})
public class LoadItemIndexServlet extends HttpServlet {
private final String INDEX_PAGE = "index.jsp";
    private final String STATUS = "active";
    private final int AMOUNT_OF_ARTICLE = 20;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        try {
            PagingTemplete pagingTemplate = new PagingTemplete();
            int totalOfPage = pagingTemplate.getQuantityPage(AMOUNT_OF_ARTICLE);
            ItemDAO itemDAO = new ItemDAO();
            boolean number = CheckValidation.checkNumber(numberPageStr);
            if(number){
                 ArrayList<ItemDTO> listItems = new ArrayList<>();
                 int numberOfPage = Integer.parseInt(numberPageStr);
                 if (numberOfPage > totalOfPage) {
                    request.setAttribute("ERROR", "Don't have any item");
                } else {
                    if (numberOfPage == 0) {
                        listItems = itemDAO.loadItemIndex(numberOfPage, AMOUNT_OF_ARTICLE, STATUS);
                    } else if (numberOfPage > 0) {
                        numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                        listItems = itemDAO.loadItemIndex(numberOfPage, AMOUNT_OF_ARTICLE, STATUS);
                    } else {
                        request.setAttribute("ERROR", "Don't have any item");
                    }
                    if (!listItems.isEmpty()) {
                        request.setAttribute("NUMBEROFINDEXPAGE", totalOfPage);
                        request.setAttribute("LISTITEMS", listItems);
                    }else{
                        request.setAttribute("ERROR", "No record !!!");
                    }
                }
            }else{
                request.setAttribute("ERROR", "Don't have any item");
            }
        } catch (NamingException e) {
            log("NamingException LoadItemIndexServlet " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException LoadItemIndexServlet " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(INDEX_PAGE);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
