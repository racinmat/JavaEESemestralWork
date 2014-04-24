package source;

import java.sql.SQLException;
import java.util.Random;


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
    private final int length;

    /**
     * 
     * @param length determines how long will be generated string
     */
    public UsernameGen(int length) {
        if (length>0) {                     //ochrana proti záporným hodnotám
            this.length=length;
        } else {
            this.length=1;
        }
    }
    
    /**
     * 
     * @return random alphanumeric string
     */
    public String getId() {
        return randomAlphaNumeric();
    }
    
    /**
     * 
     * @return random alphanumeric string
     */
    private String randomAlphaNumeric() {
        String alphaNumeric="";
        Random random=new Random();
        for (int i = 0; i < length; i++) {
            int rand=random.nextInt(ALPHA_NUMERIC_ARRAY.length-1);             //-1 je proto, aby, v případě vygenerování nejvyššího čísla, nenastal arrayindexoutofboundexception
            alphaNumeric+=ALPHA_NUMERIC_ARRAY[rand];
        }
        return alphaNumeric;
    }
    
    /**
     * 
     * @return random alphanumeric string, which is unique in sql table, used for generating primary keys
     * @throws ClassNotFoundException when establishing a connection fails
     * @throws SQLException when something goes wrong with database
     */
    public String getValidatedId() throws ClassNotFoundException, SQLException{
        Mysql sql=new Mysql();
        String id=getId();
        boolean unique=sql.validateId(id);
        if (unique) {
            return id;
        }
        return getValidatedId();
    }
}


