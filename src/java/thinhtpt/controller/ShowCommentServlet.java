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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import thinhtpt.tblcomments.TblCommentsDao;
import thinhtpt.tblcomments.TblCommentsDto;
import thinhtpt.tblcommentstatus.TblCommentStatusDao;
import thinhtpt.tblusers.TblUsersDao;
import thinhtpt.utils.PagingUtil;
import thinhtpt.utils.ValidationUitl;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "ShowCommentServlet", urlPatterns = {"/ShowCommentServlet"})
public class ShowCommentServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(ShowCommentServlet.class);

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
        String page = request.getParameter("page");
        int pageInt = 1;
        if (page != null) { // check if not first page (null value)
            if (ValidationUitl.isValidPageNum(page)) { // check if page is integer
                pageInt = Integer.parseInt(page);
            }
        }

        List<TblCommentsDto> listComments = null;
        Map<String, String> listUserFullName = null;
        int numOfPage = 0;
        int totalComments = 0;
        List<Integer> listPages = null;

        try {
            TblCommentStatusDao statusDao = new TblCommentStatusDao();
            int statusId = statusDao.getStatusByName("Active");
            TblCommentsDao commentDao = new TblCommentsDao();
            totalComments = commentDao.getTotalCommentByArticle(articleId, statusId);
            numOfPage = (int) Math.ceil((double) totalComments / 10);
            if (pageInt > numOfPage || pageInt < 1) { // check if page not in range
                pageInt = 1;
            }
            listPages = PagingUtil.getListNumPage(pageInt, numOfPage);
            listComments = commentDao.getCommentsByArticle(articleId, statusId, pageInt);
            if (listComments != null) {
                listUserFullName = new HashMap<>();
                TblUsersDao userDao = new TblUsersDao();
                for (TblCommentsDto dto : listComments) {
                    if (!listUserFullName.containsKey(dto.getUserId())) {
                        String fullName = userDao.getFullNameById(dto.getUserId());
                        listUserFullName.put(dto.getUserId(), fullName);
                    } // end if listFullName not contain key
                }
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            JsonObject objResponse = new JsonObject();
            Gson gson = new Gson();
            String commentJson = gson.toJson(listComments);
            String fullNameJson = gson.toJson(listUserFullName);
            String pageJson = gson.toJson(listPages);
            objResponse.addProperty("listComments", commentJson);
            objResponse.addProperty("listFullName", fullNameJson);
            objResponse.addProperty("listPage", pageJson);
            objResponse.addProperty("numOfPage", numOfPage);
            objResponse.addProperty("currentPage", pageInt);
            objResponse.addProperty("totalComments", totalComments);
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
