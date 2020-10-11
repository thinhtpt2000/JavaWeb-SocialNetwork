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
import thinhtpt.tblarticles.TblArticlesDao;
import thinhtpt.tblarticles.TblArticlesDto;
import thinhtpt.tblarticlestatus.TblArticleStatusDao;
import thinhtpt.tblusers.TblUsersDao;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "ViewArticleServlet", urlPatterns = {"/ViewArticleServlet"})
public class ViewArticleServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ViewArticleServlet.class);
    private final String VIEW_POST_PAGE = "post.jsp";
    private final String POST_ERR_PAGE = "error.jsp";

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

        String id = request.getParameter("articleId");
        String url = VIEW_POST_PAGE;

        try {
            TblArticleStatusDao statusDao = new TblArticleStatusDao();
            int statusId = statusDao.getStatusByName("Active");
            TblArticlesDao articleDao = new TblArticlesDao();
            TblArticlesDto dto = articleDao.getArticleById(id, statusId);
            if (dto == null) {
                url = POST_ERR_PAGE;
                String header = "Article Not Found";
                String content = "Sorry, your article may be no longer existed";
                request.setAttribute("TITLE", header);
                request.setAttribute("CONTENT", content);
            } else {
                TblUsersDao userDao = new TblUsersDao();
                String fullName = userDao.getFullNameById(dto.getOwner());
                request.setAttribute("POST_DETAIL", dto);
                request.setAttribute("OWNER_NAME", fullName);
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
