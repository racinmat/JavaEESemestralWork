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
    nullTable(          "",                 "",                     Label.uzivatelskejmeno         ),//kvůli nepřihlášenému uživateli
    uchazeci(           "uchazeci",         "0",                    Label.uzivatelskejmeno         ),
    uchazeci_spam(      "uchazeci_spam",    "1",                    Label.uzivatelskejmeno         ),
    uchazeci_ipspam(    "uchazeci_ipspam",  "2",                    Label.uzivatelskejmeno         ),
    studenti(           "studenti",         "3",                    Label.uzivatelskejmeno         ),
    login(              "login",            "4",                    Label.uzivatelskejmeno         ),
    pedagogove(         "pedagogove",       "5",                    Label.uzivatelskejmeno         ),
    administrativa(     "administrativa",   "6",                    Label.uzivatelskejmeno         ),
    ;
    
    private String table;
    private String numberAsString;                                              //url proměnné mají pouze typ string
    private Label primaryKey;
    
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
