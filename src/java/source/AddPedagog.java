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
            String username=generator.getValidatedId();
            String password=generator.getId();
            String[] label=Label.getLabel();
            String[] labelRaw=Label.getLabelRaw();
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            
            Mysql sql=new Mysql();
            String[] input = new String[label.length-2];
            input[0]=username;
            for (int i = 1; i < 4; i++) {
                if (i==3) {
                    input[i]=password;
                } else {
                    input[i]=request.getParameter(labelRaw[i]);
                }
            }
            
            boolean rs=sql.insertNewUserToLogin(input);
            
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("proAdministrativu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(AddPedagog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
