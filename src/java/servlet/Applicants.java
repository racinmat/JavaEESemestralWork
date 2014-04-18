package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enums.SQLTables;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import enums.Label;
import java.util.HashMap;
import java.util.LinkedHashMap;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class Applicants extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private SQLTables table;
    private ArrayList<HashMap<Label, String>> udajeouzivatelich;                //proměnná, která uloží všechna data o uživatelích a potom se při čtení z tablky do ní ukládají nové ne-null hodnoty   
    private String criterium;
    private Label criteriumColumn;
    private String negate;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        SecurityCheck security=new SecurityCheck(request);
        security.noDirectAccess(response);
        if (session.getAttribute("redirect")==null) {                           //kvůli předchozímu přeměrování z důvodů zákazu přímého přístupu
            try {
                String temp= request.getParameter("table");
                this.table=SQLTables.getTableFromNumberInString(temp);          //tabulka, která bude vypisována
                session.setAttribute("tabulka", table.getNumberAsString());
                if (table.equals(SQLTables.applicants_spam)||table.equals(SQLTables.applicants_ipspam)) {
                    session.setAttribute("spam", true);                         //určuje, zda je spam true či false kvůli přesunu do tabulky uchazeci ve výpisu uchazečů
                } else {
                    session.setAttribute("spam", false);
                }
                if (request.getParameter("criterium")!=null&&request.getParameter("criteriumColumn")!=null) {
                    this.criterium=request.getParameter("criterium");           //obsah, který má být ve sloupci criteriumColumn, aby byl řádek vypsán
                    temp=request.getParameter("criteriumColumn");               //stejná proměnná, ale s tempem výš nemá nic společného, pouze dočasná proměnná, je zbytečné jich tvořit víc sériově za sebou
                    this.criteriumColumn=Label.getLabelFromStringInnameRaw(temp);//sloupec, podle kterého se bude řídit výpis
                    this.negate=request.getParameter("negate");                 //pokud je yes, potom je to negace kritéria
                }
                getApplicants(request, response);
                response.sendRedirect("seznamUchazecu.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doGet method", "Error in redirecting to seznamUchazecu.jsp. "+ex.getMessage(), ex);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String tabulka=(String)session.getAttribute("tabulka");
        String temp;
        try {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("zobrazitvysledky")!=null) {
                getApplicants(request, response);
                LinkedHashMap<Label, String> checked=new LinkedHashMap<>();
                for (Label label : Label.values()) {
                    if ((label.isShowToAdministrativa()&&label.isInTables(SQLTables.login, table))||label.equals(Label.allColumns)) {
                        temp=request.getParameter(label.getNameRaw());
                        if (temp!=null&&temp.equals("checked")) {
                            checked.put(label, "checked");
                        }
                        else {
                            checked.put(label, "");
                        }
                    }
                }
                temp=request.getParameter(Label.allColumns.getNameRaw());                    //změnit také u seznamu uchazečů
                if (temp!=null&&temp.equals("checked")) {
                    checked.put(Label.allColumns, "checked");
                    for (Label label : checked.keySet()) {
                        checked.put(label, "checked");
                    }
                }
                else {
                    checked.put(Label.allColumns, temp);
                }
                session.setAttribute("checked", checked);
            }
            if (request.getParameter("zmenitudaje")!=null) {
                ArrayList<String> transfer=new ArrayList<>();                   //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                ArrayList<LinkedHashMap<Label, String>> createstudent=new ArrayList<>();            //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                Mysql sql=new Mysql();
                getApplicants(request, response);
                int oprava=0;                                                   //používá se kvůli přenosu uchazečů na studenty a nejsou zaškrtlá všechna políčka
                    
                for (int i = 0; i < udajeouzivatelich.size(); i++) {
                    for (Label label : Label.values()) {
                        if (label.isInTables(table, SQLTables.login)&&request.getParameter(label.getNameRaw()+"+"+i)!=null) {
                            udajeouzivatelich.get(i).put(label, request.getParameter(label.getNameRaw()+"+"+i));
                        }
                    }
                    temp=request.getParameter("transfer"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        transfer.add(udajeouzivatelich.get(i).get(Label.userName));
                    }
                    temp=request.getParameter("createstudent"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        createstudent.add(new LinkedHashMap<Label, String>());  //kvůli tomu, aby se neměnilo pořadí při iteraci
                        createstudent.get(i+oprava).put(Label.userName, udajeouzivatelich.get(i).get(Label.userName));
                        createstudent.get(i+oprava).put(Label.name, udajeouzivatelich.get(i).get(Label.name));
                        createstudent.get(i+oprava).put(Label.lastname, udajeouzivatelich.get(i).get(Label.lastname));
                    } else {
                        oprava--;
                    }
                    
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
                boolean success=true;                                           //používá se jako vyhodnocovací proměnná pro přenos mezi tabulkami
                int i=0;
                while (!(i==transfer.size())&&success) {                          //while cyklus se zastaví pokud je arraylist prázdný nebo pokud se nezdaří přenos
                    success=sql.transferApplicant(table, transfer.get(i), SQLTables.applicants);
                    i++;
                    if (!success) {
                        System.out.println("Transfer failed at number: "+i);
                    }
                }
                if (!createstudent.isEmpty()) {
                    session.setAttribute("newstudent", createstudent);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pridaniStudenta.jsp");
                    dispatcher.forward(request, response);
                    response.sendRedirect("pridaniStudenta.jsp");
                }
            
            }
            
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (ClassNotFoundException|SQLException|ServletException|IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }
    
    private void getApplicants(HttpServletRequest request, HttpServletResponse response){
        try {
            Mysql sql=new Mysql();
            HttpSession session = request.getSession(true);
            if (criterium==null||criteriumColumn==null) {
                udajeouzivatelich = sql.showPeople(table);
            } else if (negate==null){
                udajeouzivatelich = sql.showPeople(table, criterium, criteriumColumn, false);
            } else {
                udajeouzivatelich = sql.showPeople(table, criterium, criteriumColumn, true);
            }
            session.setAttribute("allApplicants", udajeouzivatelich);
        } catch (SQLException|ClassNotFoundException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "getApplicants method", "Error in mysql. "+ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");                                 //dodělat stránku error tak, aby vypisovala, co předám v proměnné přes url a dodělat stránku aby tam byl header, footer atd..-
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "getApplicants method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }
}



