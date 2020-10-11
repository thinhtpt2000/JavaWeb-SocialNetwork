/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import thinhtpt.tblstatus.TblStatusDao;
import thinhtpt.tblusers.TblUsersDao;
import thinhtpt.tblverifyuser.TblVerifyUserDao;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "VerifyEmailServlet", urlPatterns = {"/VerifyEmailServlet"})
public class VerifyEmailServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(VerifyEmailServlet.class);

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String email = request.getParameter("txtEmail");
        String code = request.getParameter("txtCode").toUpperCase();
        Timestamp currentTime = new Timestamp(new Date().getTime());

        boolean result = false;

        try {
            TblVerifyUserDao verifyDao = new TblVerifyUserDao();
            result = verifyDao.checkVerifyCode(email, code, currentTime);
            if (result) {
                TblUsersDao userDao = new TblUsersDao();
                TblStatusDao statusDao = new TblStatusDao();
                int statusId = statusDao.getStatusByName("Active");
                result = userDao.changeUserStatus(email, statusId);
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            JsonObject objResponse = new JsonObject();
            objResponse.addProperty("status", result);
            out.print(objResponse);
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
