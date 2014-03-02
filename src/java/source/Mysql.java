/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azathoth
 */
public class Mysql {
    private Connection conn = null;
    private String id=null;
    private PreparedStatement ps;
    private String url;
    private String dbName; 
    private String uname;
    private String pwd;

    /**
     * Zadefinuje proměnné a pokusí se vytvořit spojení s databází.
     */
    public Mysql() {
        url = "jdbc:mysql://localhost:3306/";
        dbName ="mysql"; 
        uname = "root";
        pwd = "";
        conn=null;
        id=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName,uname,pwd);
        }
        catch(ClassNotFoundException e)
        {
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Throwable t = ex.getCause();
        }
    }
          
    /**
     * Přihlásí uživatele
     * name, tedy jméno
     * lastname, tedy příjmení
     * logged, String, který nabývá hodnot buď success nebo fail
     * 
     * @param username uživateloho přihlašovací jméno
     * @param password uživatelův hash hesla
     * @return vrátí pole Stringů, kde budou uložené infomrace o uživateli, a to popořadě:
     * rights, což je integer od 0 výše
     * rightsString, což je název přiřazený každé hodnotě rights
     */
    public String[] login(String username, String password){
        String[] output=new String[5];
        try {
            String sql = "SELECT * FROM login where username=? and password=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();                                   //pro parametrizovaný dotaz
            int size=0;
            int rights=Integer.MAX_VALUE;                                       //číselná práva jsou od nuly výš, max value tedy nepřidělí platná práva
            int rightsTemp=Integer.MAX_VALUE;
            String name="";
            String lastname="";
            String nameTemp="";
            String lastnameTemp="";
            while(rs.next()){
                nameTemp=rs.getString("name");
                lastnameTemp=rs.getString("lastname");
                rightsTemp=rs.getInt("rights");
                size++;
            }
            if(size==1){                                                        //podmínkou pro úspěšné přihlášení se je právě jedna shoda
                name=nameTemp;
                lastname=lastnameTemp;
                rights=rightsTemp;
                output[3]=name;
                output[4]=lastname;
                output[0]="success";
                output[1]=Integer.toString(rights);
                switch(rights){
                    case 0:
                        output[2]="hlavní admin";
                        break;                    //main admin
                    case 1:
                        output[2]="administrativa";
                        break;                    //administrativa
                    case 2:
                        output[2]="pedagog";
                        break;                    //pedagog
                    case 3:
                        output[2]="student";
                        break;                    //student
                    case 4:
                        output[2]="uchazeč";
                        break;                    //uchazeč
                }
            }
            else{
                output[0]="fail";                         //je zapotřebí pro výpis chybové hlášky ve footeru
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    /**
     * Vloží do tabulky ip adres novou ip adresu s počtem registrací 1
     * @param IP IP adresa, která má být vložena
     * @return vrátí 1, pokud bylo vložení úspěšné, jinak vrátí 0
     */
    public int insertNewIP(String IP){
        int rs=0;
        try {
            String sql = "INSERT INTO ip_adresa(ip, count) VALUES(?,?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,IP);
            ps.setInt(2,1);
            rs = ps.executeUpdate();                                            //pro parametrizovaný dotaz
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * Najde IP adresu a vrátí počet registrací
     * @param IP IP adresa, která má být vyhledána v tabulce IP adres
     * @return počet registrací z dané IP adresy
     */
    public int findIP(String IP){
        int count=0;
        try {
            String sql = "SELECT * FROM ip_adresa where ip=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,IP);
            ResultSet rs = ps.executeQuery();                                   //pro parametrizovaný dotaz
            while(rs.next()){
                count=rs.getInt("count");                                       
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    /**
     * Zvýší počet registrací z dané IP adresy o 1
     * @param IP IP adresa, u které má být zvýšen počet registrací
     * @return vrátí 1, pokud bylo vložení úspěšné, jinak vrátí 0
     */
    public int increaseIPcount(String IP){
        int rs=0;
        try {
            String sql = "UPDATE ip_adresa SET count = count+1 WHERE ip = ?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,IP);
            rs = ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * Vloží do tabulky loginu i do tabulky uchazečů záznam o novém uchazeči o studium
     * @param tabulka tabulka, do které se mají informace vložit, možné hodnoty: uchazeci, uchazeci_spam a uchazeci_ipspam
     * @param input pole, kde jsou všechny údaje o studentovi
     * @return vrátí 1, pokud bylo vložení úspěšné, jinak vrátí 0
     */
    public int insertNewApplicant(String tabulka, String[] input){
        int rs=0;
        try {
            //String sql = "INSERT INTO login(username, name, lastname, password, rights) VALUES('"+input[0]+"','"+input[1]+"','"+input[2]+"','"+input[3]+"','4')";
            //int rsLogin = stmt.executeUpdate(sql);
            String sql = "INSERT INTO login(username, name, lastname, password, rights) VALUES(?,?,?,?,?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,input[0]);
            ps.setString(2,input[1]);
            ps.setString(3,input[2]);
            ps.setString(4,input[3]);
            ps.setInt(5,4);
            int rsLogin = ps.executeUpdate(); 
            /*sql = "INSERT INTO "+tabulka+"(username, studijniprogram, studijniobor, pohlavi, statniprislusnost, "
                    + "rodinnystav, tituly, narozeniden, narozenimesic, narozenirok, "
                    + "cisloobcanskehoprukazu, rodnecislo, cislopasu, narozenimisto, narozeniokres, "
                    + "adresaulice, adresacislodomu, adresacastobce, adresaobec, adresaokres, "
                    + "adresapsc, adresastat, adresatelefon, adresaposta, kontaktulice, "
                    + "kontaktcislodomu, kontakttelefon, kontaktcastobce, kontaktobec, kontaktokres, "
                    + "kontaktpcs, kontaktposta, kontaktstat, ssnazev, ssadresa, "
                    + "ssobor, ssjkov, sskkov, ssizo, ssrokmaturity, "
                    + "prijat) "
                    + "VALUES ('"+
                    input[0]+"','"+input[4]+"','"+input[5]+"','"+input[6]+"','"+input[7]+"','"+
                    input[8]+"','"+input[9]+"','"+input[10]+"','"+input[11]+"','"+input[12]+"','"+
                    input[13]+"','"+input[14]+"','"+input[15]+"','"+input[16]+"','"+input[17]+"','"+
                    input[18]+"','"+input[19]+"','"+input[20]+"','"+input[21]+"','"+input[22]+"','"+
                    input[23]+"','"+input[24]+"','"+input[25]+"','"+input[26]+"','"+input[27]+"','"+
                    input[28]+"','"+input[29]+"','"+input[30]+"','"+input[31]+"','"+input[32]+"','"+
                    input[33]+"','"+input[34]+"','"+input[35]+"','"+input[36]+"','"+input[37]+"','"+
                    input[38]+"','"+input[39]+"','"+input[40]+"','"+input[41]+"','"+input[42]+"','"+
                    "neprijat')";
            int rsUchazec = stmt.executeUpdate(sql);*/
            sql = "INSERT INTO "+tabulka+"(username, studijniprogram, studijniobor, pohlavi, statniprislusnost, "
                    + "rodinnystav, tituly, narozeniden, narozenimesic, narozenirok, "
                    + "cisloobcanskehoprukazu, rodnecislo, cislopasu, narozenimisto, narozeniokres, "
                    + "adresaulice, adresacislodomu, adresacastobce, adresaobec, adresaokres, "
                    + "adresapsc, adresastat, adresatelefon, adresaposta, kontaktulice, "
                    + "kontaktcislodomu, kontakttelefon, kontaktcastobce, kontaktobec, kontaktokres, "
                    + "kontaktpcs, kontaktposta, kontaktstat, ssnazev, ssadresa, "
                    + "ssobor, ssjkov, sskkov, ssizo, ssrokmaturity, "
                    + "prijat) "
                    + "VALUES ("
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,input[0]);
            ps.setString(2,input[4]);
            ps.setString(3,input[5]);
            ps.setString(4,input[6]);
            ps.setString(5,input[7]);
            ps.setString(6,input[8]);
            ps.setString(7,input[9]);
            ps.setString(8,input[10]);
            ps.setString(9,input[11]);
            ps.setString(10,input[12]);
            ps.setString(11,input[13]);
            ps.setString(12,input[14]);
            ps.setString(13,input[15]);
            ps.setString(14,input[16]);
            ps.setString(15,input[17]);
            ps.setString(16,input[18]);
            ps.setString(17,input[19]);
            ps.setString(18,input[20]);
            ps.setString(19,input[21]);
            ps.setString(20,input[22]);
            ps.setString(21,input[23]);
            ps.setString(22,input[24]);
            ps.setString(23,input[25]);
            ps.setString(24,input[26]);
            ps.setString(25,input[27]);
            ps.setString(26,input[28]);
            ps.setString(27,input[29]);
            ps.setString(28,input[30]);
            ps.setString(29,input[31]);
            ps.setString(30,input[32]);
            ps.setString(31,input[33]);
            ps.setString(32,input[34]);
            ps.setString(33,input[35]);
            ps.setString(34,input[36]);
            ps.setString(35,input[37]);
            ps.setString(36,input[38]);
            ps.setString(37,input[39]);
            ps.setString(38,input[40]);
            ps.setString(39,input[41]);
            ps.setString(40,input[42]);
            ps.setString(41,"neprijat");
            int rsUchazec = ps.executeUpdate(); 
            
            rs=Math.min(rsLogin, rsUchazec);
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * Vrátí v poli polí všechny uchazeče o studium z dané tabulky
     * @param tabulka tabulka, do které se mají informace vložit, možné hodnoty: uchazeci, uchazeci_spam a uchazeci_ipspam
     * @return vrátí pole polí stringů, první udává řádek se všemi údaji, druhý udává údaj v konkrétním sloupečku, číslování pole je stejné jako u vkládání údajů o uchazeči do tabulky
     */
    public ArrayList<String[]> showApplicants(String tabulka){
        ArrayList<String[]> output = new ArrayList<String[]>();
        try {
            String sql = "SELECT * FROM "+tabulka+" where 1";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String[] temp=new String[44];                                   //navíc na konci políčko přijat
                temp[0]=rs.getString("username");
                temp[4]=rs.getString("studijniprogram");
                temp[5]=rs.getString("studijniobor");
                temp[6]=rs.getString("pohlavi");
                temp[7]=rs.getString("statniprislusnost");
                temp[8]=rs.getString("rodinnystav");
                temp[9]=rs.getString("tituly");
                temp[10]=rs.getString("narozeniden");
                temp[11]=rs.getString("narozenimesic");
                temp[12]=rs.getString("narozenirok");
                temp[13]=rs.getString("cisloobcanskehoprukazu");
                temp[14]=rs.getString("rodnecislo");
                temp[15]=rs.getString("cislopasu");
                temp[16]=rs.getString("narozenimisto");
                temp[17]=rs.getString("narozeniokres");
                temp[18]=rs.getString("adresaulice");
                temp[19]=rs.getString("adresacislodomu");
                temp[20]=rs.getString("adresacastobce");
                temp[21]=rs.getString("adresaobec");
                temp[22]=rs.getString("adresaokres");
                temp[23]=rs.getString("adresapsc");
                temp[24]=rs.getString("adresastat");
                temp[25]=rs.getString("adresatelefon");
                temp[26]=rs.getString("adresaposta");
                temp[27]=rs.getString("kontaktulice");
                temp[28]=rs.getString("kontaktcislodomu");
                temp[29]=rs.getString("kontakttelefon");
                temp[30]=rs.getString("kontaktcastobce");
                temp[31]=rs.getString("kontaktobec");
                temp[32]=rs.getString("kontaktokres");
                temp[33]=rs.getString("kontaktpcs");
                temp[34]=rs.getString("kontaktposta");
                temp[35]=rs.getString("kontaktstat");
                temp[36]=rs.getString("ssnazev");
                temp[37]=rs.getString("ssadresa");
                temp[38]=rs.getString("ssobor");
                temp[39]=rs.getString("ssjkov");
                temp[40]=rs.getString("sskkov");
                temp[41]=rs.getString("ssizo");
                temp[42]=rs.getString("ssrokmaturity");
                temp[43]=rs.getString("prijat");
                output.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
