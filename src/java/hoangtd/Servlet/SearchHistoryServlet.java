/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.history.CheckOutHistoryDAO;
import hoangtd.history.CheckOutHistoryDTO;
import hoangtd.login.LoginDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String VIEW_HISTORY = "viewHistory.jsp";

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
        String dateStr = request.getParameter("txtDate");
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("User")) {
                    url = VIEW_HISTORY;
                    CheckOutHistoryDAO checkOutHistoryDAO = new CheckOutHistoryDAO();
                    ArrayList<CheckOutHistoryDTO> lists = checkOutHistoryDAO.loadHistoriesByDate(dateStr);
                    if (lists != null) {
                        request.setAttribute("LISTHISTORIES", lists);
                    } else {
                        request.setAttribute("ERROR", "Don't found any history");
                    }
                }
            }
        }  catch (NamingException e) {
            log("SearchHistoryServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SearchHistoryServlet SQLException " + e.getMessage());
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
