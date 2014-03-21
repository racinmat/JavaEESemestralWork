/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static source.RegisterCheck.*;

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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession(true);
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            String[] labelRaw=Label.getLabelRaw();
            Mysql sql=new Mysql();
            Encrypt crypt=new Encrypt();
            String username=(String) session.getAttribute("username");
            boolean error=false;
            String notFilledStyle=" class=\"notFilled\"";
            String[] notFilled=new String[22];                                  //poloha v poli je podle umístění v proPrihlasene.jsp
            for (int i = 0; i < notFilled.length; i++) {
                notFilled[i]="";
            }
            
            if (request.getParameter("zmenitheslo")!=null) {                    //pro změnu hesla
                String password=request.getParameter(labelRaw[4]);
                String noveheslo=request.getParameter("noveheslo");
                String kontrola=request.getParameter("kontrola");
                password=crypt.encrypt(password, username);
                String[] passwordcheck=sql.login(username, password);

                if (!passwordcheck[0].equals("success")) {
                    notFilled[0]=notFilledStyle;
                    error=true;
                }
                if (noveheslo.equals("")) {
                    notFilled[1]=notFilledStyle;
                    error=true;
                }
                if (!noveheslo.equals(kontrola)|kontrola.equals("")) {
                    notFilled[2]=notFilledStyle;
                    error=true;
                }
                
            }
            if (request.getParameter("zmenitostatniudaje")!=null) {             //pro změnu ostatních údajů
                String[] input=new String[19];
                input[0]=request.getParameter(labelRaw[9]);
                for (int i = 18; i <= 35; i++) {
                    input[i-17]=request.getParameter(labelRaw[i]);
                }
                
                if ((!input[0].equals(""))&&notValidEmail(input[0])){
                    notFilled[3]=notFilledStyle;
                    error=true;                                                 //pokud neprojde validace, nastaví se error na false
                }
                
                if ((!input[2].equals(""))&&notNumeric(input[2])) {
                    notFilled[4]=notFilledStyle;                                //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
                
                if ((!input[6].equals(""))&&notNumeric(input[6])) {
                    notFilled[8]=notFilledStyle;                                //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
                
                if ((!input[8].equals(""))&&notNumeric(input[8])) {
                    notFilled[10]=notFilledStyle;                               //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
                
                if ((!input[11].equals(""))&&notNumeric(input[11])) {
                    notFilled[13]=notFilledStyle;                               //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
                
                if ((!input[15].equals(""))&&notNumeric(input[15])) {
                    notFilled[17]=notFilledStyle;                               //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
                
                if ((!input[17].equals(""))&&notNumeric(input[17])) {
                    notFilled[19]=notFilledStyle;                               //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;                                                 //u jsp proPrihlasene není povinnost vyplnit políčka, proto je jenom kontrola čísel a ostatní notFilled jsou automaticky nastaveny na ""
                }
                
            }
            
            session.setAttribute("formCheck", notFilled);

            if (error) {
                response.sendRedirect("proPrihlasene.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ChangeData");
                dispatcher.forward(request, response);
            }
            
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
