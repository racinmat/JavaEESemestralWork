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
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import enums.Label;
import java.util.HashMap;
import java.util.LinkedHashMap;
import source.Mysql;

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
        try {
            String temp= request.getParameter("table");
            this.table=SQLTables.getTableFromNumberInString(temp);                        //tabulka, která bude vypisována
            session.setAttribute("tabulka", table.getNumberAsString());
            if (table.equals(SQLTables.uchazeci_spam)||table.equals(SQLTables.uchazeci_ipspam)) {
                session.setAttribute("spam", true);                             //určuje, zda je spam true či false kvůli přesunu do tabulky uchazeci ve výpisu uchazečů
            } else {
                session.setAttribute("spam", false);
            }
            if (request.getParameter("criterium")!=null&&request.getParameter("criteriumColumn")!=null) {
                this.criterium=request.getParameter("criterium");                   //obsah, který má být ve sloupci criteriumColumn, aby byl řádek vypsán
                temp=request.getParameter("criteriumColumn");                       //stejná proměnná, ale s tempem výš nemá nic společného, pouze dočasná proměnná, je zbytečné jich tvořit víc sériově za sebou
                this.criteriumColumn=Label.getLabelFromStringInNazevRaw(temp);      //sloupec, podle kterého se bude řídit výpis
                this.negate=request.getParameter("negate");                         //pokud je yes, potom je to negace kritéria
            }
            getApplicants(request, response);
            response.sendRedirect("seznamUchazecu.jsp");
        } catch (IOException ex) {
            Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex);
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
                    if ((label.isVypisProAdministrativu()&&label.isInTables(SQLTables.login, table))||label.equals(Label.vsechnySloupce)) {
                        temp=request.getParameter(label.getNazevRaw());
                        if (temp!=null&&temp.equals("checked")) {
                            checked.put(label, "checked");
                        }
                        else {
                            checked.put(label, "");
                        }
                    }
                }
                temp=request.getParameter(Label.vsechnySloupce.getNazevRaw());                    //změnit také u seznamu uchazečů
                if (temp!=null&&temp.equals("checked")) {
                    checked.put(Label.vsechnySloupce, "checked");
                    for (Label label : checked.keySet()) {
                        checked.put(label, "checked");
                    }
                }
                else {
                    checked.put(Label.vsechnySloupce, temp);
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
                        if (label.isInTables(table, SQLTables.login)&&request.getParameter(label.getNazevRaw()+"+"+i)!=null) {
                            udajeouzivatelich.get(i).put(label, request.getParameter(label.getNazevRaw()+"+"+i));
                        }
                    }
                    temp=request.getParameter("transfer"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        transfer.add(udajeouzivatelich.get(i).get(Label.uzivatelskejmeno));
                    }
                    temp=request.getParameter("createstudent"+"+"+i);
                    if (temp!=null&&temp.equals("checked")) {
                        createstudent.add(new LinkedHashMap<Label, String>());  //kvůli tomu, aby se neměnilo pořadí při iteraci
                        createstudent.get(i+oprava).put(Label.uzivatelskejmeno, udajeouzivatelich.get(i).get(Label.uzivatelskejmeno));
                        createstudent.get(i+oprava).put(Label.jmeno, udajeouzivatelich.get(i).get(Label.jmeno));
                        createstudent.get(i+oprava).put(Label.prijmeni, udajeouzivatelich.get(i).get(Label.prijmeni));
                    } else {
                        oprava--;
                    }
                    
                }
                boolean output = sql.updateApplicants(table, udajeouzivatelich);
                boolean success=true;                                           //používá se jako vyhodnocovací proměnná pro přenos mezi tabulkami
                int i=0;
                while (!(i==transfer.size())&&success) {                          //while cyklus se zastaví pokud je arraylist prázdný nebo pokud se nezdaří přenos
                    success=sql.transferApplicant(table, transfer.get(i), SQLTables.uchazeci);
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
        } catch (ClassNotFoundException|SQLException ex) {
            try {
                Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("chyba.jsp?error=0");
            } catch (IOException ex1) {
                Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ServletException|IOException ex) {
            Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("chyba.jsp?error=0");                                 //dodělat stránku error tak, aby vypisovala, co předám v proměnné přes url a dodělat stránku aby tam byl header, footer atd..-
            } catch (IOException ex1) {
                Logger.getLogger(Applicants.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}



