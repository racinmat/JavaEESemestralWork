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
    NO_SQL(          "0",    "Omlouváme se, ale vyskytla se chyba v připojení na databázi. Administrátor již ví o chybě a pokusí se ji v co nejkratším čase opravit."),
    NOT_KNOWN_ERROR( "1",    "Vyskytla se nepředpokládaná chyba, administrátor o ní ví a pokouší se ji řešit."                                                       ),
    NO_RIGHTS(       "2",    "Bohužel nemáte práva k přístupu na tuto stránku, pokud máte pocit, že byste na tuto stránku měli mít přístup, přihlašte se nebo kontaktujte administrátora."),
    NO_DIRECT_ACCESS("3",    "Bohužel na tuto stránku není povolen přímý přístup."),
    ;
    private final String numberAsString;
    private final String text;

    private Error(String numberAsString, String text) {
        this.numberAsString = numberAsString;
        this.text = text;
    }

    /**
     * Returns position of Error in enum. Number is unique for every error.
     * @return number unique to error, numbered ascending from beginning of the enum.
     */
    public String getNumberInString() {
        return numberAsString;
    }

    /**
     * Returns text shown to user when Error occurs.
     * @return text, which describes type of error in simple way understandable to BFU.
     */
    public String getText() {
        return text;
    }
    
    /**
     * Returns error with given number, returns i-th error from the beginning of the enum.
     * @param i determines which error should be returned, can be number from 0 to 3.
     * @return Error with corresponding string in numberAsString variable. Returns Error.NOT_KNOWN_ERROR when no suitable error is found.
     */
    public static Error getErrorFromNumberInString(String i){  //statická metoda, aby mohla být volána předtím, než je vytvořen objekt, který tato metoda vrátí
        for (Error a : Error.values()) {
            if (a.numberAsString.equals(i)) {
                return a;
            }
        }
        return NOT_KNOWN_ERROR;
    }
}
