package servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import source.LoggedUser;
import source.Mysql;
import enums.Rights;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */
public class Login extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession(true);
            String URL=(String) session.getAttribute("loggingURL");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            Mysql sql=new Mysql();
            LoggedUser user=sql.login(username, password);
            if(user.getLogged().equals("fail")){
                response.sendRedirect(URL);
            }
            else{
                session.setAttribute("user", user);
                response.sendRedirect(user.getRights().getInitialRedirect());
            }
        } catch (SQLException|ClassNotFoundException|NullPointerException ex) {
            try {
                source.MyLogger.getLogger().logp(Level.SEVERE, Login.class.getName(), "doPost method", "Error in mysql or loging in. "+ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}