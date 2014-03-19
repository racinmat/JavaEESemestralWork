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
            
            String username=(String) session.getAttribute("name");
            String password=request.getParameter(labelRaw[4]);
            String noveheslo=request.getParameter("noveheslo");
            String kontrola=request.getParameter("kontrola");
            String[] passwordcheck=sql.login(username, password);
            if (!passwordcheck[0].equals("success")) {
                
            }
            if (!noveheslo.equals(kontrola)) {
                
            }
            
            
            
            String[] input = new String[labelRaw.length-2];
            boolean[] notFilled = new boolean[labelRaw.length-2];
            for (int i = 0; i < 3; i++) {
                notFilled[i]=false;
            }
            boolean error=false;
            
            for (int i = 1; i < labelRaw.length-2; i++) {
                if (i==3) {
                    
                } else {
                    input[i]=request.getParameter(labelRaw[i]);
                }
            }
            
            for (int i = 10; i <= 15; i++) {
                if (notNumeric(input[i])) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            if (notNumeric(input[19])) {
                notFilled[19]=true;                                             //pokud je notFilled true, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[23])) {
                notFilled[23]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[25])) {
                notFilled[25]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[28])) {
                notFilled[28]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[32])) {
                notFilled[32]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[34])) {
                notFilled[34]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[42])) {
                notFilled[42]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            for (int i = 1; i < notFilled.length; i++) {                        //testování prázdnosti vyplněných polí
                if (i!=3&&input[i].equals("")) {                                //3 je pro heslo, to se nezadává
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            String email=input[9];
            int atposition=email.indexOf("@");                                  //pozice zavináče
            int dotposition=email.lastIndexOf(".");                             //pozice tečky
            if (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length()){
                notFilled[9]=true;
                error=true;                                                     //pokud neprojde validace, nastaví se error na false
            }
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                response.sendRedirect("proUchazece.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register");
                dispatcher.forward(request, response);
            }
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
