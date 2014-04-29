package source;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * This class uses static final logger and lazy inicialized FileHandler to write
 * every log to file. Every class in this project uses only this logger.
 *
 * @author Azathoth
 */
public class MyLogger {

    private static FileHandler handler;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("server");
    private static boolean init = false;

    /**
     * Adds Filehandler to logger, when logger is used for the first time.
     */
    private static void init() {
        try {
            MyLogger.handler = new FileHandler("X:\\Disk Google\\moje věci_encrypted\\škola\\2. semestr\\programování 2\\Semestralka2_decrypted\\racinmat\\logger.txt", true);    //změnit cestu, pokud budou stránky na serveru, true říká, že se nemá vytvořit další soubor, ale pokračovat ve starém
            MyLogger.logger.addHandler(handler);
            init = true;
        } catch (IOException | SecurityException ex) {
            logger.logp(Level.SEVERE, MyLogger.class.getName(), "init", "Failed inicialization of FileHandler a thus logs cannot be written to file.", ex);
        }
    }

    /**
     *
     * @return static logger with added FileHandler
     */
    public static java.util.logging.Logger getLogger() {
        if (!init) {
            init();
        }
        return logger;
    }
}
