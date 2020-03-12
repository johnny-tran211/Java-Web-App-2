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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String SHOPPING_PAGE = "LoadShoppingItemsServlet";

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
        String idStr = request.getParameter("txtId");
        String quantityStr = request.getParameter("txtQuantity");
        String realQuantityStr = request.getParameter("txtRealQuantity");
        String itemName = request.getParameter("txtName");
        String priceStr = request.getParameter("txtPrice");
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("User")) {
                    url = SHOPPING_PAGE;
                    boolean checkNum = CheckValidation.checkNumber(quantityStr);
                    if (checkNum) {
                        int quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0) {
                            int id = Integer.parseInt(idStr);
                            int realQuantity = Integer.parseInt(realQuantityStr);
                            double price = Double.parseDouble(priceStr);
                            CartObj cart = (CartObj) session.getAttribute("CART");
                            if (cart == null) {
                                cart = new CartObj();
                            }
                            boolean result = cart.addToCart(id, itemName, quantity, price, realQuantity);
                            if (result) {
                                session.setAttribute("CART", cart);
                            } else {
                                request.setAttribute("ERROR", "Your cart has " + realQuantityStr + " " + itemName + " already");
                            }
                        }else{
                            request.setAttribute("VALIDATION", "quantity > 0");
                        }

                    } else {
                        request.setAttribute("VALIDATION", "quantity is real number");
                    }

                }
            }
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
