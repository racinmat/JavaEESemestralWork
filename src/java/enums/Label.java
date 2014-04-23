/*                                                                                                                                                                                       
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;


/**                                                                                                                                                                                                                                                         u nepoviných,
 *                                                                                                                                                                                                                                                          povinné při z jakého        co si může                                          povinné
 * @author Azathoth                                           //používá se také pro změnu hesla ve                                             //uzivatel to         sloupec se                                                                              vyplňování  labelu se          uživatel sám                                        
 */                                                           //formuláři na stránce proprihlasene                                             //nemusi vyplnovat  //nachazi v tabulce:                                                                      formuláře   kopírují hodnoty   sobě změnit                    emailová    rodné                                //liší se v políčku všechny sloupce
public enum Label {           //nameForUsers                    nameForApplicants                            nameRaw                    number  autoFill            APPLICANTS  STUDENTS    LOGIN   primaryKey  PEDAGOGOVE    ADMINISTRATIVA  telefonniCislo  povinne     kopirovanoZ         menitelneUzivatelem zmenaHesla  adresa      číslo   chazeciSpam    applicantsIPSpam  vypisProAdministrativu  menitelneAdministrativou
    USERNAME(                   "uživatelské jméno",            "uživatelské jméno",                         "uzivatelskejmeno",        false,  true,               true,       true,       true,   true,       true,       true,           false,          false,      null,               false,              false,      false,      false,  true,           true,          false,                  false        ),
    NAME(                       "jméno",                        "jméno",                                     "jmeno",                   false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    LASTNAME(                   "příjmení",                     "příjmení",                                  "prijmeni",                false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    PASSWORD(                   "hash hesla",                   "Zadejte své heslo:",                        "hashhesla",               false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,               false,              true,       false,      false,  false,          false,         false,                  false        ),
    STUDY_PROGRAM(              "studijní program",             "studijní program",                          "studijniprogram",         false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BRANCH_OF_STUDY(            "studijní obor",                "studijní obor",                             "studijniobor",            false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    SEX(                        "pohlaví",                      "pohlaví",                                   "pohlavi",                 false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    CITIZENSHIP(                "státní příslušnost",           "státní příslušnost",                        "statniprislusnost",       false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    MARITAL_STATUS(             "rodinný stav",                 "rodinný stav",                              "rodinnystav",             false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    EMAIL(                      "email",                        "email",                                     "email",                   false,  false,              true,       false,      false,  false,      true,       true,           false,          true,       null,               true,               false,      true,       false,  true,           true,          true,                   true         ),
    CELLPHONE(                  "mobilní telefon",              "mobilní telefon",                           "mobilnitelefon",          false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),  
    BIRTHDAY(                   "den narození",                 "den narození",                              "dennarozeni",             true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BIRTHMONTH(                 "měsíc narození",               "měsíc narození",                            "mesicnarozeni",           true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BIRTHYEAR(                  "rok narození",                 "rok narození",                              "roknarozeni",             true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    ID(                         "číslo OP",                     "číslo OP",                                  "cisloOP",                 true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BIRTHNUMBER(                "rodné číslo",                  "rodné číslo",                               "rodnecislo",              false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      true,   true,           true,          true,                   true         ),
    PASSPORT(                   "číslo pasu",                   "číslo pasu",                                "cislopasu",               true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BIRTHPLACE(                 "místo narození",               "místo narození",                            "mistonarozeni",           false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    BIRTHCOUNTY(                "okres narození",               "okres narození",                            "okresnarozeni",           false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    STREET(                     "ulice",                        "ulice",                                     "ulice",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    HOUSE_NUMBER(               "číslo domu",                   "číslo domu",                                "cislodomu",               true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    MUNICIPALITY_PART(          "část obce",                    "část obce",                                 "castobce",                false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    MUNICIPALITY(               "obec",                         "obec",                                      "obec",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    COUNTY(                     "okres",                        "okres",                                     "okres",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    ZIP(                        "psč",                          "psč",                                       "psc",                     true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    COUNTRY(                    "stát",                         "stát",                                      "stat",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    PHONE(                      "telefon",                      "telefon",                                   "telefon",                 false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    POST(                       "pošta",                        "pošta",                                     "posta",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_STREET(             "kontaktní adresa: ulice",      "ulice",                                     "kontaktniadresaulice",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      STREET,             true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_HOUSE_NUMBER(       "kontaktní adresa: číslo domu", "číslo domu",                                "kontaktniadresacislodomu",true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      HOUSE_NUMBER,        true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_MUNICIPALITY_PART(  "kontaktní adresa: část obce",  "část obce",                                 "kontaktniadresacastobce", false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      MUNICIPALITY_PART,   true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_MUNICIPALITY(       "kontaktní adresa: obec",       "obec",                                      "kontaktniadresaobec",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      MUNICIPALITY,       true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_COUTY(              "kontaktní adresa: okres",      "okres",                                     "kontaktniadresaokres",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      COUNTY,              true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_ZIP(                "kontaktní adresa: psč",        "psč",                                       "kontaktniadresapsc",      true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      ZIP,                true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_COUNTRY(            "kontaktní adresa: stát",       "stát",                                      "kontaktniadresastat",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      COUNTRY,            true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_PHONE(              "kontaktní adresa: telefon",    "telefon",                                   "kontaktniadresatelefon",  false,  false,              true,       false,      false,  false,      false,      false,          true,           false,      PHONE,              true,               false,      false,      false,  true,           true,          true,                   true         ),
    CONTACT_POST(               "kontaktní adresa: pošta",      "pošta",                                     "kontaktniadresaposta",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      POST,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    NAME_OF_HIGH_SCHOOL(        "název střední školy",          "název střední školy",                       "nazevstredniskoly",       false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    ADDRESS_OF_HIGH_SCHOOL(     "adresa střední školy",         "adresa střední školy",                      "adresastredniskoly",      false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    OBOR_OF_HIGH_SCHOOL(        "obor střední školy",           "obor střední školy",                        "oborstredniskoly",        false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    JKOV(                       "jkov",                         "jkov",                                      "jkov",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    KKOV(                       "kkov",                         "kkov",                                      "kkov",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    IZO(                        "izo",                          "izo",                                       "izo",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    YEAR_MATURITY(              "rok maturity",                 "rok maturity",                              "rokmaturity",             true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    APPLICATION_STATE(          "stav přihlášky",               "stav přihlášky",                            "stavprihlasky",           false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    TUITION(                    "školné",                       "školné",                                    "skolne",                  false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    SEMESTER(                   "semestr",                      "semestr",                                   "semestr",                 true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    STATE_OF_STUDYING(          "stav studia",                  "stav studia",                               "stavstudia",              false,  false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    GROUP(                      "skupina",                      "skupina",                                   "skupina",                 true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    RIGHTS(                     "práva",                        "práva",                                     "rights",                  false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  false,          false,         false,                  false        ),
    ALL_COLUMNS(                "všechny sloupce",              "všechny sloupce",                           "vsechnysloupce",          false,  false,              false,      false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  false,          false,         true,                   false        ),
    NEW_PASSWORD(               "nové heslo",                   "Zadejte své nové heslo:",                   "noveheslo",               false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,               true,               true,       false,      false,  false,          false,         false,                  false        ),
    NEW_PASSWORD_CHECK(         "nové heslo kontrola",          "Zadejte pro kontrolu znovu své nové heslo:","kontrola",                false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,               true,               true,       false,      false,  false,          false,         false,                  false        ),
    ;
    private final String nameForUsers;
    private final String nameForApplicants;
    private final String nameRaw;
    private final boolean number;
    private final boolean autoFill;
    private final boolean applicants;
    private final boolean students;
    private final boolean login;
    private final boolean primaryKey;
    private final boolean teachers;
    private final boolean administrativa;
    private final boolean phonenumber;
    private final boolean obligatory;
    private final Label copiedFrom;  //null, pokud není z ničeho kopírováno
    private final boolean changableByUser;
    private final boolean passwordChange; //zda je ve formuláři změna hesla, nemá nic společného s databází
    private final boolean emailaddress;
    private final boolean birthNumber;
    private final boolean applicantsSpam;
    private final boolean applicantsIPSpam;
    private final boolean showToAdministrativa;
    private final boolean changableByAdministrativa;
    
    private Label(String nameForUsers, String nameForApplicants, String nameRaw, boolean number, boolean autoFill, boolean applicants, boolean students, boolean login, boolean primaryKey, boolean teachers, boolean administrativa, boolean phonenumber, boolean obligatory, Label copiedFrom, boolean changableByUser, boolean passwordChange, boolean emailaddress, boolean birthNumber, boolean applicantsSpam, boolean applicantsIPSpam, boolean showToAdministrativa, boolean changableByAdministrativa) {
        this.nameForUsers = nameForUsers;
        this.nameForApplicants = nameForApplicants;
        this.nameRaw = nameRaw;
        this.number = number;
        this.autoFill = autoFill;
        this.applicants = applicants;
        this.students = students;
        this.login = login;
        this.primaryKey = primaryKey;
        this.teachers = teachers;
        this.administrativa = administrativa;
        this.phonenumber = phonenumber;
        this.obligatory = obligatory;
        this.copiedFrom = copiedFrom;
        this.changableByUser = changableByUser;
        this.passwordChange = passwordChange;
        this.emailaddress = emailaddress;
        this.birthNumber = birthNumber;
        this.applicantsSpam = applicantsSpam;
        this.applicantsIPSpam = applicantsIPSpam;
        this.showToAdministrativa = showToAdministrativa;
        this.changableByAdministrativa = changableByAdministrativa;
        
    }
    
    public boolean isChangableByUser() {
        return changableByUser;
    }
    
    public boolean isApplicantsSpam() {
        return applicantsSpam;
    }

    public boolean isApplicantsIPSpam() {
        return applicantsIPSpam;
    }

    public boolean isbirthNumber() {
        return birthNumber;
    }

    public boolean isEmailAddress() {
        return emailaddress;
    }

    public boolean isPasswordChange() {
        return passwordChange;
    }

    public boolean isObligatory() {
        return obligatory;
    }

    public Label getCopiedFrom() {
        return copiedFrom;
    }

    public boolean isTeachers() {
        return teachers;
    }

    public boolean isAdministrativa() {
        return administrativa;
    }

    public boolean isPhonenumber() {
        return phonenumber;
    }

    public String getNameForUsers() {
        return nameForUsers;
    }

    public boolean isShowToAdministrativa() {
        return showToAdministrativa;
    }

    public boolean isChangableByAdministrativa() {
        return changableByAdministrativa;
    }

    public String getNameForApplicants() {
        return nameForApplicants;
    }

    public String getNameRaw() {
        return nameRaw;
    }

    public boolean isNumber() {
        return number;
    }

    public boolean isAutoFill() {
        return autoFill;
    }

    public boolean isApplicants() {
        return applicants;
    }

    public boolean isStudents() {
        return students;
    }

    public boolean isLogin() {
        return login;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }
    
    /**
     * Counts number of booleans true in all labels in column depending on provided table.
     * Only tables LOGIN, uchazeci and STUDENTS are supported, because in uchazeci_spam and uchazeci_ipspam have same columns.
     * @param table
     * @return Return count of labels which are contained in provided table.
     */
    public static int getNumberOfColumnsInTable(SQLTables table){
        int count=0;
        for (Label label : Label.values()) {
            if (label.isInTable(table)) {
                count++;
            }
        }
        return count;
    }
    
    public static int getNumberOfColumnsInTables(SQLTables... table){
        int count=0;
        for (Label label : Label.values()) {
            boolean temp=false;
            for (SQLTables tableIter : table) {
                if (label.isInTable(tableIter)) {
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
     * @param table table which is tested to contain provided label.
     * @return Returns true if label is in table and returns false if wrong table is put or label is not in table.
     */
    public boolean isInTable(SQLTables table){
        boolean output = false;
        String temp=table.getTable();
        if (temp.equals(SQLTables.STUDENTS.getTable())) {
            output=isStudents();
        } else if (temp.equals(SQLTables.APPLICANTS.getTable())){
            output=isApplicants();
        } else if (temp.equals(SQLTables.LOGIN.getTable())){
            output=isLogin();
        } else if (temp.equals(SQLTables.APPLICANTS_SPAM.getTable())){
            output=isApplicantsSpam();
        } else if (temp.equals(SQLTables.APPLICANTS_IPSPAM.getTable())){
            output=isApplicantsIPSpam();
        } else if (temp.equals(SQLTables.PEDAGOGOVE.getTable())){
            output=isTeachers();
        } else if (temp.equals(SQLTables.ADMINISTRATIVA.getTable())){
            output=isAdministrativa();
        }
        return output;
    }
    
    /**
     * Counts number of booleans true in all labels in column depending on provided tables, counts if label is contained in one or more of provided tables.
     * Tests disjuction of being contained in one of provided tables.
     * @param table one or more tables
     * @return Return count of labels which are contained in one of provided tables.
     */
    public boolean isInTables(SQLTables... table){
        for (SQLTables tableIter : table) {
            String temp=tableIter.getTable();
            if (temp.equals(SQLTables.STUDENTS.getTable())) {
                if (isStudents()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.APPLICANTS.getTable())){
                if (isApplicants()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.LOGIN.getTable())){
                if (isLogin()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.APPLICANTS_IPSPAM.getTable())){
                if (isApplicantsIPSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.APPLICANTS_SPAM.getTable())){
                if (isApplicantsSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.PEDAGOGOVE.getTable())){
                if (isTeachers()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.ADMINISTRATIVA.getTable())){
                if (isAdministrativa()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Label getLabelFromStringInnameRaw(String i) throws IllegalArgumentException{ //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Label label : Label.values()) {
            if (label.getNameRaw().equals(i)) {
                return label;
            }
        }
        throw new IllegalArgumentException("No label found for "+i+".");
    }
}
