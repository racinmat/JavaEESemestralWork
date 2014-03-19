/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azathoth
 */
public class SecurityCheck {
    
    private int rights;
    private String loggedString;
    private boolean logged;

    public SecurityCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("logged")!=null) {
            loggedString=(String) session.getAttribute("logged"); 
            if (session.getAttribute("rights")!=null) {
                Integer temp=(Integer) session.getAttribute("rights");
                rights=temp;  
            }
            if (loggedString.equals("success")) {
                logged=true;
            }
        }
        
    }
    
    public boolean isMainAdmin(){
        return logged&&rights<=0;
    }
    
    public boolean isAdministrativa(){
        return logged&&rights<=1;
    }
    
    public boolean isPedagog(){
        return logged&&rights<=2;
    }
    
    public boolean isStudent(){
        return logged&&rights<=3;
    }
    
    public boolean isUchazec(){
        return logged&&rights<=4;
    }
}
