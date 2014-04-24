/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.IOException;
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
import source.FormValidation;
import static source.FormValidation.stripCallingCode;
import static source.FormValidation.validateForm;
import source.MyLogger;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class AddAdministrativaCheck extends HttpServlet{
    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            String notFilledStyle=" class=\"notFilled\"";
            
            HashMap<Label, String> input = new HashMap<>();
            for (Label label : Label.values()) {
                if (label.isInTables(SQLTable.ADMINISTRATIVA, SQLTable.LOGIN)&&!label.isAutoFill()) {
                    if (label.isPhonenumber()){
                        input.put(label, request.getParameter("predvolba"+label.getNameRaw())+request.getParameter(label.getNameRaw()));
                    } else {
                        input.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
            }
            FormValidation form=validateForm(input, SQLTable.ADMINISTRATIVA, notFilledStyle);
            HashMap<Label, String> notFilled=form.getNotFilled();
            boolean error=form.isError();
            input=stripCallingCode(input);
            HttpSession session = request.getSession();
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                response.sendRedirect("pridaniAdministrativy.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddAdministrativa");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in dispatching to /AddAdministrativa. "+ex.getMessage(), ex);
        }
        
    }

}
