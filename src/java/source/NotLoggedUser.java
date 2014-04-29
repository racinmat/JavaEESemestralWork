package source;

import enums.Rights;

/**
 * Extends Logged User, instance of this class is created during first checking
 * of Rights to acces some pages which are not public and then it is saved as
 * session variable and used during whole session when user browses website,
 * until sucessful login when is replaced by instance of LoggedUser with proper
 * Rights.
 *
 * @author Azathoth
 */
public class NotLoggedUser extends LoggedUser {

    /**
     * Constructor which sets all data about user who is not logged.
     */
    public NotLoggedUser() {
        super("", "", Rights.NOT_LOGGED, "");
    }

    /**
     *
     * @return false
     */
    @Override
    public boolean isLogged() {
        return false;
    }

}
