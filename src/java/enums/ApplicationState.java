package enums;

/**
 * This enum is used for determining possible states of Application from Label enum and default state when creating new applicant. CHanging states must be done manually on webpage for changing of applicants data.
 * @author Azathoth
 */
public enum ApplicationState {

    /**
     * Accepted, is set when applicant is accepted to school and becomes a student.
     */
    ACCEPTED(           "přijat",                            false,  "prijat"               ),
    /**
     * Paid fee for school application, is set when fee is paid.
     */
    PAID_FEE(           "zaplacen registrační poplatek",     false,  "zaplacenpoplatek"     ),
    /**
     * Not paid fee for school application, is set by administration after checking the content of electronic application.
     */
    NOT_PAID_FEE(       "nezaplacen registrační poplatek",   false,  "nezaplacenpoplatek"   ),
    /**
     * Not checked by administration. Default state set during registration of new applicant.
     */
    NOT_CHECKED(        "nezevidován administrativou",       true,   "nezevidovan"          ),
    ;
    private final String name;
    private final boolean defaultboolean;
    private final String nameRaw;

    private ApplicationState(String name, boolean defaultboolean, String nameRaw) {
        this.name = name;
        this.defaultboolean = defaultboolean;
        this.nameRaw = nameRaw;
    }
    
    /**
     * Returns raw name of ApplicationState used in SQL database.
     * @return String with raw name of state.
     */
    public String getNameRaw() {
        return nameRaw;
    }
    
    /**
     * Returns name of ApplicationState for users.
     * @return String with name of state for users.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Determines whether chosen state is default or not.
     * @return true if this state is autofilled when new user is added to database. Otherwise, returns false.
     */
    public boolean isDefaultBoolean() {
        return defaultboolean;
    }
    
    /**
     * Returns default ApplicationState.
     * @return ApplicationState which has true in defaultboolean variable.
     * @throws NullPointerException when no default ApplicationState is found.
     */
    public static ApplicationState getDefaultBoolean() throws NullPointerException{
        for (ApplicationState stav : ApplicationState.values()) {
            if (stav.isDefaultBoolean()) {
                return stav;
            }
        }
        throw new NullPointerException("No default row in this enum.");
    }
}
