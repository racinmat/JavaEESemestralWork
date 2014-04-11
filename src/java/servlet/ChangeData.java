/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import enums.SQLTables;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import enums.Rights;
import java.util.HashMap;
import source.LoggedUser;
import source.Mysql;

/**
 *
 * @author Azathoth
 */
public class ChangeData extends HttpServlet {

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
                SQLTables tabulka=sql.findTableWithApplicant(username);         //table rozlišuje mezi uchazeči, studenty atd...kvůli sloupečkům, které mají všichni uchazeči stejné, tabulka určuje konkrétní tabulku
                HashMap<Label, String> noveudaje=new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isInTable(table)&&label.isMenitelneUzivatelem()) {
                        noveudaje.put(label, request.getParameter(label.getNazevRaw()));
                    }
                }
                
                update=sql.updateApplicant(noveudaje, tabulka);
                
                if (update) {
                    session.setAttribute("registered", "success");
                } else {
                    session.setAttribute("registered", "fail");
                }
            }
            
            
            
            response.sendRedirect("proPrihlasene.jsp");
            
        } catch (IOException ex) {
            Logger.getLogger(ChangeData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException|ClassNotFoundException ex) {
            try {
                Logger.getLogger(ChangeData.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                Logger.getLogger(ChangeData.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}