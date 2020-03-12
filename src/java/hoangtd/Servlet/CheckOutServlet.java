/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.cart.CartObj;
import hoangtd.cart.Item;
import hoangtd.checkOutDetails.CheckOutDetailDAO;
import hoangtd.login.LoginDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.jsp";
    private final String CART_PAGE = "cart.jsp";
    private final String UPLOAD_QUANTITY_SERVLET = "UpLoadQuantityServlet";

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
        boolean checkOutStatus = true;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("User")) {
                    url = CART_PAGE;
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart != null) {
                        int checkOutID = (int) request.getAttribute("LASTINDEX");
                        CheckOutDetailDAO checkOutDetailDAO = new CheckOutDetailDAO();
                        for (Map.Entry<Integer, Item> item : cart.getLists().entrySet()) {
                            boolean result = checkOutDetailDAO.checkOutItem(checkOutID, item.getValue().getItemName(), item.getValue().getQuantity(), item.getValue().getPrice(), item.getValue().getTotal());
                            if(result == false){
                                checkOutStatus = false;
                                break;
                            }
                        }
                        if(checkOutStatus){
                            request.setAttribute("SUCCESS", "Buy Successful !!!");
                            url = UPLOAD_QUANTITY_SERVLET;
                        }
                    }else{
                        request.setAttribute("ERROR", "Add to Cart again");
                    }
                }
            }
        } catch (NamingException e) {
            log("NamingException CheckOutServlet " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException CheckOutServlet " + e.getMessage());
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
