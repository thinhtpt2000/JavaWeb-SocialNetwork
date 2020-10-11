/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import thinhtpt.tblstatus.TblStatusDao;
import thinhtpt.tblusers.TblUserCreateErrors;
import thinhtpt.tblusers.TblUsersDao;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SignUpServlet.class);
    private final String ERROR_PAGE = "signUp.jsp";
    private final String VERIFY_PAGE = "SendEmailServlet";

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String email = request.getParameter("txtEmail");
        String password = (String) request.getAttribute("ENCRYPTED_PASS");
        String fullName = request.getParameter("txtFullName");

        String url = ERROR_PAGE;
        TblUserCreateErrors errors = null;

        try {
            TblStatusDao statusDao = new TblStatusDao();
            int statusId = statusDao.getStatusByName("New");
            TblUsersDao userDao = new TblUsersDao();
            boolean isExistedEmail = userDao.isExistedEmail(email);
            if (isExistedEmail) {
                errors = new TblUserCreateErrors();
                errors.setEmailIsExisted(email + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                boolean result = userDao.createNewAccount(email, password, fullName, statusId);
                if (result) {
                    url = VERIFY_PAGE;
                } // end if result
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
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
