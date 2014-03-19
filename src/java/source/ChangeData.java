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
public class ChangeData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            String[] label=Label.getLabel();
            String[] labelRaw=Label.getLabelRaw();
            Encrypt crypt=new Encrypt();
            String username=(String) session.getAttribute("username");
            String password=request.getParameter("noveheslo");
            boolean error=false;
            password=crypt.encrypt(password, username);
            
            Mysql sql=new Mysql();
            boolean rs=sql.updatePassword(username, password);
            if (rs) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("proPrihlasene.jsp");
        } catch (IOException ex) {
            Logger.getLogger(ChangeData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}