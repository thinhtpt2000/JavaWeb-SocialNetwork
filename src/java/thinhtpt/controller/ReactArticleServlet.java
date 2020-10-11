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
import thinhtpt.tbleventype.TblEventTypeDao;
import thinhtpt.tblnotifications.TblNotificationsDao;
import thinhtpt.tblnotifications.TblNotificationsDto;
import thinhtpt.tblreactions.TblReactionsDao;
import thinhtpt.tblreactions.TblReactionsDto;
import thinhtpt.tblreactiontypes.TblReactionTypesDao;
import thinhtpt.utils.RandomUtil;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "ReactArticleServlet", urlPatterns = {"/ReactArticleServlet"})
public class ReactArticleServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ReactArticleServlet.class);

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

        String reactionId = "";
        String articleId = request.getParameter("articleId");
        String type = request.getParameter("reactionType");
        Timestamp time = new Timestamp(new Date().getTime());

        boolean result = false;
        String status = type;

        try {
            // Use session to get user id
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblArticlesDao articleDao = new TblArticlesDao();
                    TblArticleStatusDao aStatusDao = new TblArticleStatusDao();
                    int aStatus = aStatusDao.getStatusByName("Active");
                    TblArticlesDto articleDto = articleDao.getArticleById(articleId, aStatus);
                    if (articleDto != null) {

                        TblReactionTypesDao rTypeDao = new TblReactionTypesDao();
                        int rTypeId = rTypeDao.getReactionTypeByName(type);

                        TblEventTypeDao eTypeDao = new TblEventTypeDao();
                        int eTypeId = eTypeDao.getEventByName(status); // like or dislike

                        TblReactionsDao reactionDao = new TblReactionsDao();
                        TblReactionsDto oldReaction = reactionDao.getCurrentReaction(articleId, userId);

                        TblNotificationsDao notiDao = new TblNotificationsDao();

                        if (oldReaction != null) { // check if user has reacted this article
                            if (oldReaction.getTypeId() == rTypeId) { // user click same react type => cancel reaction
                                rTypeId = rTypeDao.getReactionTypeByName("none");
                                status = "None";
                            }
                            // update type (like, dislike, none)
                            result = reactionDao.updateReaction(oldReaction.getReactionId(), rTypeId, time);
                            if (!articleDto.getOwner().equals(userId)) { // check if not owner article react
                                if (status.equals("None")) {
                                    eTypeId = eTypeDao.getEventByName(status);
                                }
                                TblNotificationsDto notiDto = new TblNotificationsDto(eTypeId, time, oldReaction.getReactionId());
                                result = notiDao.updateReactionNotification(notiDto);
                            }
                        } else {
                            // add new reaction
                            reactionId = RandomUtil.randomString(8);
                            TblReactionsDto newReactionDto = new TblReactionsDto(reactionId, articleId, userId, rTypeId, time);
                            result = reactionDao.addReaction(newReactionDto);
                            if (!articleDto.getOwner().equals(userId)) { // check if not owner article react
                                String notiId = RandomUtil.randomString(8);
                                TblNotificationsDto newNotiDto = new TblNotificationsDto(notiId, eTypeId, time);
                                newNotiDto.setReactionId(reactionId);
                                result = notiDao.addReactionNotification(newNotiDto);
                            }
                        }
                    } // end if article dto not null
                } // end if user id not null
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
