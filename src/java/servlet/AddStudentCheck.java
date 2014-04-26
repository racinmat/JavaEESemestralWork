/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import enums.Label;
import enums.SQLTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import source.FormValidation;
import static source.FormValidation.validateForm;
import source.MyLogger;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class AddStudentCheck extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        try {
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            String notFilledStyle = " class=\"notFilled\"";
            List<Map<Label, String>> seznamStudentu = (List<Map<Label, String>>) session.getAttribute("newstudent");
            List<Map<Label, String>> notFilled = new ArrayList<>();
            List<Map<Label, String>> input = new ArrayList<>();
            boolean error = false;
            for (Map<Label, String> map : seznamStudentu) {
                HashMap<Label, String> temp = new LinkedHashMap<>();
                temp.put(Label.USERNAME, map.get(Label.USERNAME));
                input.add(temp);
            }
            for (int i = 0; i < input.size(); i++) {
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTable.STUDENTS) && !label.isAutoFill()) {
                        if (label.isPhonenumber()) {
                            input.get(i).put(label, request.getParameter("predvolba" + label.getNameRaw() + "+" + i) + request.getParameter(label.getNameRaw() + "+" + i));
                        } else {
                            input.get(i).put(label, request.getParameter(label.getNameRaw() + "+" + i));
                        }
                    }
                }
                FormValidation form = validateForm(input.get(i), notFilledStyle);
                notFilled.add(form.getNotFilled());
                if (form.isError()) {       //aby nenastalo přemátání true falsem v případě, kdy např. předposlední bude špatně a poslední správně
                    error = form.isError();
                }
            }
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);

            if (error) {
                response.sendRedirect("pridaniStudenta.jsp");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStudent");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in dispatching to /AddStudent. " + ex.getMessage(), ex);
        }

    }
}
