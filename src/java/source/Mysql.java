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
        url = "jdbc:mysql://localhost:3306/";                                   //pro localhost
        //url = "jdbc:mysql://localhost/azathoth?autoReconnect=true";             //pro eatj
        
        dbName ="mysql";                                                        //pro localhost
        //dbName ="azathoth";                                                     //pro eatj
        
        uname = "root";
        pwd = "";
        conn=null;
        id=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName+"?useUnicode=true&characterEncoding=utf-8",uname,pwd);    //kvůli UTF-8 kódování při komunikaci s mysql databází
        }
        catch(ClassNotFoundException e)
        {
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
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
        String[] output=new String[6];
        String[] label=Label.getLabelRaw();
        try {
            String sql = "SELECT * FROM login where "+label[0]+"=? and "+label[3]+"=?";
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
                username=rs.getString(label[0]);
                name=rs.getString(label[1]);
                lastname=rs.getString(label[2]);
                rights=rs.getInt("rights");
                size++;
            }
            if(size==1){                                                        //podmínkou pro úspěšné přihlášení se je právě jedna shoda
                output[5]=username;
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
    public boolean insertNewApplicant(String tabulka, String[] input){
        boolean output=false;
        String[] label=Label.getLabelRaw();
        try {
            String sql = "INSERT INTO login("+label[0]+", "+label[1]+", "+label[2]+", "+label[3]+", rights) VALUES(?,?,?,?,?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,input[0]);
            ps.setString(2,input[1]);
            ps.setString(3,input[2]);
            ps.setString(4,input[3]);
            ps.setInt(5,4);
            int rsLogin = ps.executeUpdate(); 
            
            sql = "INSERT INTO "+tabulka+"("+ label[0]+", ";
            for (int i = 4; i < label.length; i++) {
                sql+=label[i]+", ";
            }
            sql+=") VALUES("
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?)";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,input[0]);
            for (int i = 2; i < 40; i++) {
                ps.setString(i,input[i+2]);
            }
            ps.setString(41,"nezaplacen registrační poplatek");
            ps.setString(42,"nezaplaceno");
            
            int rsUchazec = ps.executeUpdate(); 
            
            int rs=Math.min(rsLogin, rsUchazec);
            if (rs==1) {
                output=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    /**
     * Vrátí v poli polí všechny uchazeče o studium z dané tabulky
     * @param tabulka tabulka, do které se mají informace vložit, možné hodnoty: uchazeci, uchazeci_spam a uchazeci_ipspam
     * @return vrátí pole polí stringů, první udává řádek se všemi údaji, druhý udává údaj v konkrétním sloupečku, číslování pole je stejné jako u vkládání údajů o uchazeči do tabulky
     */
    public String[][] showApplicants(String tabulka){
        ArrayList<String[]> output = new ArrayList<String[]>();
        String[][] outputString = null;
        String[] label=Label.getLabelRaw();
        try {
            String sql = "SELECT * FROM "+tabulka+" where 1";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String[] temp=new String[45];                                   //navíc na konci políčko přijat
                temp[0]=rs.getString(label[0]);
                for (int i = 4; i < label.length; i++) {
                    temp[i]=rs.getString(label[i]);
                }
                output.add(temp);
            }
            outputString=new String[output.size()][45];
            for (int i = 0; i < output.size(); i++) {
                outputString[i]=output.get(i);                                  //převedení arraylistu pole stringů na pole polí stringů
            }
            
            for (int i = 0; i < outputString.length; i++) {
                sql = "SELECT * FROM login where "+label[0]+" = ?";
                ps = conn.prepareStatement(sql);                                //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                ps.setString(1,outputString[i][0]);
                ResultSet rsLogin = ps.executeQuery();                          //pro parametrizovaný dotaz
                while(rsLogin.next()){
                    outputString[i][1]=rsLogin.getString(label[1]);
                    outputString[i][2]=rsLogin.getString(label[2]);
                    outputString[i][3]=rsLogin.getString(label[3]);
                    //outputString[i][3]=outputString[i][3].substring(0, 5);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outputString;
    }
    
    private String createUpdateStatement(String tabulka){
        Label lab=new Label();
        String[] label=lab.getLabelRaw();
        String sql = "UPDATE "+tabulka+" SET ";
        for (int i = 4; i < label.length; i++) {                                //protože jméno a příjmení nepatří do tabulky
            sql+=label[i]+" = ?";
            if(!(i==label.length-1)){                                      //pro poslední iteraci cyklu nebude na konci čárka
                sql+= ", ";
            }
        }
        sql+=" WHERE "+label[0]+"= ?";
        return sql;
    }
    
    public boolean updateApplicants(String tabulka, String[][] uchazec){
        int[] rs=new int[uchazec.length*2];
        String[] label=Label.getLabelRaw();
        for (int i = 0; i < uchazec.length; i++) {                              //update se provede pro každého uchazeče
            try {
            String sql=createUpdateStatement(tabulka);
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
                for (int j = 1; j < 42; j++) {
                    ps.setString(j,uchazec[i][j+3]);
                }
            ps.setString(42,uchazec[i][0]);
            
            rs[2*i] = ps.executeUpdate(); 
            
            sql = "UPDATE login SET "+label[1]+" = ?, "+label[2]+" = ?, "+label[3]+" = ? WHERE "+label[0]+" = ?";
            ps=conn.prepareStatement(sql);                                      //parametrized statement pro dotaz s otazníky a pozdějším dosazením
            ps.setString(1,uchazec[i][1]);
            ps.setString(2,uchazec[i][2]);
            ps.setString(3,uchazec[i][3]);
            ps.setString(4,uchazec[i][0]);
            
            rs[2*i+1] = ps.executeUpdate(); 
            
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
        String[] label=Label.getLabelRaw();
        int rs = 0;
        try {
            String sql = "UPDATE login SET "+label[3]+"=? where "+label[0]+"=?";
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
}
