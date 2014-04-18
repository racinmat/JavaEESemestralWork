/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Rights;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        HttpSession session = request.getSession(true);
        if (user==null&&session.getAttribute("redirect")==null) {   //kvůli tomu, že stránka padá při pokusu o několik přesměrování najednou, je zde ošetření, při přesměrování se do proměnné redirect dá hodnota
            try {
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo method", "Error in redirecting to notLogged.jsp. "+ex.getMessage(), ex);
            }
        } else if (user.getRights().getRightsValue()>rights.getRightsValue()&&session.getAttribute("redirect")==null) {
            try {
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo method", "Error in redirecting to chyba.jsp?error=2. "+ex.getMessage(), ex);
            }
        }
    }
    
    public void accesedOnlyTo(Rights rights, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        if (user==null&&session.getAttribute("redirect")==null) {
            try {
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo method", "Error in redirecting to notLogged.jsp. "+ex.getMessage(), ex);
            }
        } else if (user.getRights().getRightsValue()!=rights.getRightsValue()&&session.getAttribute("redirect")==null) {
            try {
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo method", "Error in redirecting to chyba.jsp?error=2. "+ex.getMessage(), ex);
            }
        }
    }
    
    public void noDirectAccess(HttpServletResponse response){
        HttpSession session = request.getSession(true);
        try {
            String refererURI=null;
            URI uri= new URI(request.getHeader("referer"));
            refererURI = uri.getPath();
            if (refererURI==null) {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=3");
            }
        } catch (URISyntaxException|IOException|NullPointerException ex) {
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=3");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "noDirectAccess method", "Error in redirecting to chyba.jsp?error=3. "+ex1.getMessage(), ex1);
            }
        }
    }
}
