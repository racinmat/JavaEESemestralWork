package source;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
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
public class Uchazeci extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            Mysql sql=new Mysql();
            String table=request.getParameter("table");
            String[][] output = sql.showApplicants(table);
            session.setAttribute("allApplicants", output);
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter("zobrazitvysledky")!=null) {
                HttpSession session = request.getSession(true);
                Label lab=new Label();
                String[] label=lab.getLabel();
                String[] show=new String[lab.getLength()];
                for (int i = 0; i < show.length; i++) {
                    if (request.getParameter("sloupec"+i)!=null&&request.getParameter("sloupec"+i).equals("checked")) {
                        show[i]="show";
                    }
                    else {
                        show[i]="";
                    }
                }
                session.setAttribute("show", show);
            }
            if (request.getParameter("zmenitudaje")!=null) {
                HttpSession session = request.getSession(true);
                Mysql sql=new Mysql();
                Label lab=new Label();
                String[] labelRaw=lab.getLabelRaw();
                String table=request.getParameter("table");
                int count=0;
                while(request.getParameter(labelRaw[0]+"+"+count)!=null){
                    count++;
                }
                String[][] uchazec=new String[count][lab.getLength()];
                for (int i = 0; i < uchazec.length; i++) {
                    for (int j = 0; j < lab.getLength(); j++) {
                        uchazec[i][j]=request.getParameter(labelRaw[j]+"+"+i);
                    }
                }
                String[] show=(String[]) session.getAttribute("show");
                boolean output = sql.updateApplicants(table, uchazec, show);
            }
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



