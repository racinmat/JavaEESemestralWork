/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Label;
import enums.Rights;
import enums.SQLTables;
import static enums.SQLTables.*;
import enums.ApplicationState;
import java.io.IOException;
import servlet.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azathoth
 */
public class Mysql {
    private final Connection conn;
    private String id;
    private PreparedStatement ps;
    private final String url;
    private final String dbName; 
    private final String uname;
    private final String pwd;

    /**
     * Zadefinuje proměnné a pokusí se vytvořit spojení s databází.
     */
    public Mysql() throws ClassNotFoundException, SQLException {                                                            //vyhazuje výjimky, které mohou být chyceny servletem, který uživatele přesměruje na statickou stránku a oznámí mu problém
        url = "jdbc:mysql://localhost:3306/";                                   //pro localhost
        //url = "jdbc:mysql://localhost/azathoth?autoReconnect=true";             //pro eatj
        
        dbName ="mysql";                                                        //pro localhost
        //dbName ="azathoth";                                                     //pro eatj
        
        uname = "root";
        pwd = "";
        id=null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url+dbName+"?useUnicode=true&characterEncoding=utf-8",uname,pwd);    //kvůli UTF-8 kódování při komunikaci s mysql databází
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
    public LoggedUser login(String username, String password) throws SQLException{
        LoggedUser user=null;
        String sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+Label.userName.getNameRaw()+"=? and "+Label.password.getNameRaw()+"=?";
        ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();                                   //pro parametrizovaný dotaz
        int size=0;
        int rights=Integer.MAX_VALUE;                                       //číselná práva jsou od nuly výš, max value tedy nepřidělí platná práva
        String name="";
        String lastname="";
        while(rs.next()){
            username=rs.getString(Label.userName.getNameRaw());
            name=rs.getString(Label.name.getNameRaw());
            lastname=rs.getString(Label.lastname.getNameRaw());
            rights=rs.getInt("rights");
            size++;
        }
        if(size==1){                                                        //podmínkou pro úspěšné přihlášení se je právě jedna shoda
            Rights rightsObject=Rights.getRightsFromInt(rights);
            user=new LoggedUser(name, lastname, rightsObject, username, "success");
        }
        else{
            user=new LoggedUser("", "", Rights.notLogged, "", "");
        }
        return user;
    }
    
    /**
     * Vloží do tabulky ip adres novou ip adresu s počtem registrací 1
     * @param IP IP adresa, která má být vložena
     * @return vrátí true, pokud bylo vložení úspěšné, jinak vrátí false
     */
    public boolean insertNewIP(String IP){
        boolean output=false;
        try {
            String sql = "INSERT INTO ip_adresa(ip, count) VALUES(?,?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,IP);
            ps.setInt(2,1);
            int rs = ps.executeUpdate();                                            //pro parametrizovaný dotaz
            if (rs==1) {
                output=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
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
     * @return vrátí true, pokud bylo vložení úspěšné, jinak vrátí false
     */
    public boolean increaseIPcount(String IP){
        boolean output=false;
        try {
            String sql = "UPDATE ip_adresa SET count = count+1 WHERE ip = ?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,IP);
            int rs = ps.executeUpdate();                                        //pro parametrizovaný dotaz
            if (rs==1) {
                output=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    /**
     * Vloží do tabulky loginu i do tabulky uchazečů záznam o novém uchazeči o studium
     * @param tabulka tabulka, do které se mají informace vložit, možné hodnoty: uchazeci, uchazeci_spam a uchazeci_ipspam
     * @param input pole, kde jsou všechny údaje o studentovi
     * @return vrátí true, pokud bylo vložení úspěšné, jinak vrátí false
     */
    public boolean insertNewApplicant(SQLTables tabulka, HashMap<Label, String> input){
        boolean output=false;
        input.put(Label.applicationstate, ApplicationState.getDefaultBoolean().getName());
        input.put(Label.tuition, "nezaplaceno");
        boolean output1=insertNewUserToLogin(input,4);
        boolean output2=insertApplicant(tabulka, input);
        output=output1&&output2;
        return output;
    }
    
    /**
     * Vrátí v poli polí všechny uchazeče o studium z dané tabulky
     * @param tabulka tabulka, do které se mají informace vložit, možné hodnoty: uchazeci, uchazeci_spam a uchazeci_ipspam
     * @return vrátí pole polí stringů, první udává řádek se všemi údaji, druhý udává údaj v konkrétním sloupečku, číslování pole je stejné jako u vkládání údajů o uchazeči do tabulky
     */
    public ArrayList<HashMap<Label,String>> showPeople(SQLTables tabulka){                        //protože název tabulky nejde parametrizovat, je to ošetřeno přes výčtový typ
        ArrayList<HashMap<Label,String>> output = new ArrayList<>();
        Label debug = null;
        try {
            String sql = "SELECT * FROM "+tabulka.getTable()+" where 1";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            System.out.println("Sestavené SQL pro tabulku "+tabulka+" je: "+sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HashMap<Label,String> temp=new HashMap<>();                         
                for (Label label : Label.values()) {
                    if (label.isInTable(tabulka)) {
                        temp.put(label, rs.getString(label.getNameRaw()));
                    }
                }
                output.add(temp);
            }
            for (int i = 0; i < output.size(); i++) {
                sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+SQLTables.login.getPrimaryKey().getNameRaw()+" = ?";
                ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                ps.setString(1,output.get(i).get(SQLTables.login.getPrimaryKey()));
                System.out.println("Sestavené SQL pro tabulku "+SQLTables.login.getTable()+" je: "+sql);
                ResultSet rsLogin = ps.executeQuery();                          //pro parametrizovaný dotaz
                while(rsLogin.next()){
                    for (Label label : Label.values()) {
                        if (label.isInTable(SQLTables.login)) {
                            debug=label;
                            String temp=rsLogin.getString(label.getNameRaw());
                            output.get(i).put(label, temp);
                        }
                    }
                }
            }
            
        } catch (SQLException ex) {
            if (debug!=null) {
                System.out.println(debug.getNameRaw());
            }
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public ArrayList<HashMap<Label,String>> showPeople(SQLTables tabulka, String criterium, Label criteriumColumn, boolean negate){
        ArrayList<HashMap<Label,String>> output = new ArrayList<>();
        String sql;
        Label debug = null;
        try {
            if (negate) {
                sql = "SELECT * FROM "+tabulka.getTable()+" where "+criteriumColumn.getNameRaw()+"<>?";
            } else {
                sql = "SELECT * FROM "+tabulka.getTable()+" where "+criteriumColumn.getNameRaw()+"=?";
            }
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,criterium);
            System.out.println("Sestavené SQL pro tabulku "+tabulka+" je: "+sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HashMap<Label,String> temp=new HashMap<>();                         
                for (Label label : Label.values()) {
                    if (label.isInTable(tabulka)) {
                        temp.put(label, rs.getString(label.getNameRaw()));
                    }
                }
                output.add(temp);
            }
            for (int i = 0; i < output.size(); i++) {
                sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+SQLTables.login.getPrimaryKey().getNameRaw()+" = ?";
                ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                ps.setString(1,output.get(i).get(SQLTables.login.getPrimaryKey()));
                System.out.println("Sestavené SQL pro tabulku "+SQLTables.login.getTable()+" je: "+sql);
                ResultSet rsLogin = ps.executeQuery();                          //pro parametrizovaný dotaz
                while(rsLogin.next()){
                    for (Label label : Label.values()) {
                        if (label.isInTable(SQLTables.login)) {
                            debug=label;
                            String temp=rsLogin.getString(label.getNameRaw());
                            output.get(i).put(label, temp);
                        }
                    }
                }
            }
            
        } catch (SQLException ex) {
            if (debug!=null) {
                System.out.println(debug.getNameRaw());
            }
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    /**
     * Nyní vytváří updateStatement přesně podle toho, co je u usera změněno, není zapotřebí kopírovat staré hodnoty
     * @param user
     * @param tabulka
     * @return 
     */
    private String createUpdateStatement(HashMap<Label,String> user, SQLTables tabulka){
        String sql = "UPDATE "+tabulka.getTable()+" SET ";
        for (Label label : user.keySet()) {
            if (label.isInTable(tabulka)&&!label.isPrimaryKey()) {
                sql+=label.getNameRaw()+" = ?, ";
            }
        }
        sql=sql.substring(0, sql.length()-2);                            //odtrhne poslední čárku a mezeru za ní
        sql+=" WHERE "+tabulka.getPrimaryKey().getNameRaw()+"= ?";
        System.out.println("Sestavené SQL pro tabulku "+tabulka+" je: "+sql);
        return sql;
    }
    
    public boolean updateApplicants(SQLTables tabulka, ArrayList<HashMap<Label, String>> uchazec){
        int[] rs=new int[uchazec.size()*2];
        for (int i = 0; i < uchazec.size(); i++) {                              //update se provede pro každého uchazeče
            try {
                String sql=createUpdateStatement(uchazec.get(i), tabulka);
                ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                int count=0;
                for (Label label : uchazec.get(i).keySet()) {
                    if (!label.isPrimaryKey()&&label.isInTable(tabulka)) {
                        count++;                                                        //číslování v preparedStatementu začíná od jedné
                        ps.setString(count,uchazec.get(i).get(label));
                    }
                }
                count++;
                ps.setString(count,uchazec.get(i).get(tabulka.getPrimaryKey()));

                rs[2*i] = ps.executeUpdate(); 
                System.out.println(sql+" was successfully executed.");
                sql=createUpdateStatement(uchazec.get(i), SQLTables.login);
                ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                count=0;
                for (Label label : uchazec.get(i).keySet()) {
                    if (!label.isPrimaryKey()&&label.isInTable(SQLTables.login)) {
                        count++;                                                        //číslování v preparedStatementu začíná od jedné
                        ps.setString(count,uchazec.get(i).get(label));
                        System.out.println("ps.setString("+count+","+uchazec.get(i).get(label)+");");
                    }
                }
                count++;
                ps.setString(count,uchazec.get(i).get(tabulka.getPrimaryKey()));

                rs[2*i+1] = ps.executeUpdate(); 
                System.out.println(sql+" was successfully executed.");
            } catch (SQLException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean output=true;
        for (int i = 0; i < rs.length; i++) {
            if (rs[i]!=1) {
                output=false;
            }
        }
        return output;
    }
    
    public boolean updatePassword(String username, String password){
        int rs = 0;
        try {
            String sql = "UPDATE "+SQLTables.login.getTable()+" SET "+Label.password.getNameRaw()+"=? where "+SQLTables.login.getPrimaryKey().getNameRaw()+"=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,password);
            ps.setString(2,username);
            rs = ps.executeUpdate();                                   //pro parametrizovaný dotaz
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean output=true;
        if (rs!=1) {
            output=false;
        }
        return output;
    }
    
    /**
     * 
     * @param id
     * @return returns true in case of unique id
     */
    public boolean validateId(String id){
        try
        {
            String sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+SQLTables.login.getPrimaryKey().getNameRaw()+"=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();                                   //pro parametrizovaný dotaz
            int size=0;
            while(rs.next()){
                size++;
            }
            if (size>0) {
                return false;
            }
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Throwable t = ex.getCause();
            
        }
        return false;
    }
    
    /**
     * Returns all data about chosen applicant from chosen table, except of name and last name.
     * @param username username of user whose data will be returned
     * @param tabulka table where is chosen user
     * @return returns all data in chosen table, table is indexed by label, 
     */
    public HashMap<Label, String> showApplicant(String username, SQLTables tabulka){
        HashMap<Label,String> output=new HashMap<>();                         
        Label debug = null;
        try {
            String sql = "SELECT * FROM "+tabulka.getTable()+" where "+tabulka.getPrimaryKey().getNameRaw()+" = ?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                for (Label label : Label.values()) {
                    if (label.isInTable(tabulka)) {
                        output.put(label, rs.getString(label.getNameRaw()));
                    }
                }
            }
            sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+SQLTables.login.getPrimaryKey().getNameRaw()+" = ?";
            ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,output.get(SQLTables.login.getPrimaryKey()));
            ResultSet rsLogin = ps.executeQuery();                          //pro parametrizovaný dotaz
            while(rsLogin.next()){
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTables.login)) {
                        debug=label;
                        output.put(label, rs.getString(label.getNameRaw()));
                    }
                }
            }
                
        } catch (SQLException ex) {
            System.out.println(debug.getNameRaw());
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public boolean updateApplicant(HashMap<Label,String> uchazec, SQLTables tabulka){
        int rs=0;
        try {
            String sql=createUpdateStatement(uchazec, tabulka);
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            int count=0;
            for (Label label : uchazec.keySet()) {
                if (!label.isPrimaryKey()&&label.isInTable(tabulka)) {
                    count++;
                    ps.setString(count,uchazec.get(label));
                }
            }
            count++;
            ps.setString(count,uchazec.get(tabulka.getPrimaryKey()));
            
            rs = ps.executeUpdate(); 
            } catch (SQLException ex) {
                Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        boolean output=true;
        if (rs!=1) {
            output=false;
        }
        return output;
    }
    
    /**
     * Returns name of table where is applicant with username from input
     * @param username
     * @return returns uchazeci, uchazeci_spam or uchazeci_ipspam, depending on where username from id is stored
     */
    public SQLTables findTableWithApplicant(String username) throws IllegalArgumentException{
        SQLTables output=null;
        SQLTables[] tabulky={applicants, applicants_spam, applicants_ipspam};
        try {
            for (int i = 0; i < tabulky.length; i++) {
                String sql = "SELECT * FROM "+tabulky[i].getTable()+" where "+tabulky[i].getPrimaryKey().getNameRaw()+" = ?";
                ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    output=tabulky[i];
                    return output;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IllegalArgumentException("User "+username+" was not found.");
    }
    
    public HashMap<Label,String> showLoginInfoOfUser(String username){
        HashMap<Label,String> output = new HashMap<>();
       try {
            String sql = "SELECT * FROM "+SQLTables.login.getTable()+" where "+SQLTables.login.getPrimaryKey().getNameRaw()+" = ?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                for (Label label : Label.values()) {
                    if (label.isInTable(SQLTables.login)) {
                        output.put(label, rs.getString(label.getNameRaw()));
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public boolean transferApplicant(SQLTables tableFrom, String applicantUsername, SQLTables tableTo){
        boolean output=false;
        HashMap<Label,String> input=showApplicant(applicantUsername, tableFrom);
        boolean temp1=insertApplicant(tableTo, input);
        boolean temp2=deleteRow(applicantUsername, tableFrom);
        output=temp1&&temp2;
        return output;
        
    }
    
    public boolean insertApplicant(SQLTables tabulka, HashMap<Label,String> input){
        boolean output=false;
        try {
            String sql = createInsertStatement(tabulka);
            output=executeInsertStatement(sql, input, tabulka);
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public boolean deleteRow(String username, SQLTables table){
        int rs = 0;
        try {
            String sql = "DELETE FROM "+table.getTable()+" where "+table.getPrimaryKey().getNameRaw()+"=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,username);
            rs = ps.executeUpdate();                                   //pro parametrizovaný dotaz
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean output=true;
        if (rs!=1) {
            output=false;
        }
        return output;
    }
    
    public boolean insertNewUserToLogin(HashMap<Label, String> input, int rights){
        boolean output=false;
        try {
            input.put(Label.rights, Integer.toString(rights));
            String sql = createInsertStatement(SQLTables.login);
            output = executeInsertStatement(sql, input, SQLTables.login);
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public boolean insertNewPedagog(HashMap<Label, String> input){
        boolean output=false;
        boolean output1=insertNewUserToLogin(input,2);
        boolean output2;
        try {
            String sql = createInsertStatement(SQLTables.teachers);
            output2 = executeInsertStatement(sql, input, SQLTables.teachers);
            output=output1&&output2;
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public boolean insertNewAdministrativa(HashMap<Label, String> input){
        boolean output=false;
        boolean output1=insertNewUserToLogin(input,1);
        boolean output2;
        try {
            String sql = createInsertStatement(SQLTables.administrativa);
            output2 = executeInsertStatement(sql, input, SQLTables.administrativa);
            output=output1&&output2;
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public boolean insertNewStudent(HashMap<Label, String> input){
        boolean output=false;
        boolean output1=updateRights(input.get(SQLTables.login.getPrimaryKey()),3);
        boolean output2;
        try {
            String sql = createInsertStatement(SQLTables.students);
            output2 = executeInsertStatement(sql, input, SQLTables.students);
            output=output1&&output2;
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public boolean updateRights(String username, int rights){
        int rs = 0;
        try {
            String sql = "UPDATE "+SQLTables.login.getTable()+" SET "+Label.rights.getNameRaw()+"=? where "+SQLTables.login.getPrimaryKey().getNameRaw()+"=?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setInt(1,rights);
            ps.setString(2,username);
            rs = ps.executeUpdate();                                   //pro parametrizovaný dotaz
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean output=true;
        if (rs!=1) {
            output=false;
        }
        return output;
    }
    
    private String createInsertStatement(SQLTables tabulka){
        String sql = "INSERT INTO "+tabulka.getTable()+"(";
        for (Label label : Label.values()) {
            if (label.isInTable(tabulka)) {
                sql+=label.getNameRaw();
                sql+=", ";
            }
        }
        sql=sql.substring(0, sql.length()-2);                               //odtrhne poslední čárku a mezeru za ní
        sql+=") VALUES(";
        for (Label label : Label.values()) {
            if (label.isInTable(tabulka)) {
                sql+="?,";
            }
        }
        sql=sql.substring(0, sql.length()-1);                               //odtrhne poslední čárku
        sql+=")";
        System.out.println("Sestavené SQL pro tabulku "+tabulka+" je: "+sql);
        return sql;
    }
    
    private boolean executeInsertStatement(String sql, HashMap<Label, String> input, SQLTables table) throws SQLException{
        boolean output;
        ps=conn.prepareStatement(sql);                                          //parametrized statement pro dotaz s otazníky a pozdějším dosazením
        int count=0;
        for (Label label : Label.values()) {
            if (label.isInTable(table)) {
                count++;
                if (input.get(label)==null) {
                    throw new NullPointerException("Chyba při vkládání do tabulky "+table+", sql dotaz je "+sql+", input má null pod labelem "+label);
                }
                ps.setString(count,input.get(label));
            }
        }
        int rs = ps.executeUpdate(); 
        if (rs==1) {
            output=true;
        } else {
            output=false;
        }
        return output;
    }
    
}
