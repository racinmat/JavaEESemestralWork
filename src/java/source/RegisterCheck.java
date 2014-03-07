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

/**
 *
 * @author Azathoth
 */
public class RegisterCheck extends HttpServlet {

    public static boolean notNumeric(String str)  {  
    try  
        {  
          int d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException nfe)  
        {  
          return true;  
        }  
        return false;  
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");          //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            boolean[] notFilled = new boolean[43];
            for (int i = 0; i < notFilled.length; i++) {
                notFilled[i]=false;
            }
            boolean error=false;
            String[] input = new String[43];
            input[2]=request.getParameter("jmeno");
            input[3]=request.getParameter("prijmeni");
            input[4]=request.getParameter("studijniprogram");
            input[5]=request.getParameter("studijniobor");
            input[6]=request.getParameter("pohlavi");
            input[7]=request.getParameter("statniprislusnost");
            input[8]=request.getParameter("email");
            input[9]=request.getParameter("rodinnystav");
            input[10]=request.getParameter("narden");
            input[11]=request.getParameter("narmesic");
            input[12]=request.getParameter("narrok");
            input[13]=request.getParameter("narmisto");
            input[14]=request.getParameter("narokres");
            input[15]=request.getParameter("cisloOP");
            input[16]=request.getParameter("rodnecislo");
            input[17]=request.getParameter("cislopasu");
            input[18]=request.getParameter("ulice");
            input[19]=request.getParameter("cislodomu");
            input[20]=request.getParameter("telefon");
            input[21]=request.getParameter("castobce");
            input[22]=request.getParameter("obec");
            input[23]=request.getParameter("okres");
            input[24]=request.getParameter("psc");
            input[25]=request.getParameter("posta");
            input[26]=request.getParameter("stat");
            input[27]=request.getParameter("kulice");
            input[28]=request.getParameter("kcislodomu");
            input[29]=request.getParameter("ktelefon");
            input[30]=request.getParameter("kcastobce");
            input[31]=request.getParameter("kobec");
            input[32]=request.getParameter("kokres");
            input[33]=request.getParameter("kpsc");
            input[34]=request.getParameter("kposta");
            input[35]=request.getParameter("kstat");
            input[36]=request.getParameter("ssnazev");
            input[37]=request.getParameter("ssadresa");
            input[38]=request.getParameter("ssobor");
            input[39]=request.getParameter("jkov");
            input[40]=request.getParameter("kkov");
            input[41]=request.getParameter("izo");
            input[42]=request.getParameter("rokmaturity");
            
            for (int i = 10; i <= 12; i++) {
                if (notNumeric(input[i])) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            for (int i = 15; i <= 17; i++) {
                if (notNumeric(input[i])) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            for (int i = 19; i <= 20; i++) {
                if (notNumeric(input[i])) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            if (notNumeric(input[24])) {
                notFilled[24]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            for (int i = 28; i <= 29; i++) {
                if (notNumeric(input[i])) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            if (notNumeric(input[33])) {
                notFilled[33]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[42])) {
                notFilled[42]=true;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            
            
            
            
            for (int i = 2; i < notFilled.length; i++) {                        //testování prázdnosti vyplněných polí
                if (input[i].equals("")) {
                    notFilled[i]=true;                                          //pokud je notFilled 1, pak je v daném políčku chyba
                    error=true;
                }
            }
            String email=input[8];
            int atposition=email.indexOf("@");                                  //pozice zavináče
            int dotposition=email.lastIndexOf(".");                             //pozice tečky
            if (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length()){
                notFilled[8]=true;
                error=true;                                                     //pokud neprojde validace, nastaví se error na false
            }
            HttpSession session = request.getSession();
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
