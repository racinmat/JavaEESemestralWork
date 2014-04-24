/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import enums.Label;
import enums.Rights;
import enums.SQLTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class ForLoggedIn extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        SecurityCheck security=new SecurityCheck(request);
        security.noDirectAccess(response);
        security.accesedTo(Rights.APPLICANT, response);
        if (session.getAttribute("redirect")==null) {                           //kvůli předchozímu přeměrování z důvodů zákazu přímého přístupu
            try {
                String username=security.getUser().getUsername();
                Mysql sql=new Mysql();
                LinkedHashMap<Label, String> info;
                if(security.isApplicant()||security.isStudent()){
                    SQLTable table=sql.findTableWithApplicant(username);
                    info=sql.showApplicant(username, table);
                } else {
                    info=sql.showLoginInfoOfUser(username);
                }
                session.setAttribute("info", info);
                response.sendRedirect("proPrihlasene.jsp");
            } catch (IOException|ClassNotFoundException|SQLException|NoSuchFieldException ex) {
                try {
                    MyLogger.getLogger().logp(Level.SEVERE, Login.class.getName(), "doGet", "Error in mysql. "+ex.getMessage(), ex);
                    response.sendRedirect("chyba.jsp?error=0");
                } catch (IOException ex1) {
                    MyLogger.getLogger().logp(Level.SEVERE, AddAdministrativa.class.getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
                }
            }
        }
    }
}
