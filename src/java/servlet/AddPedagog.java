/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import enums.SQLTable;
import java.util.HashMap;
import javax.mail.MessagingException;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;
import source.SendEmail;
import source.UsernameGen;

/**
 *
 * @author Azathoth
 */
public class AddPedagog extends HttpServlet {

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
     * Adds data about new pedagog to sql and then redirects user back to jsp page from which ke came.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(true);
            UsernameGen generator=new UsernameGen(10);
            Mysql sql=new Mysql();
            String username=generator.getValidatedId();
            String password=generator.getId();
            SendEmail mail=new SendEmail(username, password, request.getParameter(Label.NAME.getNameRaw()), request.getParameter(Label.LASTNAME.getNameRaw()), request.getParameter(Label.EMAIL.getNameRaw()));
            mail.sendGmailToRegisteredUser();
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            
            HashMap<Label, String> input=new HashMap<>();
            input.put(Label.USERNAME, username);
            input.put(Label.PASSWORD, password);
            for (Label label : Label.values()) {
                if (label.isInTables(SQLTable.PEDAGOGOVE, SQLTable.LOGIN)&&!label.isAutoFill()) {
                    if (label.isPhonenumber()){
                        input.put(label, request.getParameter("predvolba"+label.getNameRaw())+request.getParameter(label.getNameRaw()));
                    } else {
                        input.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
            }
            
            boolean rs=sql.insertNewPedagog(input);
            
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniPedagoga.jsp");
        } catch (SQLException|ClassNotFoundException|IOException|MessagingException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in mysql. "+ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }

}
