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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinhtpt.tblarticles.TblArticlesDao;
import thinhtpt.tblarticles.TblArticlesDto;
import thinhtpt.tblarticlestatus.TblArticleStatusDao;
import thinhtpt.tblcomments.TblCommentsDao;
import thinhtpt.tblcomments.TblCommentsDto;
import thinhtpt.tblcommentstatus.TblCommentStatusDao;
import thinhtpt.tbleventype.TblEventTypeDao;
import thinhtpt.tblnotifications.TblNotificationsDao;
import thinhtpt.tblnotifications.TblNotificationsDto;
import thinhtpt.utils.RandomUtil;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "PostCommentServlet", urlPatterns = {"/PostCommentServlet"})
public class PostCommentServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(PostCommentServlet.class);

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
        String articleId = request.getParameter("articleId");
        String content = request.getParameter("txtComment");
        Timestamp time = new Timestamp(new Date().getTime());
        String commentId = RandomUtil.randomString(8);

        boolean result = false;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblArticlesDao articleDao = new TblArticlesDao();
                    TblArticleStatusDao aStatusDao = new TblArticleStatusDao();
                    int aStatus = aStatusDao.getStatusByName("Active");
                    TblArticlesDto articleDto = articleDao.getArticleById(articleId, aStatus);
                    if (articleDto != null) {
                        TblCommentStatusDao statusDao = new TblCommentStatusDao();
                        int status = statusDao.getStatusByName("Active");
                        TblCommentsDto commentDto = new TblCommentsDto(commentId, content, articleId, userId, status, time);
                        TblCommentsDao commentDao = new TblCommentsDao();
                        result = commentDao.addComment(commentDto);
                        if (result) {
                            // Check if owner is notifier
                            String owner = articleDto.getOwner();
                            if (!owner.equals(userId)) {
                                String notiId = RandomUtil.randomString(8);
                                TblEventTypeDao typeDao = new TblEventTypeDao();
                                int typeId = typeDao.getEventByName("Comment");
                                TblNotificationsDto notiDto = new TblNotificationsDto(notiId, typeId, time);
                                notiDto.setCommentId(commentId);
                                TblNotificationsDao notiDao = new TblNotificationsDao();
                                result = notiDao.addCommentNotification(notiDto);
                            }
                        }
                    }
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
