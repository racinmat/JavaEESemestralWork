/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import enums.SQLTables;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import java.util.HashMap;
import source.LoggedUser;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class ChangeData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        SecurityCheck security=new SecurityCheck(request);
        security.noDirectAccess(response);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            Mysql sql=new Mysql();
            boolean update=false;
            LoggedUser user=(LoggedUser) session.getAttribute("user");
            String username=user.getUsername();
            SQLTables table=user.getRights().getTable();
            
            if (request.getParameter("zmenitheslo")!=null) {
                Encrypt crypt=new Encrypt();
                String password=request.getParameter("noveheslo");
                boolean error=false;
                password=crypt.encrypt(password, username);

                update=sql.updatePassword(username, password);
                
                if (update) {
                    session.setAttribute("registered", "passwordsuccess");
                } else {
                    session.setAttribute("registered", "passwordfail");
                }
            }
            if (request.getParameter("zmenitostatniudaje")!=null) {             //pro změnu ostatních údajů
                table=sql.findTableWithApplicant(username);         //table rozlišuje mezi uchazeči, studenty atd...kvůli sloupečkům, které mají všichni uchazeči stejné, tabulka určuje konkrétní tabulku
                HashMap<Label, String> noveudaje=new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isInTable(table)&&label.isChangableByUser()) {
                        noveudaje.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
                
                update=sql.updateApplicant(noveudaje, table);
                
                if (update) {
                    session.setAttribute("registered", "success");
                } else {
                    session.setAttribute("registered", "fail");
                }
            }
            response.sendRedirect("proPrihlasene.jsp");
            
        } catch (SQLException|ClassNotFoundException|IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }
}