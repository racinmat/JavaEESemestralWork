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

/**
 *
 * @author Azathoth
 */
public class AddAdministrativa extends HttpServlet {

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
            String[] input = new String[7];
            input[0]=username;
            input[3]=password;
            input[1]=request.getParameter(labelRaw[1]);
            input[2]=request.getParameter(labelRaw[2]);
            input[4]=request.getParameter(labelRaw[9]);
            input[5]=request.getParameter("predvolba"+labelRaw[25])+request.getParameter(labelRaw[25]);
            input[6]=request.getParameter("predvolba"+labelRaw[43])+request.getParameter(labelRaw[43]);
            
            boolean rs=sql.insertNewAdministrativa(input);
            
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("pridaniAdministrativy.jsp");
        } catch (IOException ex) {
            Logger.getLogger(AddPedagog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

