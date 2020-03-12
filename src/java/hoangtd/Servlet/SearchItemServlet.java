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
@WebServlet(name = "SearchItemServlet", urlPatterns = {"/SearchItemServlet"})
public class SearchItemServlet extends HttpServlet {

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
        String searchValue = request.getParameter("txtSearchName");
        String rangeOfMoneyStr = request.getParameter("txtSearchRange");
        String category = request.getParameter("txtCategory");
        int count = 0;
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        PagingTemplete pagingTemplate = new PagingTemplete();
        try {
            if (searchValue != null) {
                if (searchValue.length() >= 0) {
                    searchValue = searchValue.trim();
                    count += 1;
                }
            }
            if (rangeOfMoneyStr != null) {
                if (rangeOfMoneyStr.length() >= 0) {
                    rangeOfMoneyStr = rangeOfMoneyStr.trim();
                    count += 1;
                }
            }
            if (category != null) {
                if (category.length() >= 0) {
                    category = category.trim();
                    count += 1;
                }
            }
            if (count == 1 && searchValue != null) {
                if (searchValue.length() > 0) {
                    int totalOfPage = pagingTemplate.getQuantitySearchValuePage(AMOUNT_OF_ARTICLE, searchValue);
                    ItemDAO itemDAO = new ItemDAO();
                    boolean number = CheckValidation.checkNumber(numberPageStr);

                    if (number) {
                        ArrayList<ItemDTO> listItems = new ArrayList<>();
                        int numberOfPage = Integer.parseInt(numberPageStr);
                        if (numberOfPage > totalOfPage) {
                            request.setAttribute("ERROR", "Don't have any item");
                        } else {
                            if (numberOfPage == 0) {
                                listItems = itemDAO.searchValueIndexByName(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, searchValue);
                            } else if (numberOfPage > 0) {
                                numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                listItems = itemDAO.searchValueIndexByName(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, searchValue);
                            } else {
                                request.setAttribute("ERROR", "Don't have any item");
                            }
                            if (!listItems.isEmpty()) {
                                request.setAttribute("NUMBEROFSEARCHVALUE", totalOfPage);
                                request.setAttribute("LISTITEMS", listItems);
                            } else {
                                request.setAttribute("ERROR", "No record !!!");
                            }
                        }
                    } else {
                        request.setAttribute("ERROR", "Don't have any item");
                    }
                }
            } else if (count == 1 && rangeOfMoneyStr != null) {
                boolean checkValidation = CheckValidation.checkDoubleNumber(rangeOfMoneyStr);
                if (checkValidation) {
                    if (rangeOfMoneyStr.length() > 0) {
                        double rangeOfMoney = Double.parseDouble(rangeOfMoneyStr);
                        int totalOfPage = pagingTemplate.getQuantitySearchRangePage(AMOUNT_OF_ARTICLE, rangeOfMoney);
                        ItemDAO itemDAO = new ItemDAO();
                        boolean number = CheckValidation.checkNumber(numberPageStr);

                        if (number) {
                            ArrayList<ItemDTO> listItems = new ArrayList<>();
                            int numberOfPage = Integer.parseInt(numberPageStr);
                            if (numberOfPage > totalOfPage) {
                                request.setAttribute("ERROR", "Don't have any item");
                            } else {
                                if (numberOfPage == 0) {
                                    listItems = itemDAO.searchValueIndexByRange(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, rangeOfMoney);
                                } else if (numberOfPage > 0) {
                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                    listItems = itemDAO.searchValueIndexByRange(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, rangeOfMoney);
                                } else {
                                    request.setAttribute("ERROR", "Don't have any item");
                                }
                                if (!listItems.isEmpty()) {
                                    request.setAttribute("NUMBEROFRANGEVALUE", totalOfPage);
                                    request.setAttribute("LISTITEMS", listItems);
                                } else {
                                    request.setAttribute("ERROR", "No record !!!");
                                }
                            }
                        } else {
                            request.setAttribute("ERROR", "Don't have any item");
                        }
                    }
                } else {
                    request.setAttribute("INPUTNUMBER", "input real number");
                }
            } else if (count == 1 && category != null) {
                int totalOfPage = pagingTemplate.getQuantitySearchCategoryPage(AMOUNT_OF_ARTICLE, category);
                ItemDAO itemDAO = new ItemDAO();
                boolean number = CheckValidation.checkNumber(numberPageStr);
                if (category.length() > 0) {
                    if (number) {
                        ArrayList<ItemDTO> listItems = new ArrayList<>();
                        int numberOfPage = Integer.parseInt(numberPageStr);
                        if (numberOfPage > totalOfPage) {
                            request.setAttribute("ERROR", "Don't have any item");
                        } else {
                            if (numberOfPage == 0) {
                                listItems = itemDAO.searchValueIndexByCategory(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, category);
                            } else if (numberOfPage > 0) {
                                numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                listItems = itemDAO.searchValueIndexByCategory(numberOfPage, AMOUNT_OF_ARTICLE, STATUS, category);
                            } else {
                                request.setAttribute("ERROR", "Don't have any item");
                            }
                            if (!listItems.isEmpty()) {
                                request.setAttribute("NUMBEROFSEARCHCATEGORY", totalOfPage);
                                request.setAttribute("LISTITEMS", listItems);
                            } else {
                                request.setAttribute("ERROR", "No record !!!");
                            }
                        }
                    } else {
                        request.setAttribute("ERROR", "Don't have any item");
                    }
                }

            } else {
                request.setAttribute("INCORRECT", "Search by 1 option");
            }

        } catch (NamingException e) {
            log("NamingException SearchItemServlet " + e.getMessage());
        } catch (SQLException e) {
            log("SQLException SearchItemServlet " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
