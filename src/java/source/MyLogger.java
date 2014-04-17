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
            MyLogger.handler = new FileHandler("serverlogger.log");
            MyLogger.logger.addHandler(handler);
            init=true;
        } catch (IOException | SecurityException ex) {
            java.util.logging.Logger.getLogger(MyLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static java.util.logging.Logger getLogger() {
        if (!init) {
            init();
        }
        return logger;
    }
}
