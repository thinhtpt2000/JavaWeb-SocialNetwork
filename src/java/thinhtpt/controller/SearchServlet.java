/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
import thinhtpt.utils.PagingUtil;
import thinhtpt.utils.ValidationUitl;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SearchServlet.class);
    private final String SEARCH_PAGE = "search.jsp";
    private final String NOT_FOUND_PAGE = "error.jsp";
    private final String[] SPECIAL_CHARS = {"\\", "%", "^", "_", "[", "]"};

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
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String searchValue = request.getParameter("txtSearchValue");
        String page = request.getParameter("page");
        String url = SEARCH_PAGE;

        try {
            if (searchValue.trim().length() > 0) { // check if user input
                int pageInt = 1;
                if (page != null) { // check if not first page
                    if (ValidationUitl.isValidPageNum(page)) { // check valid integer
                        pageInt = Integer.parseInt(page);
                    }
                }
                // Process search value
                for (String s : SPECIAL_CHARS) {
                    if (searchValue.contains(s)) {
                        searchValue = searchValue.replace(s, "\\" + s);
                    }
                }
                TblArticlesDao articleDao = new TblArticlesDao();
                TblArticleStatusDao statusDao = new TblArticleStatusDao();
                int statusId = statusDao.getStatusByName("Active");
                int totalArticles = articleDao.getTotalArticleByName(searchValue, statusId);
                int numOfPage = (int) Math.ceil((double) totalArticles / 20);
                if (pageInt > numOfPage || pageInt < 1) { // check if not in range page
                    pageInt = 1;
                }
                List<TblArticlesDto> listArticles = articleDao.getArticlesByName(searchValue, statusId, pageInt);
                if (listArticles != null) {
                    List<Integer> listPages = PagingUtil.getListNumPage(pageInt, numOfPage);
                    request.setAttribute("LIST_ARTICLES", listArticles);
                    request.setAttribute("CURR_PAGE", pageInt);
                    request.setAttribute("LIST_PAGES", listPages);
                    request.setAttribute("NUM_OF_PAGE", numOfPage);
                } else {
                    url = NOT_FOUND_PAGE;
                    String header = "No Result Found";
                    String content = "There is no artile with your search key: <b>" + request.getParameter("txtSearchValue") + "</b>";
                    request.setAttribute("TITLE", header);
                    request.setAttribute("CONTENT", content);
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
