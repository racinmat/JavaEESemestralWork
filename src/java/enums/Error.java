package enums;

/**
 * Error enum, used to store every known and described kind of error and warning statements. Webpage chyba.jsp uses this enum to show error or warning message.
 * @author Azathoth
 */
public enum Error {
    
    /**
     * Shows up when something happenes with connection to SQL database.
     */
    NO_SQL(          "0",    "Omlouváme se, ale vyskytla se chyba v připojení na databázi. Administrátor již ví o chybě a pokusí se ji v co nejkratším čase opravit."),
    /**
     * Shows up when unknown error appears.
     */
    NOT_KNOWN_ERROR( "1",    "Vyskytla se nepředpokládaná chyba, administrátor o ní ví a pokouší se ji řešit."                                                       ),
    /**
     * Shows up when user does not have rights to access certain webpage.
     */
    NO_RIGHTS(       "2",    "Bohužel nemáte práva k přístupu na tuto stránku, pokud máte pocit, že byste na tuto stránku měli mít přístup, přihlašte se nebo kontaktujte administrátora."),
    /**
     * Shows up when user tries to access webpage which is not meant to be accessed directly.
     */
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
