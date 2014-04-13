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
    notLogged(          5,  "nepřihlášený",         "index.jsp",            SQLTables.nullTable         ),
    applicant(          4,  "uchazeč",              "proPrihlasene.jsp",    SQLTables.applicants          ),
    student(            3,  "student",              "proPrihlasene.jsp",    SQLTables.students          ),
    pedagog(            2,  "pedagog",              "proPedagogy.jsp",      SQLTables.teachers        ),
    administrativa(     1,  "administrativa",       "proAdministrativu.jsp",SQLTables.administrativa    ),
    mainAdmin(          0,  "hlavní administrátor", "index.jsp",            SQLTables.login             ),
    ;
    private int rightsValue;
    private String rightsString;
    private String initialRedirect;                                             //na kterou stránku je dotyčný přesměrován po přihlášení
    private SQLTables table;                                                    //tabulka, ve které jsou informace o tomto uživateli()napočítám login
    
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
