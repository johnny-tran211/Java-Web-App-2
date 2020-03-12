/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.Servlet;

import hoangtd.item.ItemDAO;
import hoangtd.item.ItemError;
import hoangtd.login.LoginDTO;
import hoangtd.templetes.CheckValidation;
import hoangtd.templetes.UploadImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {

    private final String STATUS = "active";
    private final String LOGIN_PAGE = "login.jsp";
    private final String CREATE_PAGE = "createProduct.jsp";

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
        String productName = request.getParameter("txtName");
        String image = request.getParameter("image");
        String quantityStr = request.getParameter("txtQuantity");
        String description = request.getParameter("txtDescription");
        String priceStr = request.getParameter("txtPrice");
        String category = request.getParameter("txtCategory");
        String imageStr = "";
        boolean errorCheck = false;
        boolean accessDenied = false;
        ItemError error = new ItemError();
        String url = LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            LoginDTO user = (LoginDTO) session.getAttribute("USER");
            if (user != null) {
                if (user.getRole().equals("Admin")) {
                    accessDenied = true;
                    url = CREATE_PAGE;
                    if (productName == null
                            || image == null
                            || quantityStr == null
                            || description == null
                            || priceStr == null
                            || category == null) {
                        errorCheck = true;
                    } else {
                        if (productName.trim().isEmpty()) {
                            errorCheck = true;
                            error.setProductNameLengthError("Input name of product");
                        }
                        if (quantityStr.trim().isEmpty()) {
                            errorCheck = true;
                            error.setQuantityLengthError("Input quantity");
                        } else {
                            boolean checkNumber = CheckValidation.checkNumber(quantityStr.trim());
                            if (!checkNumber) {
                                errorCheck = true;
                                error.setQuantityValidationError("Input real number ( >= 0)");
                            }
                        }
                        if (description.trim().isEmpty()) {
                            errorCheck = true;
                            error.setDescriptionError("Input description");
                        }
                        if (priceStr.trim().isEmpty()) {
                            errorCheck = true;
                            error.setPriceLengthError("Input price");
                        } else {
                            boolean checkNumber = CheckValidation.checkDoubleNumber(priceStr.trim());
                            if (!checkNumber) {
                                errorCheck = true;
                                error.setPriceValidationError("Input real number ( >= 0)");
                            }
                        }
                        if (category.trim().isEmpty()) {
                            errorCheck = true;
                            error.setCategoryError("Input category");
                        }
                        if (image.trim().isEmpty()) {
                            errorCheck = true;
                            error.setFileLengthError("import image");
                        }
                        if (errorCheck == false) {
                            int quantity = Integer.parseInt(quantityStr.trim());
                            double price = Double.parseDouble(priceStr.trim());
                            String absoluteDiskPath = getServletContext().getRealPath("");
                            int lastIndex = absoluteDiskPath.lastIndexOf("build\\web\\");
                            String newAbsoluteDiskPath = absoluteDiskPath.substring(0, lastIndex);
                            imageStr = UploadImage.uploadImage(image, newAbsoluteDiskPath);
                            ItemDAO itemDAO = new ItemDAO();
                            Timestamp createDate = new Timestamp(System.currentTimeMillis());
                            boolean result = itemDAO.createProduct(productName.trim(), STATUS, quantity, createDate, imageStr, description.trim(), price, category.trim());
                            if (result) {
                                request.setAttribute("STATUS", "CREATE NEW PRODUCT SUCCESSFULLY");
                            } else {
                                request.setAttribute("STATUS", "CREATE NEW PRODUCT FAILED");
                            }
                        } else {
                            request.setAttribute("VALIDATION", error);
                        }
                    }
                }
            }
            if (!accessDenied) {
                request.setAttribute("ERROR", "this page allow for admin. Login by admin account");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                error.setProductNameDuplicateError("Product Name is duplicated");
                request.setAttribute("VALIDATION", error);
            }
            log("SQLException CreateProductServlet " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException CreateProductServlet " + e.getMessage());
        } catch (FileNotFoundException e) {
            error.setFileError("File not found");
            request.setAttribute("VALIDATION", error);
            log("FileNotFoundException CreateProductServlet " + e.getMessage());
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
