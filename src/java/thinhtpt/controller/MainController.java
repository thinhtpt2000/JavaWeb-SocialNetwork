/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private final String LOGIN_PAGE = "signIn.html";
    private final String SIGN_IN_CONTROLLER = "SignInServlet";
    private final String SIGN_UP_CONTROLLER = "SignUpServlet";
    private final String SIGN_OUT_CONTROLLER = "SignOutServlet";
    private final String STARTUP_CONTROLLER = "StartupServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String VIEW_ARTICLE_CONTROLLER = "ViewArticleServlet";
    private final String POST_ARTICLE_CONTROLLER = "PostArticleServlet";
    private final String DELETE_ARTICLE_CONTROLLER = "DeleteArticleServlet";
    private final String SHOW_NOTIFICATION_CONTROLLER = "ShowNotificationServlet";
    private final String COUNT_REACTION_CONTROLLER = "CountReactionSerlvet";
    private final String SHOW_COMMENT_CONTROLLER = "ShowCommentServlet";
    private final String REACT_ARTICLE_CONTROLLER = "ReactArticleServlet";
    private final String POST_COMMENT_CONTROLLER = "PostCommentServlet";
    private final String DELETE_COMMENT_CONTROLLER = "DeleteCommentServlet";
    private final String SEND_EMAIL_CONTROLLER = "SendEmailServlet";
    private final String VERIFY_EMAIL_CONTROLLER = "VerifyEmailServlet";

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

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String url = LOGIN_PAGE;

        try {
            if (null == action) {
                url = STARTUP_CONTROLLER;
            } else {
                switch (action) {
                    case "Sign in":
                        url = SIGN_IN_CONTROLLER;
                        break;
                    case "Sign up":
                        url = SIGN_UP_CONTROLLER;
                        break;
                    case "Sign out":
                        url = SIGN_OUT_CONTROLLER;
                        break;
                    case "Search":
                        url = SEARCH_CONTROLLER;
                        break;
                    case "ViewPost":
                        url = VIEW_ARTICLE_CONTROLLER;
                        break;
                    case "Post an article":
                        url = POST_ARTICLE_CONTROLLER;
                        break;
                    case "Delete an article":
                        url = DELETE_ARTICLE_CONTROLLER;
                        break;
                    case "Show notification":
                        url = SHOW_NOTIFICATION_CONTROLLER;
                        break;
                    case "Show reaction":
                        url = COUNT_REACTION_CONTROLLER;
                        break;
                    case "Show comment":
                        url = SHOW_COMMENT_CONTROLLER;
                        break;
                    case "React article":
                        url = REACT_ARTICLE_CONTROLLER;
                        break;
                    case "Post a comment":
                        url = POST_COMMENT_CONTROLLER;
                        break;
                    case "Delete a comment":
                        url = DELETE_COMMENT_CONTROLLER;
                        break;
                    case "Send email":
                        url = SEND_EMAIL_CONTROLLER;
                        break;
                    case "Verify":
                        url = VERIFY_EMAIL_CONTROLLER;
                        break;
                    default:
                        url = STARTUP_CONTROLLER;
                        break;
                }
            }
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
