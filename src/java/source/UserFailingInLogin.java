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

    public UserFailingInLogin() {
        super("", "", Rights.NOT_LOGGED, "", "fail");
    }
    
    @Override
    public LoggedUser clear(){
        return new NotLoggedUser();
    }
}
