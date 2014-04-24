package source;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

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

    /**
     * Converts array of bytes to string.
     * @param array wchich will be turned into String
     * @return String representation of provided array
     */
    private String byteToString(byte[] array){
        String newSalt="";
        for (int j = 0; j < array.length; j++) {
            newSalt+=array[j];
        }
        return newSalt;
    }
    
    /**
     * Encrypts first String with seconf String as cryptographic salt.
     * @param password string to be encrypted
     * @param username cryptographic salt
     * @return hash, where is encrypted first String from param
     */
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
    
    /**
     * Returns SHA256 hash of first provided String with usage of cryptographic salt provided in secons String.
     * @param password String you want to hash
     * @param saltString salt you want to use
     * @return SHA256 hash
     * @throws NoSuchAlgorithmException when it fails to get instance of SHA-256 algorithm
     */
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
    
    /**
     * Returns MD5 hash of first provided String with usage of cryptographic salt provided in secons String.
     * @param password String you want to hash
     * @param saltString salt you want to use
     * @return MD5 hash
     * @throws NoSuchAlgorithmException when it fails to get instance of MD5 algorithm
     */
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
