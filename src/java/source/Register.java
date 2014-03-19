package source;



import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(true);
        UsernameGen generator=new UsernameGen(10);
        String username=generator.getValidatedId();
        String password=generator.getId();
        String[] label=Label.getLabel();
        String[] labelRaw=Label.getLabelRaw();
        SendEmail mail=new SendEmail(username, password, request.getParameter(labelRaw[1]), request.getParameter(labelRaw[2]), request.getParameter(labelRaw[6]), request.getParameter(labelRaw[9]));
        mail.sendGmail();
        Encrypt crypt=new Encrypt();
        password=crypt.encrypt(password, username);
        String ip=request.getRemoteAddr();//adresa kvůli spamům-10 přihlášek za den ze stejné adresy, reset o půlnoci, při zaslání 11. přihlášky žádost o verifikaci emailem a uložení do průběžné tabulky dokud nebude verifikováno
        String tabulka="uchazeci";//určuje se, do jaké tabulky se data uloží
            
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
            tabulka="uchazeci_ipspam";
            rsIP = sql.increaseIPcount(ip);
        }
        else{
            tabulka="uchazeci";
            rsIP = sql.increaseIPcount(ip);
        }
        if (request.getParameter("stoletimaturity") != null) {//skryté tlačítko, formulář se uloží do průběžné tabulky a vytvoří se mu uživateslé jméno a heslo, ale ve finále kandidát na vymazání
            spam=2;//pro vyplněné skryté políčko je spam=2
            tabulka="uchazeci_spam";//je to tady na konci, aby do spamu padalo všechno správné i když by to jinak mělo jít do ipspamu
        }
        String[] input = new String[43];
        input[0]=username;
        for (int i = 1; i < label.length-2; i++) {
            if (i==3) {
                input[i]=password;
            } else {
                input[i]=request.getParameter(labelRaw[i]);
            }
        }
        
        boolean rsWrite=sql.insertNewApplicant(tabulka, input);
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
        response.sendRedirect("proUchazece.jsp");
    }
}
