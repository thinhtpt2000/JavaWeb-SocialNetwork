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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import thinhtpt.tblreactions.TblReactionsDao;
import thinhtpt.tblreactions.TblReactionsDto;
import thinhtpt.tblreactiontypes.TblReactionTypesDao;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "CountReactionSerlvet", urlPatterns = {"/CountReactionSerlvet"})
public class CountReactionSerlvet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CountReactionSerlvet.class);

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
        String articleId = request.getParameter("articleId");

        boolean result = false;

        int likeCounter = 0;
        int dislikeCounter = 0;
        int userReaction = -1;

        try {
            TblReactionTypesDao typeDao = new TblReactionTypesDao();
            int typeId = typeDao.getReactionTypeByName("Like");
            TblReactionsDao reactionDao = new TblReactionsDao();
            likeCounter = reactionDao.counterReactionByArticleId(articleId, typeId);
            typeId = typeDao.getReactionTypeByName("Dislike");
            dislikeCounter = reactionDao.counterReactionByArticleId(articleId, typeId);
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("EMAIL");
                if (userId != null) {
                    TblReactionsDto reactionDto = reactionDao.getCurrentReaction(articleId, userId);
                    if (reactionDto != null) {
                        userReaction = reactionDto.getTypeId();
                        result = true;
                    }
                }
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            JsonObject objResponse = new JsonObject();
            objResponse.addProperty("result", result);
            objResponse.addProperty("likeCounter", likeCounter);
            objResponse.addProperty("dislikeCounter", dislikeCounter);
            objResponse.addProperty("type", userReaction);
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
