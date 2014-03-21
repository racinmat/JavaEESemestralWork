/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.IOException;
import java.io.PrintWriter;
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
public class AddPedagogCheck extends HttpServlet {

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
            String[] labelRaw=Label.getLabelRaw();
            String[] notFilled = new String[3];
            for (int i = 1; i < notFilled.length; i++) {
                notFilled[i]="";
            }
            boolean error=false;
            String notFilledStyle=" class=\"notFilled\"";
            
            String[] input = new String[3];
            for (int i = 1; i < input.length; i++) {
                input[i]=request.getParameter(labelRaw[i+1]);
            }
            
            for (int i = 1; i < notFilled.length; i++) {                        //testování prázdnosti vyplněných polí
                if (input[i].equals("")) {                                //3 je pro heslo, to se nezadává
                    notFilled[i]=notFilledStyle;                                //pokud je notFilled notFilledStyle, pak je v daném políčku chyba a notFilledStyle definuje css pro nevyplněné labely
                    error=true;
                }
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                response.sendRedirect("pridaniPedagoga.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AddPedagog");
                dispatcher.forward(request, response);
            }
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
