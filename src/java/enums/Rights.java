package enums;

/**
 * This enum is used for keeping inormation about Rights to access websites. 
 * RightsValue represents numeric value of Rights, is used for comparing. 
 * RightsString describes Rights by words in CZ. 
 * InitialRedirect determines ehere is user rederected after login.
 * @author Azathoth
 */
public enum Rights {//rightsValue   rightsString    initialRedirect         table
    
    /**
     * Not logged user, completely no Rights, used for users who are not logged.
     */
    NOT_LOGGED(         5,  "nepřihlášený",         "index.jsp",            SQLTable.NULL_TABLE        ),
    /**
     * Applicant, the one who fills electronic application.
     */
    APPLICANT(          4,  "uchazeč",              "ForLoggedIn",          SQLTable.APPLICANTS        ),
    /**
     * Student, the one who is accepted to study at this school.
     */
    STUDENT(            3,  "student",              "ForLoggedIn",          SQLTable.STUDENTS          ),
    /**
     * Teacher, the one who teaches at this school.
     */
    PEDAGOG(            2,  "pedagog",              "proPedagogy.jsp",      SQLTable.PEDAGOGOVE        ),
    /**
     * Administration, the one who does all paperwok and so on.
     */
    ADMINISTRATIVA(     1,  "administrativa",       "proAdministrativu.jsp",SQLTable.ADMINISTRATIVA    ),
    /**
     * Main admin, the one who controlls everything. Like god in church or GM in MMORPG.
     */
    MAIN_ADMIN(         0,  "hlavní administrátor", "index.jsp",            SQLTable.LOGIN             ),
    ;
    private final int rightsValue;
    private final String rightsString;
    private final String initialRedirect;                                             //na kterou stránku je dotyčný přesměrován po přihlášení
    private final SQLTable table;                                                    //tabulka, ve které jsou informace o tomto uživateli()napočítám LOGIN
    
    private Rights(int rightsValue, String rightsString, String initialRedirect, SQLTable table) {
        this.rightsValue = rightsValue;
        this.rightsString = rightsString;
        this.initialRedirect = initialRedirect;
        this.table = table;
        
    }
    
    /**
     * Static method which returns enum with same value in rightsValue field as integer provided as parameter.
     * @param i integer which is searched for in whole class in rightsValue field .
     * @return enum containing integer provided as input in rightsValue field
     * @throws IllegalArgumentException when no enum contains provided integer in rightsValue field
     */
    public static Rights getRightsFromInt(int i) throws IllegalArgumentException{   //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Rights r : Rights.values()) {
            if (r.rightsValue==i) {
                return r;
            }
        }
        throw new IllegalArgumentException("No rights found for "+i+".");
    }

    /**
     * 
     * @return SQL table containing more information about user with these rights
     */
    public SQLTable getTable() {
        return table;
    }

    /**
     * 
     * @return rights or user as integer
     */
    public int getRightsValue() {
        return rightsValue;
    }

    /**
     * 
     * @return string with name of jsp page where is user with these rights redirected after login
     */
    public String getInitialRedirect() {
        return initialRedirect;
    }

    /**
     * 
     * @return string representation of rights, used for users
     */
    public String getRightsString() {
        return rightsString;
    }
    
}
