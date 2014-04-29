package enums;

/**
 * This enum is used for storing information about MySQL tables. 
 * Constains name of table, number used for determining table from URL variable 
 * and primary key.
 * @author Azathoth
 */
public enum SQLTable {//název tabulky      číslo používané v jsp   primární klíč
    
    /**
     * No real table, used to handle null pointer exception when any page requiring SQLTable is accesses directly, thus lacking proper SQLTable.
     */
    NULL_TABLE(         "",                 "",                    Label.USERNAME         ),//kvůli nepřihlášenému uživateli a úspěšnému buildu stránky při zákazu direct accessu
    /**
     * SQL Table containing applicants.
     */
    APPLICANTS(         "uchazeci",         "0",                   Label.USERNAME         ),
    /**
     * SQL Table containing applicants who filled input which was not meant to be filled.
     */
    APPLICANTS_SPAM(    "uchazeci_spam",    "1",                   Label.USERNAME         ),
    /**
     * SQL Table containing applicants who spam from one IP address.
     */
    APPLICANTS_IPSPAM(  "uchazeci_ipspam",  "2",                   Label.USERNAME         ),
    /**
     * SQL Table containing students.
     */
    STUDENTS(           "studenti",         "3",                   Label.USERNAME         ),
    /**
     * SQL Table containing all users.
     */
    LOGIN(              "login",            "4",                   Label.USERNAME         ),
    /**
     * SQL Table containing teachers.
     */
    PEDAGOGOVE(         "pedagogove",       "5",                   Label.USERNAME         ),
    /**
     * SQL Table containing administration.
     */
    ADMINISTRATIVA(     "administrativa",   "6",                   Label.USERNAME         ),
    ;
    
    private final String table;
    private final String numberAsString;                                              //url proměnné mají pouze typ string
    private final Label primaryKey;
    
    private SQLTable(String table, String numberAsString, Label primaryKey) {
        this.table = table;
        this.numberAsString = numberAsString;
        this.primaryKey = primaryKey;
        
    }

    /**
     * 
     * @return primary key of this SQL table
     */
    public Label getPrimaryKey() {
        return primaryKey;
    }
    
    /**
     * Returns SQLTable which has same string in numberAsString as String provided as parameter and throws IllegalArgumentException when no table is found
     * @param i String which is used for finding desired table
     * @return SQLTable with same 
     * @throws IllegalArgumentException 
     */
    public static SQLTable getTableFromNumberInString(String i) throws IllegalArgumentException{  //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (SQLTable a : SQLTable.values()) {
            if (a.numberAsString.equals(i)) {
                return a;
            }
        }
        throw new IllegalArgumentException("No table found for "+i+".");
    }
    
    /**
     * 
     * @return name of table used in sql database as String
     */
    public String getTable() {
        return table;
    }

    /**
     * 
     * @return number of SQLTable used for working with sql tables through url, tables are numbered as natural numbers in String, because session variables in URL allow only Strings.
     */
    public String getNumberAsString() {
        return numberAsString;
    }
}
