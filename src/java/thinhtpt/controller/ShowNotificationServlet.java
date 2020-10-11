/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinhtpt.tblarticles.TblArticlesDao;
import thinhtpt.tblarticlestatus.TblArticleStatusDao;
import thinhtpt.tblcomments.TblCommentsDao;
import thinhtpt.tblcommentstatus.TblCommentStatusDao;
import thinhtpt.tbleventype.TblEventTypeDao;
import thinhtpt.tblnotifications.TblNotificationInfo;
import thinhtpt.tblnotifications.TblNotificationsDao;
import thinhtpt.tblnotifications.TblNotificationsDto;
import thinhtpt.tblreactions.TblReactionsDao;
import thinhtpt.tblreactiontypes.TblReactionTypesDao;
import thinhtpt.tblusers.TblUsersDao;
import thinhtpt.utils.ValidationUitl;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "ShowNotificationServlet", urlPatterns = {"/ShowNotificationServlet"})
public class ShowNotificationServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ShowNotificationServlet.class);

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

        String nextPage = request.getParameter("nextPage");
        int pageInt = 1;
        if (nextPage != null) {
            if (ValidationUitl.isValidPageNum(nextPage)) {
                pageInt = Integer.parseInt(nextPage);
            } // check if page is valid
        } // check if not first paga (null value)

        List<TblNotificationInfo> listNotiInfo = null;
        Map<Integer, String> listMappingEvent = null;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblArticleStatusDao statusDao = new TblArticleStatusDao();
                    int statusId = statusDao.getStatusByName("Active");
                    TblArticlesDao articleDao = new TblArticlesDao();
                    List<String> listArticleIds = articleDao.getArticleIdsByOwner(userId, statusId);
                    if (!listArticleIds.isEmpty()) {
                        TblNotificationsDao notiDao = new TblNotificationsDao();
                        TblEventTypeDao eventDao = new TblEventTypeDao();

                        TblReactionsDao reactionDao = new TblReactionsDao();
                        TblReactionTypesDao rTypeDao = new TblReactionTypesDao();

                        TblCommentsDao commentDao = new TblCommentsDao();
                        TblCommentStatusDao cStatusDao = new TblCommentStatusDao();

                        int noneReactionType = rTypeDao.getReactionTypeByName("None");
                        List<String> listReactionIds = reactionDao.getListReactionIdsByArticle(listArticleIds, userId, noneReactionType);

                        int activeCommentStatus = cStatusDao.getStatusByName("Active");
                        List<String> listCommentIds = commentDao.getListCommentIdsByArticle(listArticleIds, userId, activeCommentStatus);
                        if (!listReactionIds.isEmpty() || !listCommentIds.isEmpty()) {
                            List<TblNotificationsDto> listNoti = notiDao.getNotificationsByAllEvent(listReactionIds, listCommentIds, pageInt);
                            listNotiInfo = new ArrayList<>();
                            if (listNoti != null) {
                                TblUsersDao userDao = new TblUsersDao();
                                Map<String, String> listFullName = new HashMap<>(); // display full name instead of email
                                listMappingEvent = new HashMap<>();
                                for (TblNotificationsDto dto : listNoti) {
                                    String[] tempInfo = null;
                                    String fullName;
                                    String articleId;
                                    if (dto.getReactionId() != null) {
                                        tempInfo = reactionDao.getOwnerAndArticleIdReactionById(dto.getReactionId());
                                    } else if (dto.getCommentId() != null) {
                                        tempInfo = commentDao.getOwnerAndArticleIdCommentById(dto.getCommentId());
                                    }
                                    if (listFullName.containsKey(tempInfo[0])) {
                                        fullName = listFullName.get(tempInfo[0]);
                                    } else {
                                        fullName = userDao.getFullNameById(tempInfo[0]);
                                        listFullName.put(tempInfo[0], fullName);
                                    }
                                    articleId = tempInfo[1];

                                    TblNotificationInfo notiInfo = new TblNotificationInfo(dto.getId(), articleId, fullName, dto.getEventType(), dto.getTime());
                                    listNotiInfo.add(notiInfo);

                                    if (!listMappingEvent.containsKey(dto.getEventType())) {
                                        String eventName = eventDao.getEventNameById(dto.getEventType());
                                        listMappingEvent.put(dto.getEventType(), eventName);
                                    } // end if listEvent not contain key
                                } // end fore notification
                            } // end if no notification
                        } // end if any reaction or comment
                    } // end if no article posted
                } // end if userId not null
            } // end if session not null
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            JsonObject objResponse = new JsonObject();
            Gson gson = new Gson();
            String notiJson = gson.toJson(listNotiInfo);
            String eventJson = gson.toJson(listMappingEvent);
            objResponse.addProperty("listNotiInfo", notiJson);
            objResponse.addProperty("listMappingEvent", eventJson);
            objResponse.addProperty("currentPage", pageInt);
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
