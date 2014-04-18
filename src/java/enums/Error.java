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
public enum Error {
    noSQL(          "0",    "Omlouváme se, ale vyskytla se chyba v připojení na databázi. Administrátor již ví o chybě a pokusí se ji v co nejkratším čase opravit."),
    notKnownError(  "1",    "Vyskytla se nepředpokládaná chyba, administrátor o ní ví a pokouší se ji řešit."                                                       ),
    noRights(       "2",    "Bohužel nemáte práva k přístupu na tuto stránku, pokud máte pocit, že byste na tuto stránku měli mít přístup, přihlašte se nebo kontaktujte administrátora."),
    noDirectAccess( "3",    "Bohužel na tuto stránku není povolen přímý přístup."),
    ;
    private String numberAsString;
    private String text;

    private Error(String numberAsString, String text) {
        this.numberAsString = numberAsString;
        this.text = text;
    }

    public String getNumberInString() {
        return numberAsString;
    }

    public String getText() {
        return text;
    }
    
    public static Error getErrorFromNumberInString(String i){  //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Error a : Error.values()) {
            if (a.numberAsString.equals(i)) {
                return a;
            }
        }
        return notKnownError;
    }
    
}
