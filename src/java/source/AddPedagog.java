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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static source.RegisterCheck.getBirthDay;
import static source.RegisterCheck.getBirthMonth;
import static source.RegisterCheck.getBirthYear;
import static source.RegisterCheck.notValidBirthNumber;

/**
 *
 * @author Azathoth
 */
public class AddPedagog extends HttpServlet {

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
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(true);
            UsernameGen generator=new UsernameGen(10);
            Mysql sql=new Mysql();
            String username=generator.getValidatedId();
            String password=generator.getId();
            String[] label=Label.getLabel();
            String[] labelRaw=Label.getLabelRaw();
            
            String[] input = new String[7];
            input[0]=username;
            input[3]=password;
            input[1]=request.getParameter(labelRaw[1]);
            input[2]=request.getParameter(labelRaw[2]);
            input[4]=request.getParameter(labelRaw[9]);
            input[5]=request.getParameter("predvolba"+labelRaw[25])+request.getParameter(labelRaw[25]);
            input[6]=request.getParameter("predvolba"+labelRaw[43])+request.getParameter(labelRaw[43]);
            
            SendEmail mail=new SendEmail(input[0], input[3], input[1], input[2], input[4]);
            mail.sendGmailToRegisteredUser();
            
            Encrypt crypt=new Encrypt();
            input[3]=crypt.encrypt(input[3], username);
            
            boolean rs=sql.insertNewPedagog(input);
            
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniPedagoga.jsp");
        } catch (IOException ex) {
            Logger.getLogger(AddPedagog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
