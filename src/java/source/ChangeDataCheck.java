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
            Encrypt crypt=new Encrypt();
            
            String username=(String) session.getAttribute("username");
            String password=request.getParameter(labelRaw[4]);
            String noveheslo=request.getParameter("noveheslo");
            String kontrola=request.getParameter("kontrola");
            boolean error=false;
            password=crypt.encrypt(password, username);
            String[] passwordcheck=sql.login(username, password);
            String[] notFilled=new String[30];                                       //upravit délku
            
            if (!passwordcheck[0].equals("success")) {
                notFilled[0]=" class=\"notFilled\"";
                error=true;
            } else {
                notFilled[0]="";
            }
            if (noveheslo.equals("")) {
                notFilled[1]=" class=\"notFilled\"";
                error=true;
            } else {
                notFilled[1]="";
            }
            if (!noveheslo.equals(kontrola)|kontrola.equals("")) {
                notFilled[2]=" class=\"notFilled\"";
                error=true;
            } else {
                notFilled[2]="";
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
