package source;

import enums.Rights;

/**
 * Extends LoggedUser, instance of this class is created when user fails in an
 * attempt to login. When this class is saved in user session variable, beneath
 * login window is text announcing the attempt to login failed and after showing
 * this message, the clear function is called and UserFailingInLogin is replaced
 * by an instance of NotLoggedUser.
 *
 * @author Azathoth
 */
public class UserFailingInLogin extends LoggedUser {

    /**
     * Constructor defining all variables for user who failed in attempt to
     * login.
     */
    public UserFailingInLogin() {
        super("", "", Rights.NOT_LOGGED, "");
    }

    /**
     *
     * @return notLoggedUser, is used because for UserFailingInLogin is in jsp
     * pages shown text telling him his attempt to login failed, after
     * displaying once it should disappear, which is solved by using this method
     */
    @Override
    public LoggedUser clear() {
        return new NotLoggedUser();
    }

    /**
     *
     * @return true
     */
    @Override
    public boolean failedInLogin() {
        return true;
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
