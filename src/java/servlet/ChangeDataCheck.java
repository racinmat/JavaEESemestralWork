/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import enums.SQLTable;
import java.util.HashMap;
import java.util.Map;
import source.LoggedUser;
import source.Mysql;
import source.FormValidation;
import static source.FormValidation.validateForm;
import source.MyLogger;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class ChangeDataCheck extends HttpServlet {

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
     * Processes requests for HTTP <code>POST</code> method. Validates data from
     * form and redirects user to next servlet which continues in processing
     * data.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            Mysql sql = new Mysql();
            Encrypt crypt = new Encrypt();
            LoggedUser user = (LoggedUser) session.getAttribute("user");
            String username = user.getUsername();
            boolean error = false;
            Map<Label, String> notFilled = new HashMap<>();
            String notFilledStyle = " class=\"notFilled\"";
            SQLTable table = user.getRights().getTable();
            for (Label label : Label.values()) {
                if (label.isChangableByUser()) {
                    notFilled.put(label, "");
                }
            }

            if (request.getParameter("zmenitheslo") != null) {                    //pro změnu hesla
                Map<Label, String> heslo = new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isPasswordChange()) {
                        heslo.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
                heslo.put(Label.PASSWORD, crypt.encrypt(heslo.get(Label.PASSWORD), username));
                LoggedUser passwordcheck = sql.login(username, heslo.get(Label.PASSWORD));

                if (!passwordcheck.isLogged()) {
                    notFilled.put(Label.PASSWORD, notFilledStyle);
                    error = true;
                }
                if (heslo.get(Label.NEW_PASSWORD).equals("")) {
                    notFilled.put(Label.NEW_PASSWORD, notFilledStyle);
                    error = true;
                }
                if (!heslo.get(Label.NEW_PASSWORD).equals(heslo.get(Label.NEW_PASSWORD_CHECK)) || heslo.get(Label.NEW_PASSWORD_CHECK).equals("")) {
                    notFilled.put(Label.NEW_PASSWORD_CHECK, notFilledStyle);
                    error = true;
                }
            }
            if (request.getParameter("zmenitostatniudaje") != null) {             //pro změnu ostatních údajů
                Map<Label, String> input = new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isInTable(table) && label.isChangableByUser()) {
                        input.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
                FormValidation form = validateForm(input, notFilledStyle);
                notFilled = form.getNotFilled();
                error = form.isError();
            }

            session.setAttribute("formCheck", notFilled);
            if (error) {
                response.sendRedirect("proPrihlasene.jsp");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ChangeData");
                dispatcher.forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException | ServletException | IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. " + ex1.getMessage(), ex1);
            }
        }
    }

}
