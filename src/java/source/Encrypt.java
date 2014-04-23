package source;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class Encrypt {

    private String byteToString(byte[] pole){
        String newSalt="";
        for (int j = 0; j < pole.length; j++) {
            newSalt+=pole[j];
        }
        return newSalt;
    }
    
    public String encrypt(String password, String username){
        for (int i = 0; i < 20; i++) {
            try {
                username=getMD5(username, password);
                password=getSHA256(password, username);
            } catch (NoSuchAlgorithmException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "encrypt", ex.getMessage(), ex);
            }
        }
        return password;
    }
    
    private String getSHA256(String password, String saltString) throws NoSuchAlgorithmException{
       byte[] salt=saltString.getBytes();
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       digest.reset();
       digest.update(salt);
        try {
            return byteToString(digest.digest(password.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "getSHA256", ex.getMessage(), ex);
        }
        return null;
    }
    
    private String getMD5(String password, String saltString) throws NoSuchAlgorithmException{
       byte[] salt=saltString.getBytes();
       MessageDigest digest = MessageDigest.getInstance("MD5");
       digest.reset();
       digest.update(salt);
        try {
            return byteToString(digest.digest(password.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException ex) {
            MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "getMD5", ex.getMessage(), ex);
        }
        return null;
    }
    
}
