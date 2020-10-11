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
import java.util.Date;
import java.sql.Timestamp;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinhtpt.tblarticles.TblArticlesDao;
import thinhtpt.tblarticles.TblArticlesDto;
import thinhtpt.tblarticlestatus.TblArticleStatusDao;
import thinhtpt.utils.RandomUtil;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
@MultipartConfig
public class PostArticleServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(PostArticleServlet.class);

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String title = request.getParameter("txtTitle");
        String description = request.getParameter("txtDescription");
        String fileUrl = request.getParameter("txtFile");

        String randomId = RandomUtil.randomString(8);

        boolean result = false;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblArticleStatusDao statusDao = new TblArticleStatusDao();
                    int statusId = statusDao.getStatusByName("Active");
                    Timestamp date = new Timestamp(new Date().getTime());
                    TblArticlesDto dto = new TblArticlesDto(randomId, title, description, fileUrl, date, statusId, userId);
                    TblArticlesDao articleDao = new TblArticlesDao();
                    result = articleDao.addArticle(dto);
                }
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
