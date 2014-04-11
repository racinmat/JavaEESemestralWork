/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import enums.Label;
import enums.SQLTables;
import java.util.HashMap;
import java.util.LinkedHashMap;
import source.FormValidation;
import static source.FormValidation.validateForm;

/**
 *
 * @author Azathoth
 */
public class AddStudentCheck extends HttpServlet {

/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        try {
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            String notFilledStyle=" class=\"notFilled\"";
            ArrayList<LinkedHashMap<Label, String>> seznamStudentu=(ArrayList<LinkedHashMap<Label, String>>) session.getAttribute("newstudent");
            ArrayList<HashMap<Label, String>> notFilled = new ArrayList<>();
            ArrayList<HashMap<Label, String>> input = new ArrayList<>();
            boolean error = false;
            for (LinkedHashMap<Label, String> map : seznamStudentu) {
                HashMap<Label, String> temp=new HashMap<>();
                temp.put(Label.uzivatelskejmeno, map.get(Label.uzivatelskejmeno));
                input.add(temp);
            }
            for (int i = 0; i < input.size(); i++) {
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTables.studenti)&&!label.isAutomatickeVyplneni()) {
                        if (label.isTelefonniCislo()){
                            input.get(i).put(label, request.getParameter("predvolba"+label.getNazevRaw()+"+"+i)+request.getParameter(label.getNazevRaw()+"+"+i));
                        } else {
                            input.get(i).put(label, request.getParameter(label.getNazevRaw()+"+"+i));
                        }
                    }
                }
                FormValidation form=validateForm(input.get(i), SQLTables.studenti, notFilledStyle);
                notFilled.add(form.getNotFilled());
                if (form.isError()) {       //aby nenastalo přemátání true falsem v případě, kdy např. předposlední bude špatně a poslední správně
                    error=form.isError();
                }
            }
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                response.sendRedirect("pridaniStudenta.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStudent");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
