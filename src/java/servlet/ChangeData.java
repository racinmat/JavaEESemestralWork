/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import enums.SQLTable;
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
import java.util.Map;
import source.LoggedUser;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class ChangeData extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> method. Only disables direct
     * access.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        SecurityCheck security = new SecurityCheck(request);
        security.noDirectAccess(response);
    }

    /**
     * Processes requests for HTTP <code>POST</code> method. Change data about
     * user in sql and then redirects user back to jsp page from which ke came.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            Mysql sql = new Mysql();
            boolean update;
            LoggedUser user = (LoggedUser) session.getAttribute("user");
            String username = user.getUsername();
            if (request.getParameter("zmenitheslo") != null) {
                Encrypt crypt = new Encrypt();
                String password = request.getParameter("noveheslo");
                password = crypt.encrypt(password, username);

                update = sql.updatePassword(username, password);

                if (update) {
                    session.setAttribute("registered", "passwordsuccess");
                } else {
                    session.setAttribute("registered", "passwordfail");
                }
            }
            if (request.getParameter("zmenitostatniudaje") != null) {             //pro změnu ostatních údajů
                SQLTable table = sql.findTableWithApplicant(username);            //table rozlišuje mezi uchazeči, studenty atd...kvůli sloupečkům, které mají všichni uchazeči stejné, tabulka určuje konkrétní tabulku
                Map<Label, String> newData = new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isInTable(table) && label.isChangableByUser()) {
                        newData.put(label, request.getParameter(label.getNameRaw()));
                    }
                }

                update = sql.updateApplicant(newData, table);                     //update zatím pouze pro uchazeče

                if (update) {
                    session.setAttribute("registered", "success");
                } else {
                    session.setAttribute("registered", "fail");
                }
            }
            response.sendRedirect("proPrihlasene.jsp");

        } catch (SQLException | ClassNotFoundException | IOException | NoSuchFieldException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. " + ex1.getMessage(), ex1);
            }
        }
    }
}
