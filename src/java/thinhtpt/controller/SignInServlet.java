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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinhtpt.tblstatus.TblStatusDao;
import thinhtpt.tblusers.TblUsersDao;
import thinhtpt.tblusers.TblUsersDto;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/SignInServlet"})
public class SignInServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SignInServlet.class);
    private final String INVALID_PAGE = "signIn.jsp";
    private final String SEARCH_PAGE = "home.jsp";
    private final String VERIFY_PAGE = "verify.jsp";

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

        String email = request.getParameter("txtEmail");
        String password = (String) request.getAttribute("ENCRYPTED_PASS");

        String url = INVALID_PAGE;
        boolean result = false;

        try {
            TblUsersDao userDao = new TblUsersDao();
            TblUsersDto dto = userDao.checkLogin(email, password);
            if (dto != null) {
                TblStatusDao statusDao = new TblStatusDao();
                String statusName = statusDao.getStatusNameById(dto.getStatusId());
                if (statusName.equals("Active")) {
                    result = true;
                    url = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("FULL_NAME", dto.getFullName());
                    session.setAttribute("EMAIL", dto.getEmail());
//                session.setAttribute("STATUS", statusName);
                } else {
                    url = VERIFY_PAGE;
                    request.setAttribute("VERIFY_EMAIL", dto.getEmail());
                }
            } else {
                String errorMsg = "Email or password is incorrect!";
                request.setAttribute("ERR_MSG", errorMsg);
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            if (result) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
