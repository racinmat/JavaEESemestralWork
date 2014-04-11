/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Rights;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azathoth
 */
public class SecurityCheck {
    
    private LoggedUser user;
    private boolean logged;
    private int rightsValue;
    private HttpServletRequest request;
    
    public SecurityCheck(HttpServletRequest request) {
        this.request=request;
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user")!=null) {
            this.user=(LoggedUser) session.getAttribute("user");
            this.rightsValue=this.user.getRights().getRightsValue();
        } else {
            this.user=new NotLoggedUser();
            this.rightsValue=this.user.getRights().getRightsValue();
            this.logged=false;
        }
        if (this.user!=null&&this.user.getLogged().equals("success")) {
            this.logged=true;
        }
    }
    
    public boolean isMainAdmin(){
        return logged&&rightsValue==0;
    }
    
    public boolean isAdministrativa(){
        return logged&&rightsValue==1;
    }
    
    public boolean isPedagog(){
        return logged&&rightsValue==2;
    }
    
    public boolean isStudent(){
        return logged&&rightsValue==3;
    }
    
    public boolean isUchazec(){
        return logged&&rightsValue==4;
    }
    
    public boolean hasMainAdminRights(){
        return logged&&rightsValue<=0;
    }
    
    public boolean hasAdministrativaRights(){
        return logged&&rightsValue<=1;
    }
    
    public boolean hasPedagogRights(){
        return logged&&rightsValue<=2;
    }
    
    public boolean hasStudentRights(){
        return logged&&rightsValue<=3;
    }
    
    public boolean hasUchazecRights(){
        return logged&&rightsValue<=4;
    }

    public LoggedUser getUser() {
        return user;
    }

    public boolean isLogged() {
        return logged;
    }

    public int getRightsValue() {
        return rightsValue;
    }
    
    public void accesedTo(Rights rights, HttpServletResponse response){
        if (user==null) {
            try {
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SecurityCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (user.getRights().getRightsValue()>rights.getRightsValue()) {
            try {
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                Logger.getLogger(SecurityCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void accesedOnlyTo(Rights rights, HttpServletResponse response){
        if (user==null) {
            try {
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SecurityCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (user.getRights().getRightsValue()!=rights.getRightsValue()) {
            try {
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                Logger.getLogger(SecurityCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
