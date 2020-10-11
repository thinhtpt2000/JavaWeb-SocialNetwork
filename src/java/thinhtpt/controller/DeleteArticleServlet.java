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
import thinhtpt.tblarticles.TblArticlesDao;
import thinhtpt.tblarticlestatus.TblArticleStatusDao;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "DeleteArticleServlet", urlPatterns = {"/DeleteArticleServlet"})
public class DeleteArticleServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(DeleteArticleServlet.class);
    private final String HOME_PAGE = "home.jsp";
    private final String ERROR_PAGE = "error.jsp";

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

        String articleId = request.getParameter("articleId");

        String url = ERROR_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblArticleStatusDao statusDao = new TblArticleStatusDao();
                    int statusId = statusDao.getStatusByName("Delete");
                    TblArticlesDao articleDao = new TblArticlesDao();
                    boolean result = articleDao.deleteArticle(articleId, userId, statusId);
                    if (result) {
                        url = HOME_PAGE;
                        String content = "Your article is deleted.";
                        request.setAttribute("MSG", content);
                    } else {
                        String header = "Delete Article Failed";
                        String content = "Your article may be no longer existed or you have no right to delete.";
                        request.setAttribute("TITLE", header);
                        request.setAttribute("CONTENT", content);
                    }
                }
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
