/*                                                                                                                                                                                       
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;

import static enums.LabelCategory.cisloId;

/**                                                                                                                                                                                                                                                 u nepoviných,
 *                                                                                                                                                                                                                                      povinné při z jakého        co si může                                          povinné
 * @author Azathoth                                           //používá se také pro změnu hesla ve                                             //uzivatel to         sloupec se                                                                              vyplňování  labelu se       uživatel sám                                        
 */                                                           //formuláři na stránce proprihlasene                                             //nemusi vyplnovat  //nachazi v tabulce:                                                                      formuláře   kopírují hodnoty sobě změnit                    emailová    rodné                                //liší se v políčku všechny sloupce
public enum Label {           //nazevProUzivatele               nazevProUchazece                             nazevRaw                    ciselne automatickeVyplneni uchazeci    studenti    login   primaryKey  pedagogove  administrativa  telefonniCislo  povinne     kopirovanoZ     menitelneUzivatelem zmenaHesla  adresa      číslo   chazeciSpam    uchazeciIPSpam  vypisProAdministrativu  menitelneAdministrativou
    uzivatelskejmeno(           "uživatelské jméno",            "uživatelské jméno",                         "uzivatelskejmeno",         false,  true,               true,       true,       true,   true,       true,       true,           false,          false,      null,           false,              false,      false,      false,  true,           true,          false,                  false        ),
    jmeno(                      "jméno",                        "jméno",                                     "jmeno",                    false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  false,          false,         true,                   true         ),
    prijmeni(                   "příjmení",                     "příjmení",                                  "prijmeni",                 false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  false,          false,         true,                   true         ),
    hashhesla(                  "hash hesla",                   "Zadejte své heslo:",                        "hashhesla",                false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,           false,              true,       false,      false,  false,          false,         false,                  false        ),
    studijniprogram(            "studijní program",             "studijní program",                          "studijniprogram",          false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    studijniobor(               "studijní obor",                "studijní obor",                             "studijniobor",             false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    pohlavi(                    "pohlaví",                      "pohlaví",                                   "pohlavi",                  false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    statniprislusnost(          "státní příslušnost",           "státní příslušnost",                        "statniprislusnost",        false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    rodinnystav(                "rodinný stav",                 "rodinný stav",                              "rodinnystav",              false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    email(                      "email",                        "email",                                     "email",                    false,  false,              true,       false,      false,  false,      true,       true,           false,          true,       null,           true,               false,      true,       false,  true,           true,          true,                   true         ),
    mobilnitelefon(             "mobilní telefon",              "mobilní telefon",                           "mobilnitelefon",           false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),  
    dennarozeni(                "den narození",                 "den narození",                              "dennarozeni",              true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    mesicnarozeni(              "měsíc narození",               "měsíc narození",                            "mesicnarozeni",            true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    roknarozeni(                "rok narození",                 "rok narození",                              "roknarozeni",              true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    cisloOP(                    "číslo OP",                     "číslo OP",                                  "cisloOP",                  true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    rodnecislo(                 "rodné číslo",                  "rodné číslo",                               "rodnecislo",               false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      true,   true,           true,          true,                   true         ),
    cislopasu(                  "číslo pasu",                   "číslo pasu",                                "cislopasu",                true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    mistonarozeni(              "místo narození",               "místo narození",                            "mistonarozeni",            false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    okresnarozeni(              "okres narození",               "okres narození",                            "okresnarozeni",            false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    ulice(                      "ulice",                        "ulice",                                     "ulice",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    cislodomu(                  "číslo domu",                   "číslo domu",                                "cislodomu",                true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    castobce(                   "část obce",                    "část obce",                                 "castobce",                 false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    obec(                       "obec",                         "obec",                                      "obec",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    okres(                      "okres",                        "okres",                                     "okres",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    psc(                        "psč",                          "psč",                                       "psc",                      true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    stat(                       "stát",                         "stát",                                      "stat",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    telefon(                    "telefon",                      "telefon",                                   "telefon",                  false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    posta(                      "pošta",                        "pošta",                                     "posta",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresaulice(       "kontaktní adresa: ulice",      "ulice",                                     "kontaktniadresaulice",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      ulice,          true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresacislodomu(   "kontaktní adresa: číslo domu", "číslo domu",                                "kontaktniadresacislodomu", true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      cislodomu,      true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresacastobce(    "kontaktní adresa: část obce",  "část obce",                                 "kontaktniadresacastobce",  false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      castobce,       true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresaobec(        "kontaktní adresa: obec",       "obec",                                      "kontaktniadresaobec",      false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      obec,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresaokres(       "kontaktní adresa: okres",      "okres",                                     "kontaktniadresaokres",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      okres,          true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresapsc(         "kontaktní adresa: psč",        "psč",                                       "kontaktniadresapsc",       true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      psc,            true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresastat(        "kontaktní adresa: stát",       "stát",                                      "kontaktniadresastat",      false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      stat,           true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresatelefon(     "kontaktní adresa: telefon",    "telefon",                                   "kontaktniadresatelefon",   false,  false,              true,       false,      false,  false,      false,      false,          true,           false,      telefon,        true,               false,      false,      false,  true,           true,          true,                   true         ),
    kontaktniadresaposta(       "kontaktní adresa: pošta",      "pošta",                                     "kontaktniadresaposta",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      posta,          true,               false,      false,      false,  true,           true,          true,                   true         ),
    nazevstredniskoly(          "název střední školy",          "název střední školy",                       "nazevstredniskoly",        false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    adresastredniskoly(         "adresa střední školy",         "adresa střední školy",                      "adresastredniskoly",       false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    oborstredniskoly(           "obor střední školy",           "obor střední školy",                        "oborstredniskoly",         false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    jkov(                       "jkov",                         "jkov",                                      "jkov",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    kkov(                       "kkov",                         "kkov",                                      "kkov",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    izo(                        "izo",                          "izo",                                       "izo",                      false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    rokmaturity(                "rok maturity",                 "rok maturity",                              "rokmaturity",              true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    stavprihlasky(              "stav přihlášky",               "stav přihlášky",                            "stavprihlasky",            false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    skolne(                     "školné",                       "školné",                                    "skolne",                   false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  true,           true,          true,                   true         ),
    semestr(                    "semestr",                      "semestr",                                   "semestr",                  true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  false,          false,         true,                   true         ),
    stavstudia(                 "stav studia",                  "stav studia",                               "stavstudia",               false,  false,              false,      true,       false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  false,          false,         true,                   true         ),
    skupina(                    "skupina",                      "skupina",                                   "skupina",                  true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,           false,              false,      false,      false,  false,          false,         true,                   true         ),
    rights(                     "práva",                        "práva",                                     "rights",                   false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  false,          false,         false,                  false        ),
    vsechnySloupce(             "všechny sloupce",              "všechny sloupce",                           "vsechnysloupce",           false,  false,              false,      false,      false,  false,      false,      false,          false,          false,      null,           false,              false,      false,      false,  false,          false,         true,                   false        ),
    noveheslo(                  "nové heslo",                   "Zadejte své nové heslo:",                   "noveheslo",                false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,           true,               true,       false,      false,  false,          false,         false,                  false        ),
    noveheslokonrola(           "nové heslo kontrola",          "Zadejte pro kontrolu znovu své nové heslo:","konrola",                  false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,           true,               true,       false,      false,  false,          false,         false,                  false        ),
    ;
    private String nazevProUzivatele;
    private String nazevProUchazece;
    private String nazevRaw;
    private boolean ciselne;
    private boolean automatickeVyplneni;
    private boolean uchazeci;
    private boolean studenti;
    private boolean login;
    private boolean primaryKey;
    private boolean pedagogove;
    private boolean administrativa;
    private boolean telefonniCislo;
    private boolean povinne;
    private Label kopirovanoZ;  //null, pokud není z ničeho kopírováno
    private boolean menitelneUzivatelem;
    private boolean zmenaHesla; //zda je ve formuláři změna hesla, nemá nic společného s databází
    private boolean emailovaadresa;
    private boolean rodneCislo;
    private boolean uchazeciSpam;
    private boolean uchazeciIPSpam;
    private boolean vypisProAdministrativu;
    private boolean menitelneAdministrativou;
    
    private Label(String nazevProUzivatele, String nazevProUchazece, String nazevRaw, boolean ciselne, boolean automatickeVyplneni, boolean uchazeci, boolean studenti, boolean login, boolean primaryKey, boolean pedagogove, boolean administrativa, boolean telefonniCislo, boolean povinne, Label kopirovanoZ, boolean menitelneUzivatelem, boolean zmenaHesla, boolean emailovaadresa, boolean rodneCislo, boolean uchazeciSpam, boolean uchazeciIPSpam, boolean vypisProAdministrativu, boolean menitelneAdministrativou) {
        this.nazevProUzivatele = nazevProUzivatele;
        this.nazevProUchazece = nazevProUchazece;
        this.nazevRaw = nazevRaw;
        this.ciselne = ciselne;
        this.automatickeVyplneni = automatickeVyplneni;
        this.uchazeci = uchazeci;
        this.studenti = studenti;
        this.login = login;
        this.primaryKey = primaryKey;
        this.pedagogove = pedagogove;
        this.administrativa = administrativa;
        this.telefonniCislo = telefonniCislo;
        this.povinne = povinne;
        this.kopirovanoZ = kopirovanoZ;
        this.menitelneUzivatelem = menitelneUzivatelem;
        this.zmenaHesla = zmenaHesla;
        this.emailovaadresa = emailovaadresa;
        this.rodneCislo = rodneCislo;
        this.uchazeciSpam = uchazeciSpam;
        this.uchazeciIPSpam = uchazeciIPSpam;
        this.vypisProAdministrativu = vypisProAdministrativu;
        this.menitelneAdministrativou = menitelneAdministrativou;
        
    }
    
    public boolean isMenitelneUzivatelem() {
        return menitelneUzivatelem;
    }
    
    public boolean isUchazeciSpam() {
        return uchazeciSpam;
    }

    public boolean isUchazeciIPSpam() {
        return uchazeciIPSpam;
    }

    public boolean isRodneCislo() {
        return rodneCislo;
    }

    public boolean isEmailovaadresa() {
        return emailovaadresa;
    }

    public boolean isZmenaHesla() {
        return zmenaHesla;
    }

    public boolean isPovinne() {
        return povinne;
    }

    public Label getKopirovanoZ() {
        return kopirovanoZ;
    }

    public boolean isPedagogove() {
        return pedagogove;
    }

    public boolean isAdministrativa() {
        return administrativa;
    }

    public boolean isTelefonniCislo() {
        return telefonniCislo;
    }

    public String getNazevProUzivatele() {
        return nazevProUzivatele;
    }

    public boolean isVypisProAdministrativu() {
        return vypisProAdministrativu;
    }

    public boolean isMenitelneAdministrativou() {
        return menitelneAdministrativou;
    }

    public String getNazevProUchazece() {
        return nazevProUchazece;
    }

    public String getNazevRaw() {
        return nazevRaw;
    }

    public boolean isCiselne() {
        return ciselne;
    }

    public boolean isAutomatickeVyplneni() {
        return automatickeVyplneni;
    }

    public boolean isUchazeci() {
        return uchazeci;
    }

    public boolean isStudenti() {
        return studenti;
    }

    public boolean isLogin() {
        return login;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }
    
    /**
     * Counts number of booleans true in all labels in column depending on provided table.
     * Only tables login, uchazeci and studenti are supported, because in uchazeci_spam and uchazeci_ipspam have same columns.
     * @param tabulka
     * @return Return count of labels which are contained in provided table.
     */
    public static int getNumberOfColumnsInTable(SQLTables tabulka){
        int count=0;
        for (Label label : Label.values()) {
            if (label.isInTable(tabulka)) {
                count++;
            }
        }
        return count;
    }
    
    public static int getNumberOfColumnsInTables(SQLTables... tabulka){
        int count=0;
        for (Label label : Label.values()) {
            boolean temp=false;
            for (SQLTables table : tabulka) {
                if (label.isInTable(table)) {
                    temp=true;
                }
            }
            if (temp) {
                count++;
            }

        }
        return count;
    }
    
    /**
     * Takes label and table and tests if label is in table.
     * @param label label which will be tested.
     * @param tabulka table which is tested to contain provided label.
     * @return Returns true if label is in table and returns false if wrong table is put or label is not in table.
     */
    public boolean isInTable(SQLTables tabulka){
        boolean output = false;
        String temp=tabulka.getTable();
        if (temp.equals(SQLTables.studenti.getTable())) {
            output=isStudenti();
        } else if (temp.equals(SQLTables.uchazeci.getTable())){
            output=isUchazeci();
        } else if (temp.equals(SQLTables.login.getTable())){
            output=isLogin();
        } else if (temp.equals(SQLTables.uchazeci_spam.getTable())){
            output=isUchazeciSpam();
        } else if (temp.equals(SQLTables.uchazeci_ipspam.getTable())){
            output=isUchazeciIPSpam();
        } else if (temp.equals(SQLTables.pedagogove.getTable())){
            output=isPedagogove();
        } else if (temp.equals(SQLTables.administrativa.getTable())){
            output=isAdministrativa();
        }
        return output;
    }
    
    /**
     * Counts number of booleans true in all labels in column depending on provided tables, counts if label is contained in one or more of provided tables.
     * Tests disjuction of being contained in one of provided tables.
     * @param tabulka one or more tables
     * @return Return count of labels which are contained in one of provided tables.
     */
    public boolean isInTables(SQLTables... tabulka){
        for (SQLTables table : tabulka) {
            String temp=table.getTable();
            if (temp.equals(SQLTables.studenti.getTable())) {
                if (isStudenti()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.uchazeci.getTable())){
                if (isUchazeci()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.login.getTable())){
                if (isLogin()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.uchazeci_ipspam.getTable())){
                if (isUchazeciIPSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.uchazeci_spam.getTable())){
                if (isUchazeciSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.pedagogove.getTable())){
                if (isPedagogove()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.administrativa.getTable())){
                if (isAdministrativa()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Label getLabelFromStringInNazevRaw(String i) throws IllegalArgumentException{ //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Label label : Label.values()) {
            if (label.getNazevRaw().equals(i)) {
                return label;
            }
        }
        throw new IllegalArgumentException("No label found for "+i+".");
    }
}
