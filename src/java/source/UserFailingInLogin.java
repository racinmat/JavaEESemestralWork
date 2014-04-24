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
public class UserFailingInLogin extends LoggedUser{

    /**
     * Constructor defining all variables for user who failed in attempt to login.
     */
    public UserFailingInLogin() {
        super("", "", Rights.NOT_LOGGED, "", "fail", false);
    }
    
    /**
     * 
     * @return notLoggedUser, is used because for UserFailingInLogin is in jsp pages shown text telling him his attempt to login failed, after displaying once it should disappear, which is solved by using this method
     */
    @Override
    public LoggedUser clear(){
        return new NotLoggedUser();
    }
}
