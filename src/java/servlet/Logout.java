package servlet;


import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.MyLogger;
import source.SecurityCheck;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */
public class Logout extends HttpServlet{
    
    /**
     * Processes requests for HTTP <code>GET</code> method.
     * Only disables direct access.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        SecurityCheck security=new SecurityCheck(request);
        security.noDirectAccess(response);
    }
    
    /**
     * Processes requests for HTTP <code>POST</code> method.
     * Deletes all data in session variable user and sends user back from where he came.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String URL = "";
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", null);
            URL=(String) session.getAttribute("loggingURL");
            session.setAttribute("loggingURL", null);
            response.sendRedirect(URL);
        } catch (IOException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to "+URL+ex.getMessage(), ex);
        }
    }    
}
