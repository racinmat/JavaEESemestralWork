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
    private String table;
    private String[][] udajeouzivatelich;                                       //proměnná, která uloží všechna data o uživatelích a potom se při čtení z tablky do ní ukládají nové ne-null hodnoty   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.table=request.getParameter("table");
            getApplicants(request);
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("zobrazitvysledky")!=null) {
                getApplicants(request);
                HttpSession session = request.getSession(true);
                String[] label=Label.getLabel();
                String[] show=new String[Label.getLength()];
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
                String[] labelRaw=Label.getLabelRaw();
                for (int i = 0; i < udajeouzivatelich.length; i++) {
                    for (int j = 0; j < Label.getLength(); j++) {
                        if (request.getParameter(labelRaw[j]+"+"+i)!=null) {
                            udajeouzivatelich[i][j]=request.getParameter(labelRaw[j]+"+"+i);
                        }
                    }
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
            }
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getApplicants(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Mysql sql=new Mysql();
        udajeouzivatelich = sql.showApplicants(table);
        session.setAttribute("allApplicants", udajeouzivatelich);
            
    }
}



