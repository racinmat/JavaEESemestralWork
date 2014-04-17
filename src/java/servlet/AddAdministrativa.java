/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import enums.SQLTables;
import java.util.HashMap;
import java.util.logging.Level;
import source.LoggedUser;
import source.MyLogger;
import source.Mysql;
import source.SendEmail;
import source.UsernameGen;
import sun.security.x509.AlgorithmId;

/**
 *
 * @author Azathoth
 */
public class AddAdministrativa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
            SendEmail mail=new SendEmail(username, password, request.getParameter(Label.name.getNameRaw()), request.getParameter(Label.lastname.getNameRaw()), request.getParameter(Label.email.getNameRaw()));
            mail.sendGmailToRegisteredUser();
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            
            HashMap<Label, String> input=new HashMap<>();
            input.put(Label.userName, username);
            input.put(Label.password, password);
            for (Label label : Label.values()) {
                if (label.isInTables(SQLTables.administrativa, SQLTables.login)&&!label.isAutoFill()) {
                    if (label.isPhonenumber()){
                        input.put(label, request.getParameter("predvolba"+label.getNameRaw())+request.getParameter(label.getNameRaw()));
                    } else {
                        input.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
            }
            boolean rs=sql.insertNewAdministrativa(input);
            
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniAdministrativy.jsp");
        } catch (IOException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, AddAdministrativa.class.getName(), "doPost method", "During adding new administrativa. "+ex.getMessage(), ex);
        } catch (SQLException|ClassNotFoundException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, AddAdministrativa.class.getName(), "doPost method", "Error in mysql. "+ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, AddAdministrativa.class.getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }

}

