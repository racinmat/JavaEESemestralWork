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

    public NotLoggedUser() {
        super("", "", Rights.notLogged, "", "fail");
    }
    
}