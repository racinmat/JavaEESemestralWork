package servlet;



import enums.SQLTables;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import source.Encrypt;
import enums.Label;
import java.util.HashMap;
import source.Mysql;
import source.SendEmail;
import source.UsernameGen;
import static source.FormValidation.*;
import source.MyLogger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */
public class Register extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession(true);
            UsernameGen generator=new UsernameGen(10);
            String username=generator.getValidatedId();
            String password=generator.getId();
            SendEmail mail=new SendEmail(username, password, request.getParameter(Label.name.getNameRaw()), request.getParameter(Label.lastname.getNameRaw()), request.getParameter(Label.email.getNameRaw()));
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            String ip=request.getRemoteAddr();//určuje se, do jaké tabulky se data uloží
            SQLTables tabulka=SQLTables.applicants;//určuje se, do jaké tabulky se data uloží
            
            int size=0;                                                         //vyberu z tabulky stejnou ip adresu, pokud tam není, tak vytvořím nový řádek
            boolean rsIP=false;
            int spam=0;
            Mysql sql=new Mysql();
            int count=sql.findIP(ip);
            if (count==0) {
                rsIP = sql.insertNewIP(ip);
            }
            else if(count>10){
                spam=1;                                                         //pro stejnou ip je spam=1
                tabulka=SQLTables.applicants_ipspam;
                rsIP = sql.increaseIPcount(ip);
            }
            else{
                tabulka=SQLTables.applicants;
                rsIP = sql.increaseIPcount(ip);
            }
            if (request.getParameter("stoletimaturity") != null) {//skryté tlačítko, formulář se uloží do průběžné tabulky a vytvoří se mu uživateslé jméno a heslo, ale ve finále kandidát na vymazání
                spam=2;//pro vyplněné skryté políčko je spam=2
                tabulka=SQLTables.applicants_spam;//je to tady na konci, aby do spamu padalo všechno správné i když by to jinak mělo jít do ipspamu
            }
            HashMap<Label, String> input=new HashMap<>();
            input.put(Label.userName, username);
            input.put(Label.password, password);
            for (Label label : Label.values()) {
                if (label.isInTables(SQLTables.applicants, SQLTables.login)&&!label.isAutoFill()) {
                    if (label.isPhonenumber()){
                        input.put(label, request.getParameter("predvolba"+label.getNameRaw())+request.getParameter(label.getNameRaw()));
                    } else {
                        input.put(label, request.getParameter(label.getNameRaw()));
                    }
                }
            }
            
            input.put(Label.birthday, getBirthDay(input.get(Label.birthnumber)));//z rodného čísla, zde není zapotřebí kontrola, protože ta se provádí v RegisterChecku
            input.put(Label.birthmonth, getBirthMonth(input.get(Label.birthnumber)));
            input.put(Label.birthyear, getBirthYear(input.get(Label.birthnumber)));
            input.put(Label.sex, getSexFromBirthNumber(input.get(Label.birthnumber)));
            
            for (Label label : Label.values()) {                                //pokud je něco prázdné (například číslo pasu, pokud je vyplněno číslo OP, pak bude v sql "nevyplněno")
                if (label.isInTable(SQLTables.applicants)&&label.getCopiedFrom()!=null) {
                    if (input.get(label).equals("")) {
                        input.put(label, input.get(label.getCopiedFrom()));
                    }
                }
            }
            
            for (Label label : Label.values()) {                                //pokud je něco prázdné (například číslo pasu, pokud je vyplněno číslo OP, pak bude v sql "nevyplněno")
                if (label.isInTable(SQLTables.applicants)&&!label.isAutoFill()) {
                    if (input.get(label).equals("")) {
                        input.put(label, "nevyplněno");
                    }
                }
            }
            
            boolean rsWrite=sql.insertNewApplicant(tabulka, input);
            if (rsWrite) {
                mail.sendGmailToApplicant(request.getParameter(Label.sex.getNameRaw()));//aby se email neodeslal, pokud se nezdaří registrace
            }
            System.out.println("rsWrite:"+rsWrite);
            System.out.println("rsIP:"+rsIP);
            System.out.println("spam:"+spam);
            
            if (rsIP&rsWrite) {
                if (spam==0) {
                    session.setAttribute("registered", "success");
                } else if(spam==1) {
                    session.setAttribute("registered", "ip");
                } else if(spam==2) {
                    session.setAttribute("registered", "spam");
                }
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("proUchazece_Prihlaska.jsp");
        } catch (SQLException|ClassNotFoundException|IOException ex) {
            try {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", ex.getMessage(), ex);
                response.sendRedirect("chyba.jsp");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "doPost method", "Error in redirecting to chyba.jsp?error=0. "+ex1.getMessage(), ex1);
            }
        }
    }
}
