/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Rights;

/**
 *
 * @author Azathoth
 */
public class NotLoggedUser extends LoggedUser{

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
