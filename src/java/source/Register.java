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
        SendEmail mail=new SendEmail(username, password, request.getParameter("jmeno"), request.getParameter("prijmeni"), request.getParameter("pohlavi"), request.getParameter("email"));
        mail.sendGmail();
        Encrypt crypt=new Encrypt();
        password=crypt.encrypt(password, username);
        String ip=request.getRemoteAddr();//adresa kvůli spamům-10 přihlášek za den ze stejné adresy, reset o půlnoci, při zaslání 11. přihlášky žádost o verifikaci emailem a uložení do průběžné tabulky dokud nebude verifikováno
        if(true){//podmínka, aby byla vyplněná všechna povinná políčka
            String tabulka="uchazeci";//určuje se, do jaké tabulky se data uloží
                
            int size=0;                                                         //vyberu z tabulky stejnou ip adresu, pokud tam není, tak vytvořím nový řádek
            int rsIP=0;
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
            input[1]=request.getParameter("jmeno");
            input[2]=request.getParameter("prijmeni");
            input[3]=password;
            
            input[4]=request.getParameter("studijniprogram");
            input[5]=request.getParameter("studijniobor");
            input[6]=request.getParameter("pohlavi");
            input[7]=request.getParameter("statniprislusnost");
            input[8]=request.getParameter("rodinnystav");
            input[9]=request.getParameter("email");
            input[10]=request.getParameter("narden");
            input[11]=request.getParameter("narmesic");
            input[12]=request.getParameter("narrok");
            input[13]=request.getParameter("cisloOP");
            input[14]=request.getParameter("rodnecislo");
            input[15]=request.getParameter("cislopasu");
            input[16]=request.getParameter("narmisto");
            input[17]=request.getParameter("narokres");
            input[18]=request.getParameter("ulice");
            input[19]=request.getParameter("cislodomu");
            input[20]=request.getParameter("castobce");
            input[21]=request.getParameter("obec");
            input[22]=request.getParameter("okres");
            input[23]=request.getParameter("psc");
            input[24]=request.getParameter("stat");
            input[25]=request.getParameter("telefon");
            input[26]=request.getParameter("posta");
            input[27]=request.getParameter("kulice");
            input[28]=request.getParameter("kcislodomu");
            input[29]=request.getParameter("ktelefon");
            input[30]=request.getParameter("kcastobce");
            input[31]=request.getParameter("kobec");
            input[32]=request.getParameter("kokres");
            input[33]=request.getParameter("kpsc");
            input[34]=request.getParameter("kposta");
            input[35]=request.getParameter("kstat");
            input[36]=request.getParameter("ssnazev");
            input[37]=request.getParameter("ssadresa");
            input[38]=request.getParameter("ssobor");
            input[39]=request.getParameter("jkov");
            input[40]=request.getParameter("kkov");
            input[41]=request.getParameter("izo");
            input[42]=request.getParameter("rokmaturity");
            
            int rsWrite=sql.insertNewApplicant(tabulka, input);
            if (rsIP==1&rsWrite==1) {
                if (spam==0) {
                    session.setAttribute("registered", "success");
                } else if(spam==1) {
                    session.setAttribute("registered", "ip");
                } else {
                    session.setAttribute("registered", "spam");
                }
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("proUchazece.jsp");
        }
    }
}
