package source;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
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
    private String criterium;
    private String criteriumColumn;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        try {
            this.table=request.getParameter("table");                           //tabulka, která bude vypisována
            if (table.equals("uchazeci_spam")||table.equals("uchazeci_ipspam")) {
                session.setAttribute("spam", true);                             //určuje, zda je spam true či false kvůli přesunu do tabulky uchazeci ve výpisu uchazečů
            } else {
                session.setAttribute("spam", false);
            }
            this.criterium=request.getParameter("criterium");                   //obsah, který má být ve sloupci criteriumColumn, aby byl řádek vypsán
            this.criteriumColumn=request.getParameter("criteriumColumn");       //sloupec, podle kterého se bude řídit výpis
            getApplicants(request);
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String[] labelRaw=Label.getLabelRaw();
        String[] label=Label.getLabel();
        try {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("zobrazitvysledky")!=null) {
                getApplicants(request);
                String[] show=new String[label.length+1];                       //kvůli políčku zaškrtnout vše
                for (int i = 0; i < show.length; i++) {
                    if (request.getParameter("sloupec"+i)!=null&&request.getParameter("sloupec"+i).equals("checked")) {
                        show[i]="show";
                    }
                    else {
                        show[i]="";
                    }
                }
                int last=show.length-1;
                if (request.getParameter("sloupec"+last)!=null&&request.getParameter("sloupec"+last).equals("checked")) {
                    for (int i = 0; i < show.length; i++) {                    //zobrazení všech sloupců
                        show[i]="show";
                    }
                }
                else {
                    show[show.length-1]="";
                }
                session.setAttribute("show", show);
            }
            if (request.getParameter("zmenitudaje")!=null) {
                ArrayList<String> transfer=new ArrayList<String>();             //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                Mysql sql=new Mysql();
                for (int i = 0; i < udajeouzivatelich.length; i++) {
                    for (int j = 0; j < Label.getLength(); j++) {
                        if (request.getParameter(labelRaw[j]+"+"+i)!=null) {
                            udajeouzivatelich[i][j]=request.getParameter(labelRaw[j]+"+"+i);
                        }
                    }
                    if (request.getParameter("transfer"+"+"+i)!=null&&request.getParameter("transfer"+"+"+i).equals("checked")) {
                        transfer.add(udajeouzivatelich[i][0]);
                    }
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
                boolean success=true;                                           //používá se jako vyhodnocovací proměnná pro přenos mezi tabulkami
                int i=0;
                while (!transfer.isEmpty()&&success) {                          //while cyklus se zastaví pokud je arraylist prázdný nebo pokud se nezdaří přenos
                    i++;
                    success=sql.transferApplicant(table, transfer.get(i), "uchazeci");
                    if (!success) {
                        System.out.println("Transfer failed at number: "+i);
                    }
                }
            }
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getApplicants(HttpServletRequest request){
        Mysql sql=new Mysql();
        HttpSession session = request.getSession(true);
        if (criterium==null||criteriumColumn==null) {
            udajeouzivatelich = sql.showApplicants(table);
        } else {
            udajeouzivatelich = sql.showApplicants(table, criterium, criteriumColumn);
        }
        session.setAttribute("allApplicants", udajeouzivatelich);
            
    }
}



