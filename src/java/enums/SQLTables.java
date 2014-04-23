/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;

/**
 *
 * @author Azathoth
 */
public enum SQLTables {//název tabulky      číslo používané v jsp   primární klíč
    NULL_TABLE(         "",                 "",                     Label.USERNAME         ),//kvůli nepřihlášenému uživateli a úspěšnému buildu stránky při zákazu direct accessu
    APPLICANTS(         "uchazeci",         "0",                    Label.USERNAME         ),
    APPLICANTS_SPAM(    "uchazeci_spam",    "1",                    Label.USERNAME         ),
    APPLICANTS_IPSPAM(  "uchazeci_ipspam",  "2",                    Label.USERNAME         ),
    STUDENTS(           "studenti",         "3",                    Label.USERNAME         ),
    LOGIN(              "login",            "4",                    Label.USERNAME         ),
    PEDAGOGOVE(         "pedagogove",       "5",                    Label.USERNAME         ),
    ADMINISTRATIVA(     "administrativa",   "6",                    Label.USERNAME         ),
    ;
    
    private final String table;
    private final String numberAsString;                                              //url proměnné mají pouze typ string
    private final Label primaryKey;
    
    private SQLTables(String table, String numberAsString, Label primaryKey) {
        this.table = table;
        this.numberAsString = numberAsString;
        this.primaryKey = primaryKey;
        
    }

    public Label getPrimaryKey() {
        return primaryKey;
    }
    
    public static SQLTables getTableFromNumberInString(String i) throws IllegalArgumentException{  //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (SQLTables a : SQLTables.values()) {
            if (a.numberAsString.equals(i)) {
                return a;
            }
        }
        throw new IllegalArgumentException("No table found for "+i+".");
    }
    
    public String getTable() {
        return table;
    }

    public String getNumberAsString() {
        return numberAsString;
    }
}
