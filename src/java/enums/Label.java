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
public enum Label {           //nameForUsers                    nameForApplicants                            nameRaw                    number  autoFill            applicants  students    login   primaryKey  teachers    administrativa  telefonniCislo  povinne     kopirovanoZ         menitelneUzivatelem zmenaHesla  adresa      číslo   chazeciSpam    applicantsIPSpam  vypisProAdministrativu  menitelneAdministrativou
    userName(                   "uživatelské jméno",            "uživatelské jméno",                         "uzivatelskejmeno",        false,  true,               true,       true,       true,   true,       true,       true,           false,          false,      null,               false,              false,      false,      false,  true,           true,          false,                  false        ),
    name(                       "jméno",                        "jméno",                                     "jmeno",                   false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    lastname(                   "příjmení",                     "příjmení",                                  "prijmeni",                false,  false,              false,      false,      true,   false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    password(                   "hash hesla",                   "Zadejte své heslo:",                        "hashhesla",               false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,               false,              true,       false,      false,  false,          false,         false,                  false        ),
    studyProgram(               "studijní program",             "studijní program",                          "studijniprogram",         false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    branchOfStudy(              "studijní obor",                "studijní obor",                             "studijniobor",            false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    sex(                        "pohlaví",                      "pohlaví",                                   "pohlavi",                 false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    citizenship(                "státní příslušnost",           "státní příslušnost",                        "statniprislusnost",       false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    maritalStatus(              "rodinný stav",                 "rodinný stav",                              "rodinnystav",             false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    email(                      "email",                        "email",                                     "email",                   false,  false,              true,       false,      false,  false,      true,       true,           false,          true,       null,               true,               false,      true,       false,  true,           true,          true,                   true         ),
    cellphone(                  "mobilní telefon",              "mobilní telefon",                           "mobilnitelefon",          false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),  
    birthday(                   "den narození",                 "den narození",                              "dennarozeni",             true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    birthmonth(                 "měsíc narození",               "měsíc narození",                            "mesicnarozeni",           true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    birthyear(                  "rok narození",                 "rok narození",                              "roknarozeni",             true,   true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    ID(                         "číslo OP",                     "číslo OP",                                  "cisloOP",                 true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    birthnumber(                "rodné číslo",                  "rodné číslo",                               "rodnecislo",              false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      true,   true,           true,          true,                   true         ),
    passport(                   "číslo pasu",                   "číslo pasu",                                "cislopasu",               true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    birthplace(                 "místo narození",               "místo narození",                            "mistonarozeni",           false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    birthcouty(                 "okres narození",               "okres narození",                            "okresnarozeni",           false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    street(                     "ulice",                        "ulice",                                     "ulice",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    houseNumber(                "číslo domu",                   "číslo domu",                                "cislodomu",               true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    municipalityPart(           "část obce",                    "část obce",                                 "castobce",                false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    municipality(               "obec",                         "obec",                                      "obec",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    couty(                      "okres",                        "okres",                                     "okres",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    zip(                        "psč",                          "psč",                                       "psc",                     true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    country(                    "stát",                         "stát",                                      "stat",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    phone(                      "telefon",                      "telefon",                                   "telefon",                 false,  false,              true,       false,      false,  false,      true,       true,           true,           true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    post(                       "pošta",                        "pošta",                                     "posta",                   false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactstreet(              "kontaktní adresa: ulice",      "ulice",                                     "kontaktniadresaulice",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      street,             true,               false,      false,      false,  true,           true,          true,                   true         ),
    contacthouseNumber(         "kontaktní adresa: číslo domu", "číslo domu",                                "kontaktniadresacislodomu",true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      houseNumber,        true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactmunicipalityPart(    "kontaktní adresa: část obce",  "část obce",                                 "kontaktniadresacastobce", false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      municipalityPart,   true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactmunicipality(        "kontaktní adresa: obec",       "obec",                                      "kontaktniadresaobec",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      municipality,       true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactcouty(               "kontaktní adresa: okres",      "okres",                                     "kontaktniadresaokres",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      couty,              true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactzip(                 "kontaktní adresa: psč",        "psč",                                       "kontaktniadresapsc",      true,   false,              true,       false,      false,  false,      false,      false,          false,          false,      zip,                true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactcountry(             "kontaktní adresa: stát",       "stát",                                      "kontaktniadresastat",     false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      country,            true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactphone(               "kontaktní adresa: telefon",    "telefon",                                   "kontaktniadresatelefon",  false,  false,              true,       false,      false,  false,      false,      false,          true,           false,      phone,              true,               false,      false,      false,  true,           true,          true,                   true         ),
    contactpost(                "kontaktní adresa: pošta",      "pošta",                                     "kontaktniadresaposta",    false,  false,              true,       false,      false,  false,      false,      false,          false,          false,      post,               true,               false,      false,      false,  true,           true,          true,                   true         ),
    nameofHighSchool(           "název střední školy",          "název střední školy",                       "nazevstredniskoly",       false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    adresaofHighSchool(         "adresa střední školy",         "adresa střední školy",                      "adresastredniskoly",      false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    oborofHighSchool(           "obor střední školy",           "obor střední školy",                        "oborstredniskoly",        false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    jkov(                       "jkov",                         "jkov",                                      "jkov",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    kkov(                       "kkov",                         "kkov",                                      "kkov",                    false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    izo(                        "izo",                          "izo",                                       "izo",                     false,  false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    yearmaturity(               "rok maturity",                 "rok maturity",                              "rokmaturity",             true,   false,              true,       false,      false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    applicationstate(           "stav přihlášky",               "stav přihlášky",                            "stavprihlasky",           false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    tuition(                    "školné",                       "školné",                                    "skolne",                  false,  true,               true,       false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  true,           true,          true,                   true         ),
    semester(                   "semestr",                      "semestr",                                   "semestr",                 true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    stateofstudying(            "stav studia",                  "stav studia",                               "stavstudia",              false,  false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    group(                      "skupina",                      "skupina",                                   "skupina",                 true,   false,              false,      true,       false,  false,      false,      false,          false,          true,       null,               false,              false,      false,      false,  false,          false,         true,                   true         ),
    rights(                     "práva",                        "práva",                                     "rights",                  false,  true,               false,      false,      true,   false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  false,          false,         false,                  false        ),
    allColumns(                 "všechny sloupce",              "všechny sloupce",                           "vsechnysloupce",          false,  false,              false,      false,      false,  false,      false,      false,          false,          false,      null,               false,              false,      false,      false,  false,          false,         true,                   false        ),
    newpassword(                "nové heslo",                   "Zadejte své nové heslo:",                   "noveheslo",               false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,               true,               true,       false,      false,  false,          false,         false,                  false        ),
    newpasswordcheck(           "nové heslo kontrola",          "Zadejte pro kontrolu znovu své nové heslo:","kontrola",                false,  false,              false,      false,      false,  false,      false,      false,          false,          true,       null,               true,               true,       false,      false,  false,          false,         false,                  false        ),
    ;
    private String nameForUsers;
    private String nameForApplicants;
    private String nameRaw;
    private boolean number;
    private boolean autoFill;
    private boolean applicants;
    private boolean students;
    private boolean login;
    private boolean primaryKey;
    private boolean teachers;
    private boolean administrativa;
    private boolean phonenumber;
    private boolean obligatory;
    private Label copiedFrom;  //null, pokud není z ničeho kopírováno
    private boolean changableByUser;
    private boolean passwordChange; //zda je ve formuláři změna hesla, nemá nic společného s databází
    private boolean emailaddress;
    private boolean birthNumber;
    private boolean applicantsSpam;
    private boolean applicantsIPSpam;
    private boolean showToAdministrativa;
    private boolean changableByAdministrativa;
    
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
     * Only tables login, uchazeci and students are supported, because in uchazeci_spam and uchazeci_ipspam have same columns.
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
        if (temp.equals(SQLTables.students.getTable())) {
            output=isStudents();
        } else if (temp.equals(SQLTables.applicants.getTable())){
            output=isApplicants();
        } else if (temp.equals(SQLTables.login.getTable())){
            output=isLogin();
        } else if (temp.equals(SQLTables.applicants_spam.getTable())){
            output=isApplicantsSpam();
        } else if (temp.equals(SQLTables.applicants_ipspam.getTable())){
            output=isApplicantsIPSpam();
        } else if (temp.equals(SQLTables.teachers.getTable())){
            output=isTeachers();
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
            if (temp.equals(SQLTables.students.getTable())) {
                if (isStudents()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.applicants.getTable())){
                if (isApplicants()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.login.getTable())){
                if (isLogin()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.applicants_ipspam.getTable())){
                if (isApplicantsIPSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.applicants_spam.getTable())){
                if (isApplicantsSpam()) {
                    return true;
                }
            } else if (temp.equals(SQLTables.teachers.getTable())){
                if (isTeachers()) {
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
    
    public static Label getLabelFromStringInnameRaw(String i) throws IllegalArgumentException{ //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Label label : Label.values()) {
            if (label.getNameRaw().equals(i)) {
                return label;
            }
        }
        throw new IllegalArgumentException("No label found for "+i+".");
    }
}
