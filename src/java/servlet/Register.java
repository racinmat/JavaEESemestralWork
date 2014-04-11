package servlet;



import enums.SQLTables;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            SendEmail mail=new SendEmail(username, password, request.getParameter(Label.jmeno.getNazevRaw()), request.getParameter(Label.prijmeni.getNazevRaw()), request.getParameter(Label.email.getNazevRaw()));
            Encrypt crypt=new Encrypt();
            password=crypt.encrypt(password, username);
            String ip=request.getRemoteAddr();//určuje se, do jaké tabulky se data uloží
            SQLTables tabulka=SQLTables.uchazeci;//určuje se, do jaké tabulky se data uloží
            
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
                tabulka=SQLTables.uchazeci_ipspam;
                rsIP = sql.increaseIPcount(ip);
            }
            else{
                tabulka=SQLTables.uchazeci;
                rsIP = sql.increaseIPcount(ip);
            }
            if (request.getParameter("stoletimaturity") != null) {//skryté tlačítko, formulář se uloží do průběžné tabulky a vytvoří se mu uživateslé jméno a heslo, ale ve finále kandidát na vymazání
                spam=2;//pro vyplněné skryté políčko je spam=2
                tabulka=SQLTables.uchazeci_spam;//je to tady na konci, aby do spamu padalo všechno správné i když by to jinak mělo jít do ipspamu
            }
            HashMap<Label, String> input=new HashMap<>();
            input.put(Label.uzivatelskejmeno, username);
            input.put(Label.hashhesla, password);
            for (Label label : Label.values()) {
                if (label.isInTables(SQLTables.uchazeci, SQLTables.login)&&!label.isAutomatickeVyplneni()) {
                    if (label.isTelefonniCislo()){
                        input.put(label, request.getParameter("predvolba"+label.getNazevRaw())+request.getParameter(label.getNazevRaw()));
                    } else {
                        input.put(label, request.getParameter(label.getNazevRaw()));
                    }
                }
            }
            
            input.put(Label.dennarozeni, getBirthDay(input.get(Label.rodnecislo)));//z rodného čísla, zde není zapotřebí kontrola, protože ta se provádí v RegisterChecku
            input.put(Label.mesicnarozeni, getBirthMonth(input.get(Label.rodnecislo)));
            input.put(Label.roknarozeni, getBirthYear(input.get(Label.rodnecislo)));
            input.put(Label.pohlavi, getSexFromBirthNumber(input.get(Label.rodnecislo)));
            
            for (Label label : Label.values()) {                                //pokud je něco prázdné (například číslo pasu, pokud je vyplněno číslo OP, pak bude v sql "nevyplněno")
                if (label.isInTable(SQLTables.uchazeci)&&label.getKopirovanoZ()!=null) {
                    if (input.get(label).equals("")) {
                        input.put(label, input.get(label.getKopirovanoZ()));
                    }
                }
            }
            
            for (Label label : Label.values()) {                                //pokud je něco prázdné (například číslo pasu, pokud je vyplněno číslo OP, pak bude v sql "nevyplněno")
                if (label.isInTable(SQLTables.uchazeci)&&!label.isAutomatickeVyplneni()) {
                    if (input.get(label).equals("")) {
                        input.put(label, "nevyplněno");
                    }
                }
            }
            
            boolean rsWrite=sql.insertNewApplicant(tabulka, input);
            if (rsWrite) {
                mail.sendGmailToApplicant(request.getParameter(Label.pohlavi.getNazevRaw()));//aby se email neodeslal, pokud se nezdaří registrace
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
        } catch (SQLException|ClassNotFoundException ex) {
            try {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("chyba.jsp");
            } catch (IOException ex1) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
