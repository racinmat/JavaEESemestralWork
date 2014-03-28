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
    private String negate;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        try {
            this.table=request.getParameter("table");                           //tabulka, která bude vypisována
            session.setAttribute("tabulka", table);
            if (table.equals("uchazeci_spam")||table.equals("uchazeci_ipspam")) {
                session.setAttribute("spam", true);                             //určuje, zda je spam true či false kvůli přesunu do tabulky uchazeci ve výpisu uchazečů
            } else {
                session.setAttribute("spam", false);
            }
            this.criterium=request.getParameter("criterium");                   //obsah, který má být ve sloupci criteriumColumn, aby byl řádek vypsán
            this.criteriumColumn=request.getParameter("criteriumColumn");       //sloupec, podle kterého se bude řídit výpis
            this.negate=request.getParameter("negate");                //pokud je yes, potom je to negace kritéria
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
        String tabulka=(String)session.getAttribute("tabulka");
        if(tabulka.equals("studenti")){
            label=Label.getLabelStudent();
            labelRaw=Label.getLabelStudentRaw();
        }
        String temp;
        try {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("zobrazitvysledky")!=null) {
                getApplicants(request);
                String[] show=new String[label.length+1];                       //kvůli políčku zaškrtnout vše
                for (int i = 0; i < show.length; i++) {
                    temp=request.getParameter("sloupec"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        show[i]="show";
                    }
                    else {
                        show[i]="";
                    }
                }
                int last=show.length-1;
                temp=request.getParameter("sloupec"+last);
                if (temp!=null&&temp.equals("checked")) {
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
                ArrayList<String[]> createstudent=new ArrayList<String[]>();             //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                Mysql sql=new Mysql();
                getApplicants(request);
                int oprava=0;                                                   //používá se kvůli přenosu uchazečů na studenty a nejsou zaškrtlá všechna políčka
                    
                for (int i = 0; i < udajeouzivatelich.length; i++) {
                    for (int j = 0; j < Label.getLength(); j++) {
                        if (request.getParameter(labelRaw[j]+"+"+i)!=null) {
                            udajeouzivatelich[i][j]=request.getParameter(labelRaw[j]+"+"+i);
                        }
                    }
                    temp=request.getParameter("transfer"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        transfer.add(udajeouzivatelich[i][0]);
                    }
                    temp=request.getParameter("createstudent"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        createstudent.add(new String[3]);
                        createstudent.get(i+oprava)[0]=udajeouzivatelich[i][0];
                        createstudent.get(i+oprava)[1]=udajeouzivatelich[i][1];
                        createstudent.get(i+oprava)[2]=udajeouzivatelich[i][2];
                    } else {
                        oprava--;
                    }
                    
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
                boolean success=true;                                           //používá se jako vyhodnocovací proměnná pro přenos mezi tabulkami
                int i=0;
                while (!(i==transfer.size())&&success) {                          //while cyklus se zastaví pokud je arraylist prázdný nebo pokud se nezdaří přenos
                    success=sql.transferApplicant(table, transfer.get(i), "uchazeci");
                    i++;
                    if (!success) {
                        System.out.println("Transfer failed at number: "+i);
                    }
                }
                if (!createstudent.isEmpty()) {
                    session.setAttribute("newstudent", createstudent);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pridaniStudenta.jsp");
                    dispatcher.forward(request, response);
                    //response.sendRedirect("pridaniStudenta.jsp");
                }
            
            }
            
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(Uchazeci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getApplicants(HttpServletRequest request){
        Mysql sql=new Mysql();
        HttpSession session = request.getSession(true);
        if (criterium==null||criteriumColumn==null) {
            udajeouzivatelich = sql.showApplicants(table);
        } else if (negate==null){
            udajeouzivatelich = sql.showApplicants(table, criterium, criteriumColumn, false);
        } else {
            udajeouzivatelich = sql.showApplicants(table, criterium, criteriumColumn, true);
        }
        session.setAttribute("allApplicants", udajeouzivatelich);
            
    }
}



