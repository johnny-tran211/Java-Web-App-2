/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.item.ItemDAO;
import hoangtd.login.LoginDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    private final String LOAD_ADMIN_SERVLET = "LoadItemAdminServlet";
    private final String STATUS_HISTORY_SERVLET = "StatusHistoryServlet";
    private final String LOGIN_PAGE = "login.jsp";

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
        String[] products = request.getParameterValues("cboDelete");
        boolean checkExist = true;
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("Admin")) {
                    url = LOAD_ADMIN_SERVLET;
                    if (products != null) {
                        ArrayList<String> newProducts = new ArrayList<>();
                        for (int i = 0; i < products.length; i++) {
                            if (!products[i].equals("active")) {
                                newProducts.add(products[i]);
                            }
                        }
                        ItemDAO itemDAO = new ItemDAO();
                        for (String product : newProducts) {
                            checkExist = itemDAO.deleteProduct(product);
                            if (checkExist == false) {
                                request.setAttribute("NAMEERROR", "product name: " + product + " is not existed");
                                break;
                            }
                        }
                        if (checkExist) {
                            url = STATUS_HISTORY_SERVLET;
                            request.setAttribute("DELETE", newProducts);
                        }
                    }

                }
            }
        } catch (NamingException e) {
            log("NamingException DeleteProductServlet " + e.getMessage());
        } catch (SQLException e) {
            log("NamingException DeleteProductServlet " + e.getMessage());
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
