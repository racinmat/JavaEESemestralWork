

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Encrypt crypt=new Encrypt();
        try {
            password=crypt.encrypt(password, username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ip=request.getRemoteAddr();//adresa kvůli spamům-10 přihlášek za den ze stejné adresy, reset o půlnoci, při zaslání 11. přihlášky žádost o verifikaci emailem a uložení do průběžné tabulky dokud nebude verifikováno
        
        String tabulka="uchazeci";//určuje se, do jaké tabulky se data uloží
        if (request.getParameter("stoletimaturity") != null) {//skryté tlačítko, formulář se uloží do průběžné tabulky a vytvoří se mu uživateslé jméno a heslo, ale ve finále kandidát na vymazání
            tabulka="uchazeci_spam";
        }
        if(true){//podmínka, aby byla vyplněná všechna povinná políčka
        try
        {
            Connection conn = null;
            String id=null;
            String sql="";
            Statement stmt;
            String url = "jdbc:mysql://localhost:3306/";
            String dbName ="mysql"; 
            String uname = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName,uname,pwd);
            stmt = conn.createStatement();
            
            sql = "SELECT * FROM ip_adresa where ip='"+ip+"'";
            ResultSet rs = stmt.executeQuery(sql);
            int size=0;                                                         //vyberu z tabulky stejnou ip adresu, pokud tam není, tak vytvořím nový řádek
            int count=0;
            int rsIP=0;
            while(rs.next()){
                count=rs.getInt("count");
                size++;
            }
            if (count==0) {
                sql = "INSERT INTO ip_adresa(ip, count) VALUES('"+ip+"','1')";
                rsIP = stmt.executeUpdate(sql);
            }
            else if(count>10){//změnit zpátky na 10, po odladění
                count++;                                                        //přičte se jednička za tuto ip adresu
                tabulka="uchazeci_ipspam";
                sql = "UPDATE ip_adresa SET count = '"+count+"' WHERE ip = '"+ip+"'";
                rsIP = stmt.executeUpdate(sql);
            }
            else{
                count++;                                                        //přičte se jednička za tuto ip adresu
                tabulka="uchazeci";
                sql = "UPDATE ip_adresa SET count = '"+count+"' WHERE ip = '"+ip+"'";
                rsIP = stmt.executeUpdate(sql);
            }
            
            sql = "INSERT INTO login(username, name, lastname, password, rights) VALUES('"+username+"','"+request.getParameter("jmeno")+"','"+request.getParameter("prijmeni")+"','"+password+"','4')";
            int rsLogin = stmt.executeUpdate(sql);
            sql = "INSERT INTO "+tabulka+"(username, studijniprogram, studijniobor, pohlavi, statniprislusnost, "
                    + "rodinnystav, tituly, narozeniden, narozenimesic, narozenirok, "
                    + "cisloobcanskehoprukazu, rodnecislo, cislopasu, narozenimisto, narozeniokres, "
                    + "adresaulice, adresacislodomu, adresacastobce, adresaobec, adresaokres, "
                    + "adresapsc, adresastat, adresatelefon, adresaposta, kontaktulice, "
                    + "kontaktcislodomu, kontakttelefon, kontaktcastobce, kontaktobec, kontaktokres, "
                    + "kontaktpcs, kontaktposta, kontaktstat, ssnazev, ssadresa, "
                    + "ssobor, ssjkov, sskkov, ssizo, ssrokmaturity, "
                    + "prijat) "
                    + "VALUES ('"+
                    username+"','"+request.getParameter("studijniprogram")+"','"+request.getParameter("studijniobor")+"','"+request.getParameter("pohlavi")+"','"+request.getParameter("statniprislusnost")+"','"+
                    request.getParameter("rodinnystav")+"','"+request.getParameter("tituly")+"','"+request.getParameter("narden")+"','"+request.getParameter("narmesic")+"','"+request.getParameter("narrok")+"','"+
                    request.getParameter("cisloOP")+"','"+request.getParameter("rodnecislo")+"','"+request.getParameter("cislopasu")+"','"+request.getParameter("narmisto")+"','"+request.getParameter("narokres")+"','"+
                    request.getParameter("ulice")+"','"+request.getParameter("cislodomu")+"','"+request.getParameter("castobce")+"','"+request.getParameter("obec")+"','"+request.getParameter("okres")+"','"+
                    request.getParameter("psc")+"','"+request.getParameter("stat")+"','"+request.getParameter("telefon")+"','"+request.getParameter("posta")+"','"+request.getParameter("kulice")+"','"+
                    request.getParameter("kcislodomu")+"','"+request.getParameter("ktelefon")+"','"+request.getParameter("kcastobce")+"','"+request.getParameter("kobec")+"','"+request.getParameter("kokres")+"','"+
                    request.getParameter("kpsc")+"','"+request.getParameter("kposta")+"','"+request.getParameter("kstat")+"','"+request.getParameter("ssnazev")+"','"+request.getParameter("ssadresa")+"','"+
                    request.getParameter("ssobor")+"','"+request.getParameter("jkov")+"','"+request.getParameter("kkov")+"','"+request.getParameter("izo")+"','"+request.getParameter("rokmaturity")+"','"+
                    "neprijat')";
            int rsUchazec = stmt.executeUpdate(sql);
            
            if (rsIP==1&rsLogin==1&rsUchazec==1) {
                session.setAttribute("registered", "success");
            }
            else {
                session.setAttribute("registered", "fail");
            }
            response.sendRedirect("proUchazece.jsp");
        }
        catch(ClassNotFoundException e)
        {
            response.getWriter().print(e);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Throwable t = ex.getCause();
            response.getWriter().print(t);
        }
        }
    }
}
