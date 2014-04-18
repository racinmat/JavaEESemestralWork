/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 *
 * @author Azathoth
 */
public class MyLogger {
    
    private static FileHandler handler;
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("server");
    private static boolean init=false;

    
    private static void init(){
        try {
            MyLogger.handler = new FileHandler("X:\\Disk Google\\moje věci_encrypted\\škola\\2. semestr\\programování 2\\Semestralka2_decrypted\\racinmat\\logger.txt");    //změnit cestu, pokud budou stránky na serveru
            MyLogger.logger.addHandler(handler);
            init=true;
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Failed inicialization of FileHandler a thus logs cannot be written to file.", ex);
        }
    }
    
    public static java.util.logging.Logger getLogger() {
        if (!init) {
            init();
        }
        return logger;
    }
}
