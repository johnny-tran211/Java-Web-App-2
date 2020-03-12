/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.cart.CartObj;
import hoangtd.login.LoginDTO;
import hoangtd.templetes.CheckValidation;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteItemCartServlet", urlPatterns = {"/DeleteItemCartServlet"})
public class DeleteItemCartServlet extends HttpServlet {

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
        String idStr = request.getParameter("txId");
        String action = request.getParameter("action");
        String quantityStr = request.getParameter("txtQuantity");
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("User")) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    int id = Integer.parseInt(idStr);
                    if (cart != null) {
                        if (action.equals("Delete")) {
                            cart.deleteItem(id);
                            session.setAttribute("CART", cart);
                        } else if (action.equals("Update")) {
                            boolean checkNum = CheckValidation.checkNumber(quantityStr);
                            if (checkNum) {
                                int quantity = Integer.parseInt(quantityStr);
                                cart.getLists().get(id).setQuantity(quantity);
                                cart.getLists().get(id).setTotal(quantity * cart.getLists().get(id).getPrice());
                                session.setAttribute("CART", cart);
                            }else{
                                request.setAttribute("ERROR", "Input real number > 0");
                            }
                        }
                    }
                }
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
            rd.forward(request, response);
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
