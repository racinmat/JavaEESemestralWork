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
public enum Rights {//rightsValue   rightsString    initialRedirect         table
    NOT_LOGGED(         5,  "nepřihlášený",         "index.jsp",            SQLTables.NULL_TABLE         ),
    APPLICANT(          4,  "uchazeč",              "ForLoggedIn",          SQLTables.APPLICANTS        ),
    STUDENT(            3,  "student",              "ForLoggedIn",          SQLTables.STUDENTS          ),
    PEDAGOG(            2,  "pedagog",              "proPedagogy.jsp",      SQLTables.PEDAGOGOVE          ),
    ADMINISTRATIVA(     1,  "administrativa",       "proAdministrativu.jsp",SQLTables.ADMINISTRATIVA    ),
    MAIN_ADMIN(         0,  "hlavní administrátor", "index.jsp",            SQLTables.LOGIN             ),
    ;
    private final int rightsValue;
    private final String rightsString;
    private final String initialRedirect;                                             //na kterou stránku je dotyčný přesměrován po přihlášení
    private final SQLTables table;                                                    //tabulka, ve které jsou informace o tomto uživateli()napočítám LOGIN
    
    private Rights(int rightsValue, String rightsString, String initialRedirect, SQLTables table) {
        this.rightsValue = rightsValue;
        this.rightsString = rightsString;
        this.initialRedirect = initialRedirect;
        this.table = table;
        
    }
    
    public static Rights getRightsFromInt(int i) throws IllegalArgumentException{   //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Rights r : Rights.values()) {
            if (r.rightsValue==i) {
                return r;
            }
        }
        throw new IllegalArgumentException("No rights found for "+i+".");
    }

    public SQLTables getTable() {
        return table;
    }

    public int getRightsValue() {
        return rightsValue;
    }

    public String getInitialRedirect() {
        return initialRedirect;
    }

    public String getRightsString() {
        return rightsString;
    }
    
}
