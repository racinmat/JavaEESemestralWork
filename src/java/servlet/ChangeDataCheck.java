/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import enums.SQLTables;
import java.util.HashMap;
import source.LoggedUser;
import source.Mysql;
import source.FormValidation;
import static source.FormValidation.validateForm;

/**
 *
 * @author Azathoth
 */
public class ChangeDataCheck extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession(true);
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            Mysql sql=new Mysql();
            Encrypt crypt=new Encrypt();
            LoggedUser user=(LoggedUser) session.getAttribute("user");
            String username=user.getUsername();
            boolean error=false;
            HashMap<Label, String> notFilled = new HashMap<>();
            String notFilledStyle=" class=\"notFilled\"";
            SQLTables table=user.getRights().getTable();
            for (Label label : Label.values()) {
                if (label.isMenitelneUzivatelem()) {
                    notFilled.put(label, "");
                }
            }
            
            if (request.getParameter("zmenitheslo")!=null) {                    //pro změnu hesla
                HashMap<Label, String> heslo = new HashMap<>();
                for (Label label : Label.values()) {
                    if (label.isZmenaHesla()) {
                        heslo.put(label, request.getParameter(label.getNazevRaw()));
                    }
                }
                heslo.put(Label.hashhesla, crypt.encrypt(heslo.get(Label.hashhesla), username));
                LoggedUser passwordcheck=sql.login(username, heslo.get(Label.hashhesla));
                
                if (!passwordcheck.getLogged().equals("success")) {
                    notFilled.put(Label.hashhesla, notFilledStyle);
                    error=true;
                }
                if (heslo.get(Label.noveheslo).equals("")) {
                    notFilled.put(Label.noveheslo, notFilledStyle);
                    error=true;
                }
                if (!heslo.get(Label.noveheslo).equals(heslo.get(Label.noveheslokonrola))||heslo.get(Label.noveheslokonrola).equals("")) {
                    notFilled.put(Label.noveheslokonrola, notFilledStyle);
                    error=true;
                }
            }
            if (request.getParameter("zmenitostatniudaje")!=null) {             //pro změnu ostatních údajů
                HashMap<Label, String> input=new HashMap<Label, String>();
                for (Label label : Label.values()) {
                    if (label.isInTable(table)&&label.isMenitelneUzivatelem()) {
                        input.put(label, request.getParameter(label.getNazevRaw()));
                    }
                }
                FormValidation form=validateForm(input, SQLTables.uchazeci, notFilledStyle);
                notFilled=form.getNotFilled();
                error=form.isError();
            }
            
            session.setAttribute("formCheck", notFilled);

            if (error) {
                response.sendRedirect("proPrihlasene.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ChangeData");
                dispatcher.forward(request, response);
            }
            
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException|ClassNotFoundException ex) {
            try {
                Logger.getLogger(ChangeDataCheck.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("chyba.jsp");
            } catch (IOException ex1) {
                Logger.getLogger(ChangeDataCheck.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
