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
public enum StavPrihlasky {
    prijat(             "přijat",                            false,  "prijat"               ),
    zaplacenypoplatek(  "zaplacen registrační poplatek",     false,  "zaplacenpoplatek"     ),
    nazplacenypoplatek( "nezaplacen registrační poplatek",   false,  "nezaplacenpoplatek"   ),
    nezevidovan(        "nezevidován administrativou",       true,   "nezevidovan"          ),
    ;
    private String nazev;
    private boolean defaultni;
    private String nazevRaw;

    private StavPrihlasky(String nazev, boolean defaultni, String nazevRaw) {
        this.nazev = nazev;
        this.defaultni = defaultni;
        this.nazevRaw = nazevRaw;
    }

    public String getNazevRaw() {
        return nazevRaw;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean isDefaultni() {
        return defaultni;
    }
    
    public static StavPrihlasky getDefaultni(){
        for (StavPrihlasky stav : StavPrihlasky.values()) {
            if (stav.isDefaultni()) {
                return stav;
            }
        }
        throw new NullPointerException("No default row in this enum.");
    }
}
