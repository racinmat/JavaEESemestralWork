/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

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
import static source.RegisterCheck.notNumeric;

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
            String[] labelRaw=Label.getLabelStudentRaw();
            boolean error=false;
            String notFilledStyle=" class=\"notFilled\"";
            ArrayList<String> seznamStudentu=(ArrayList<String>) session.getAttribute("newstudent");
            String[][] input = new String[seznamStudentu.size()][5];
            String[][] notFilled = new String[seznamStudentu.size()][labelRaw.length];
            for (int i = 0; i < notFilled.length; i++) {
                for (int j = 0; j < notFilled[0].length; j++) {
                    notFilled[i][j]="";
                }
            }
            for (int i = 0; i < input.length; i++) {
                String[] temp=new String[5];
                temp[1]=request.getParameter(labelRaw[1]+"+"+i);
                temp[2]=request.getParameter(labelRaw[2]+"+"+i);
                temp[3]=request.getParameter(labelRaw[3]+"+"+i);

                for (int j = 1; j < notFilled.length; j++) {                    //testování prázdnosti vyplněných polí
                    if (temp[j].equals("")) {                                   //3 je pro heslo, to se nezadává
                        notFilled[i][j]=notFilledStyle;                                //pokud je notFilled notFilledStyle, pak je v daném políčku chyba a notFilledStyle definuje css pro nevyplněné labely
                        error=true;
                    }
                }

                if (notNumeric(temp[3])) {
                    notFilled[i][3]=notFilledStyle;                                //pokud je notFilled notFilledStyle, pak je v daném políčku chyba a notFilledStyle definuje css pro nevyplněné labely
                    error=true;
                }

                if (notNumeric(temp[5])) {
                    notFilled[i][5]=notFilledStyle;                                //pokud je notFilled notFilledStyle, pak je v daném políčku chyba a notFilledStyle definuje css pro nevyplněné labely
                    error=true;
                }
                input[i]=temp;
            }
            
            
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                response.sendRedirect("pridaniPedagoga.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStudent");
                dispatcher.forward(request, response);
            }
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
