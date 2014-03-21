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
            Mysql sql=new Mysql();
            boolean update=false;
            String username=(String) session.getAttribute("username");
                
            if (request.getParameter("zmenitheslo")!=null) {
                Encrypt crypt=new Encrypt();
                String password=request.getParameter("noveheslo");
                boolean error=false;
                password=crypt.encrypt(password, username);

                update=sql.updatePassword(username, password);
                
                if (update) {
                    session.setAttribute("registered", "passwordsuccess");
                } else {
                    session.setAttribute("registered", "passwordfail");
                }
            }
            if (request.getParameter("zmenitostatniudaje")!=null) {             //pro změnu ostatních údajů
                String tabulka=sql.findTableWithApplicant(username);
                String[] udaje=sql.showApplicant(username, tabulka);
                String[] noveudaje=new String[19];
                noveudaje[0]=request.getParameter(labelRaw[9]);
                for (int i = 18; i <= 35; i++) {
                    noveudaje[i-17]=request.getParameter(labelRaw[i]);
                }
                if (!noveudaje[0].equals("")) {
                    udaje[9]=noveudaje[0];
                }
                for (int i = 18; i <= 35; i++) {
                    if (!noveudaje[i-17].equals("")) {
                        udaje[i]=noveudaje[i-17];
                    }
                }
                
                update=sql.updateApplicant(udaje, tabulka);
                
                if (update) {
                    session.setAttribute("registered", "success");
                } else {
                    session.setAttribute("registered", "fail");
                }
            }
            
            
            
            response.sendRedirect("proPrihlasene.jsp");
            
        } catch (IOException ex) {
            Logger.getLogger(ChangeData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}