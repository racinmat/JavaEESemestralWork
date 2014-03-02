package source;




import source.Login;
import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */

public class UsernameGen {

    private final char[] ALPHA_NUMERIC_ARRAY = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    private int length = 1;

    public UsernameGen(int length) {
        if (length>0) {                     //ochrana proti záporným hodnotám
            this.length=length;
        }
    }
    
    public String getId() {
        return randomAlphaNumeric(length);
    }
    
    private String randomAlphaNumeric(int count) {
        String alphaNumeric="";
        
        for (int i = 0; i < length; i++) {
            int rand=(int) (ALPHA_NUMERIC_ARRAY.length*Math.random());
            alphaNumeric+=ALPHA_NUMERIC_ARRAY[rand];
        }
        return alphaNumeric;
    }
    
    public boolean validateId(String id){
        try
        {
            Connection conn = null;
            Statement stmt;
            String url = "jdbc:mysql://localhost:3306/";
            String dbName ="mysql"; 
            String uname = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName,uname,pwd);
            String sql = "SELECT * FROM login where username='"+id+"'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            int size=0;
            int rights=Integer.MAX_VALUE;                                       //číselná práva jsou od nuly výš, max value tedy nepřidělí platná práva
            int rightsTemp=Integer.MAX_VALUE;
            String name="";
            String lastname="";
            String nameTemp="";
            String lastnameTemp="";
            while(rs.next()){
                size++;
            }
            if (size>0) {
                return false;
            }
            return true;
        }
        catch(ClassNotFoundException e)
        {
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            Throwable t = ex.getCause();
            
        }
        return false;
    }
    
    public String getValidatedId(){
        String id=getId();
        boolean unique=validateId(id);
        if (unique) {
            return id;
        }
        return getValidatedId();
    }
}


