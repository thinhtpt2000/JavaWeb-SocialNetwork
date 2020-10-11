/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import thinhtpt.tblverifyuser.TblVerifyUserDao;
import thinhtpt.utils.MailUtil;
import thinhtpt.utils.RandomUtil;
import thinhtpt.utils.ValidationUitl;

/**
 *
 * @author ThinhTPT
 */
@WebServlet(name = "SendEmailServlet", urlPatterns = {"/SendEmailServlet"})
public class SendEmailServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(SendEmailServlet.class);
    private final String MESSAGE_SUBJECT = "Verification account from Mini Social Network [LAB WEB]";

    private final String VERIFY_PAGE = "verify.jsp";

    private final long TWO_HOURS = 2 * 60 * 60 * 1000;

    SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");

    private String host;
    private String port;
    private String user;
    private String pass;

    @Override
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("address");
        pass = context.getInitParameter("password");
    }

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

        String recipient = request.getParameter("txtEmail");
        boolean result = false;
        boolean isMaxRequest = false;
        String url = VERIFY_PAGE;

        Date tmpDate = new Date();
        Timestamp currentTime = new Timestamp(tmpDate.getTime());
        Timestamp expireTime = new Timestamp(tmpDate.getTime() + TWO_HOURS); // 2 hour
        Timestamp lastRequestTime = null;

        try {
            if (recipient.trim().length() > 0 && ValidationUitl.isValidEmail(recipient)) {
                TblVerifyUserDao verifyUserDao = new TblVerifyUserDao();
                int numOfValidCode = verifyUserDao.countNumberOfValidCode(recipient, currentTime);
                if (numOfValidCode < 3) {
                    String id = RandomUtil.randomString(8);
                    String code = RandomUtil.randomString(6).toUpperCase();
                    result = verifyUserDao.addNewCode(id, recipient, code, currentTime, expireTime);
                    if (result) {
                        String htmlMessage = "<h3>Welcome to Mini Social Network</h3>";
                        htmlMessage += "<div>Your verification code is <b>" + code + "</b></div>";
                        htmlMessage += "<div>Your code will be expired at <i>" + DATE_FORMATTER.format(expireTime) + "</i></div>";
                        htmlMessage += "<div>If you don't sign up, please ignore this mail.</div>";
                        htmlMessage += "<div>Thank you.</div>";
                        String textMessage = "Welcome to Mini Social Network,\n"
                                + "Your verification code is " + code + ".\n"
                                + "Your code will be expired at " + DATE_FORMATTER.format(expireTime) + ".";
                        MailUtil.sendEmail(host, port, user, pass, recipient, MESSAGE_SUBJECT, htmlMessage, textMessage);
                        request.setAttribute("VERIFY_EMAIL", recipient);
                    }
                } else {
                    lastRequestTime = verifyUserDao.getLastRequestTime(recipient, currentTime);
                    isMaxRequest = true;
                }
            }
        } catch (SQLException | NamingException | EmailException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            request.setAttribute("VERIFY_EMAIL", recipient);
            if (result) {
                request.setAttribute("SEND_MSG", "Verify code is sent to your mail.");
            } else if (isMaxRequest) {
                request.setAttribute("ERR_MSG", "You can only request 3 times in 2 hours. Please try again at "
                        + DATE_FORMATTER.format(new Timestamp(lastRequestTime.getTime() + TWO_HOURS)));
            } else {
                request.setAttribute("ERR_MSG", "Your code is not sent. Please try again.");
            }

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
