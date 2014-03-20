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
    
    public String getValidatedId(){
        Mysql sql=new Mysql();
        String id=getId();
        boolean unique=sql.validateId(id);
        if (unique) {
            return id;
        }
        return getValidatedId();
    }
}


