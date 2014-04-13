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
public enum ApplicationState {
    accepted(           "přijat",                            false,  "prijat"               ),
    paidfee(            "zaplacen registrační poplatek",     false,  "zaplacenpoplatek"     ),
    notpaidfee(         "nezaplacen registrační poplatek",   false,  "nezaplacenpoplatek"   ),
    notchecked(         "nezevidován administrativou",       true,   "nezevidovan"          ),
    ;
    private String name;
    private boolean defaultboolean;
    private String nameRaw;

    private ApplicationState(String name, boolean defaultboolean, String nameRaw) {
        this.name = name;
        this.defaultboolean = defaultboolean;
        this.nameRaw = nameRaw;
    }

    public String getNameRaw() {
        return nameRaw;
    }

    public String getName() {
        return name;
    }

    public boolean isDefaultBoolean() {
        return defaultboolean;
    }
    
    public static ApplicationState getDefaultBoolean(){
        for (ApplicationState stav : ApplicationState.values()) {
            if (stav.isDefaultBoolean()) {
                return stav;
            }
        }
        throw new NullPointerException("No default row in this enum.");
    }
}
