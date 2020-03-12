/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.cart.CartObj;
import hoangtd.cart.Item;
import hoangtd.history.CheckOutHistoryDAO;
import hoangtd.login.LoginDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
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
@WebServlet(name = "SaveHistoryServlet", urlPatterns = {"/SaveHistoryServlet"})
public class SaveHistoryServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
        private final String CART_PAGE = "cart.jsp";
    private final String CHECK_OUT_DETAIL_PAGE = "CheckOutServlet";

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
        String paymentMethod = request.getParameter("ckbMethod");
        String totalStr = request.getParameter("txtTotal");
        String url = LOGIN_PAGE;
        boolean checkQuantity = true;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("User")) {
                    url = CART_PAGE;
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart != null) {
                        for (Map.Entry<Integer, Item> item : cart.getLists().entrySet()) {
                            if (item.getValue().getQuantity() > item.getValue().getRealQuantity()) {
                                checkQuantity = false;
                                request.setAttribute("ERRORQUANTITY", "Quantity of " + item.getValue().getItemName() + " is out of stock. It has only " + item.getValue().getRealQuantity() + " items");
                                break;
                            }
                        }
                        if (checkQuantity) {
                            double total = Double.parseDouble(totalStr);
                            CheckOutHistoryDAO checkOutHistoryDAO = new CheckOutHistoryDAO();
                            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                            boolean result = checkOutHistoryDAO.saveHistory(user.getUsername(), currentTime, paymentMethod, total);
                            if (result) {
                                int lastIndex = checkOutHistoryDAO.lastIndex();
                                if (lastIndex != 0) {
                                    url = CHECK_OUT_DETAIL_PAGE;
                                    request.setAttribute("LASTINDEX", lastIndex);
                                } else {
                                    request.setAttribute("ERROR", "CheckOut gets something wrong...");
                                }
                            } else {
                                request.setAttribute("ERROR", "CheckOut gets something wrong...");
                            }
                        }
                    }
                }
            }
        } catch (NamingException e) {
            log("CheckOutServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("CheckOutServlet SQLException " + e.getMessage());
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
