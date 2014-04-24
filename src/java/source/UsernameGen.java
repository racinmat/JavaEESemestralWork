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
    private int length = 1;

    /**
     * 
     * @param length 
     */
    public UsernameGen(int length) {
        if (length>0) {                     //ochrana proti záporným hodnotám
            this.length=length;
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getId() {
        return randomAlphaNumeric();
    }
    
    /**
     * 
     * @return 
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
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
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


