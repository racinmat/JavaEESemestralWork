package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enums.SQLTable;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import source.MyLogger;
import source.Mysql;
import source.SecurityCheck;

/**
 *
 * @author Azathoth
 */
public class Applicants extends HttpServlet {

    private SQLTable table;
    private List<Map<Label, String>> udajeouzivatelich;                         //proměnná, která uloží všechna data o uživatelích a potom se při čtení z tablky do ní ukládají nové ne-null hodnoty   
    private String criterium;
    private Label criteriumColumn;
    private String negate;
    
    /**
     * Processes requests for HTTP <code>GET</code> method.
     * Prepares data about users from SQL table and then redirect user to jsp page where can be data seen.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        SecurityCheck security=new SecurityCheck(request);
        security.noDirectAccess(response);
        if (session.getAttribute("redirect")==null) {                           //kvůli předchozímu přeměrování z důvodů zákazu přímého přístupu
            try {
                String temp= request.getParameter("table");
                this.table=SQLTable.getTableFromNumberInString(temp);          //tabulka, která bude vypisována
                session.setAttribute("tabulka", table.getNumberAsString());
                if (table.equals(SQLTable.APPLICANTS_SPAM)||table.equals(SQLTable.APPLICANTS_IPSPAM)) {
                    session.setAttribute("spam", true);                         //určuje, zda je spam true či false kvůli přesunu do tabulky uchazeci ve výpisu uchazečů
                } else {
                    session.setAttribute("spam", false);
                }
                if (request.getParameter("criterium")!=null&&request.getParameter("criteriumColumn")!=null) {
                    this.criterium=request.getParameter("criterium");           //obsah, který má být ve sloupci criteriumColumn, aby byl řádek vypsán
                    temp=request.getParameter("criteriumColumn");               //stejná proměnná, ale s tempem výš nemá nic společného, pouze dočasná proměnná, je zbytečné jich tvořit víc sériově za sebou
                    this.criteriumColumn=Label.getLabelFromStringInNameRaw(temp);//sloupec, podle kterého se bude řídit výpis
                    this.negate=request.getParameter("negate");                 //pokud je yes, potom je to negace kritéria
                }
                getApplicants(request, response);
                response.sendRedirect("seznamUchazecu.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doGet method", "Error in redirecting to seznamUchazecu.jsp. "+ex.getMessage(), ex);
            }
        }
    }
    
    /**
     * Processes requests for HTTP <code>POST</code> method.
     * Validates data from form, writes them to sql table and redirects user to next jsp page.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String temp;
        try {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("zobrazitvysledky")!=null) {
                getApplicants(request, response);
                Map<Label, String> checked=new LinkedHashMap<>();
                for (Label label : Label.values()) {
                    if ((label.isShowToAdministrativa()&&label.isInTables(SQLTable.LOGIN, table))||label.equals(Label.ALL_COLUMNS)) {
                        temp=request.getParameter(label.getNameRaw());
                        if (temp!=null&&temp.equals("checked")) {
                            checked.put(label, "checked");
                        }
                        else {
                            checked.put(label, "");
                        }
                    }
                }
                temp=request.getParameter(Label.ALL_COLUMNS.getNameRaw());                    //změnit také u seznamu uchazečů
                if (temp!=null&&temp.equals("checked")) {
                    checked.put(Label.ALL_COLUMNS, "checked");
                    for (Label label : checked.keySet()) {
                        checked.put(label, "checked");
                    }
                }
                else {
                    checked.put(Label.ALL_COLUMNS, temp);
                }
                session.setAttribute("checked", checked);
            }
            if (request.getParameter("zmenitudaje")!=null) {
                List<String> transfer=new ArrayList<>();                        //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                List<LinkedHashMap<Label, String>> createstudent=new ArrayList<>();            //arraylist na id uživatelů, kteří budou přeneseni ze spamové tabulky do tabulky běžných uchazečů
                Mysql sql=new Mysql();
                getApplicants(request, response);
                int oprava=0;                                                   //používá se kvůli přenosu uchazečů na studenty a nejsou zaškrtlá všechna políčka
                    
                for (int i = 0; i < udajeouzivatelich.size(); i++) {
                    for (Label label : Label.values()) {
                        if (label.isInTables(table, SQLTable.LOGIN)&&request.getParameter(label.getNameRaw()+"+"+i)!=null) {
                            udajeouzivatelich.get(i).put(label, request.getParameter(label.getNameRaw()+"+"+i));
                        }
                    }
                    temp=request.getParameter("transfer"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        transfer.add(udajeouzivatelich.get(i).get(Label.USERNAME));
                    }
                    temp=request.getParameter("createstudent"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        createstudent.add(new LinkedHashMap<Label, String>());  //kvůli tomu, aby se neměnilo pořadí při iteraci
                        createstudent.get(i+oprava).put(Label.USERNAME, udajeouzivatelich.get(i).get(Label.USERNAME));
                        createstudent.get(i+oprava).put(Label.NAME, udajeouzivatelich.get(i).get(Label.NAME));
                        createstudent.get(i+oprava).put(Label.LASTNAME, udajeouzivatelich.get(i).get(Label.LASTNAME));
                    } else {
                        oprava--;
                    }
                    
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
                boolean success=true;                                           //používá se jako vyhodnocovací proměnná pro přenos mezi tabulkami
                int i=0;
                if (!output&&session.getAttribute("redirect")==null) {
                    session.setAttribute("redirect", "true");
                    response.sendRedirect("chyba.jsp?error=0");                 //pokud se nezdaří úprava
                }
                while (!(i==transfer.size())&&success) {                          //while cyklus se zastaví pokud je arraylist prázdný nebo pokud se nezdaří přenos
                    success=sql.transferApplicant(table, transfer.get(i), SQLTable.APPLICANTS);
                    i++;
                }
                if (!createstudent.isEmpty()&&session.getAttribute("redirect")==null) {
                    session.setAttribute("newstudent", createstudent);
                    response.sendRedirect("pridaniStudenta.jsp");
                }
            
            }
            if (session.getAttribute("redirect")==null) {
                response.sendRedirect("seznamUchazecu.jsp");
            }
        } catch (ClassNotFoundException|SQLException|IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }
    
    /**
     * Gets data from SQL table and prepares them to fiels.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
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



