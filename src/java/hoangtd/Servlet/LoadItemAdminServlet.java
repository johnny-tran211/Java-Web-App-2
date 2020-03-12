/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.item.ItemDAO;
import hoangtd.item.ItemDTO;
import hoangtd.login.LoginDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "LoadItemAdminServlet", urlPatterns = {"/LoadItemAdminServlet"})
public class LoadItemAdminServlet extends HttpServlet {

    private final String UPDATE_PAGE = "updateProduct.jsp";
    private final String LOGIN_PAGE = "login.jsp";
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
        String url = LOGIN_PAGE;
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("Admin")) {
                    PagingTemplete pagingTemplate = new PagingTemplete();
                    int totalOfPage = pagingTemplate.getQuantityAdminPage(AMOUNT_OF_ARTICLE);
                    ItemDAO itemDAO = new ItemDAO();
                    boolean number = CheckValidation.checkNumber(numberPageStr);
                    url = UPDATE_PAGE;
                    if (number) {
                        ArrayList<ItemDTO> listItems = new ArrayList<>();
                        int numberOfPage = Integer.parseInt(numberPageStr);
                        if (numberOfPage > totalOfPage) {
                            request.setAttribute("ERROR", "Don't have any blog");
                        } else {
                            if (numberOfPage == 0) {
                                listItems = itemDAO.loadItemAdmin(numberOfPage, AMOUNT_OF_ARTICLE);
                            } else if (numberOfPage > 0) {
                                numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                listItems = itemDAO.loadItemAdmin(numberOfPage, AMOUNT_OF_ARTICLE);
                            } else {
                                request.setAttribute("ERROR", "Don't have any blog");
                            }
                            if (!listItems.isEmpty()) {
                                request.setAttribute("NUMBEROFADMINPAGE", totalOfPage);
                                request.setAttribute("LISTITEMS", listItems);
                            } else {
                                request.setAttribute("ERROR", "No record !!!");
                            }
                        }
                    } else {
                        request.setAttribute("ERROR", "Don't have any blog");
                    }
                }
            }
        } catch (NamingException e) {
            log("NamingException LoadItemIndexServlet " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException LoadItemIndexServlet " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
