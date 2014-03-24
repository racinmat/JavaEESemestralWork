/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

/**
 *
 * @author Azathoth
 */
public class Label {
    private static final String[] label={
        "uživatelské jméno",
        "jméno",
        "příjmení",
        "hash hesla",
        "studijní program",
        "studijní obor",
        "pohlaví",
        "státní příslušnost",
        "rodinný stav",
        "email",
        "den narození",
        "měsíc narození",
        "rok narození",
        "číslo OP",
        "rodné číslo",
        "číslo pasu",
        "místo narození",
        "okres narození",
        "ulice",
        "číslo domu",
        "část obce",
        "obec",
        "okres",
        "psč",
        "stát",
        "telefon",
        "pošta",
        "kontaktní adresa: ulice",
        "kontaktní adresa: číslo domu",
        "kontaktní adresa: část obce",
        "kontaktní adresa: obec",
        "kontaktní adresa: okres",
        "kontaktní adresa: psč",
        "kontaktní adresa: stát",
        "kontaktní adresa: telefon",
        "kontaktní adresa: pošta",
        "název střední školy",
        "adresa střední školy",
        "obor střední školy",
        "jkov",
        "kkov",
        "izo",
        "rok maturity",
        "mobilní telefon",
        "stav přihlášky",
        "školné",
    };
    
    private static final String[] labelApplicantForm={
        "uživatelské jméno",
        "jméno",
        "příjmení",
        "hash hesla",
        "studijní program",
        "studijní obor",
        "pohlaví",
        "státní příslušnost",
        "rodinný stav",
        "email",
        "den narození",
        "měsíc narození",
        "rok narození",
        "číslo OP",
        "rodné číslo",
        "číslo pasu",
        "místo narození",
        "okres narození",
        "ulice",
        "číslo domu",
        "část obce",
        "obec",
        "okres",
        "psč",
        "stát",
        "telefon",
        "pošta",
        "ulice",
        "číslo domu",
        "část obce",
        "obec",
        "okres",
        "psč",
        "stát",
        "telefon",
        "pošta",
        "název střední školy",
        "adresa střední školy",
        "obor střední školy",
        "jkov",
        "kkov",
        "izo",
        "rok maturity",
        "mobilní telefon",
        "stav přihlášky",
        "školné",
    };
    
    private static final String[] labelRaw={
        "uzivatelskejmeno",                                                     //0
        "jmeno",                                                                //1
        "prijmeni",                                                             //2
        "hashhesla",                                                            //3
        "studijniprogram",                                                      //4
        "studijniobor",                                                         //5
        "pohlavi",                                                              //6
        "statniprislusnost",                                                    //7
        "rodinnystav",                                                          //8
        "email",                                                                //9
        "dennarozeni",                                                          //10
        "mesicnarozeni",                                                        //11
        "roknarozeni",                                                          //12
        "cisloOP",                                                              //13
        "rodnecislo",                                                           //14
        "cislopasu",                                                            //15
        "mistonarozeni",                                                        //16
        "okresnarozeni",                                                        //17
        "ulice",                                                                //18
        "cislodomu",                                                            //19
        "castobce",                                                             //20
        "obec",                                                                 //21
        "okres",                                                                //22
        "psc",                                                                  //23
        "stat",                                                                 //24
        "telefon",                                                              //25
        "posta",                                                                //26
        "kontaktniadresaulice",                                                 //27
        "kontaktniadresacislodomu",                                             //28
        "kontaktniadresacastobce",                                              //29
        "kontaktniadresaobec",                                                  //30
        "kontaktniadresaokres",                                                 //31
        "kontaktniadresapsc",                                                   //32
        "kontaktniadresastat",                                                  //33
        "kontaktniadresatelefon",                                               //34
        "kontaktniadresaposta",                                                 //35
        "nazevstredniskoly",                                                    //36
        "adresastredniskoly",                                                   //37
        "oborstredniskoly",                                                     //38
        "jkov",                                                                 //39
        "kkov",                                                                 //40
        "izo",                                                                  //41
        "rokmaturity",                                                          //42
        "mobilnitelefon",                                                       //43
        "stavprihlasky",                                                        //44
        "skolne",                                                               //45
    };
    
    private static final String[] labelStudent={
        "uživatelské jméno",                                                    //0
        "jméno",                                                                //1
        "příjmení",                                                             //2
        "semestr",                                                              //3
        "stav studia",                                                          //4
        "skupina",                                                              //5
    };
    
    private static final String[] labelStudentRaw={
        "uzivatelskejmeno",                                                     //0
        "jmeno",                                                                //1
        "prijmeni",                                                             //2
        "semestr",                                                              //3
        "stavstudia",                                                           //4
        "skupina",                                                              //5
    };

    public static String[] getLabelStudent() {
        return labelStudent;
    }

    public static String[] getLabelStudentRaw() {
        return labelStudentRaw;
    }

    public static String[] getLabelApplicantForm() {
        return labelApplicantForm;
    }

    public static String[] getLabel() {
        return label;
    }

    public static String[] getLabelRaw() {
        return labelRaw;
    }
    
    public static int getLength() {
        return label.length;
    }
    
    public static String getLabelOnPosition(int i){
        return label[i];
    }
    
    public static String getLabelRawOnPosition(int i){
        return labelRaw[i];
    }
}
