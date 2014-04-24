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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azathoth
 */
public class SecurityCheck {
    
    private final LoggedUser user;
    private final boolean logged;
    private final int rightsValue;
    private final HttpServletRequest request;
    
    /**
     * Constructor of SecurityCheck, saves all info about user currently browsing pages from session variable.
     * @param request HttpServletRequest which enables using session variables
     */
    public SecurityCheck(HttpServletRequest request) {
        this.request=request;
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user")!=null) {
            this.user=(LoggedUser) session.getAttribute("user");
            this.logged = this.user.getLogged().equals("success");
            
        } else {
            this.user=new NotLoggedUser();
            this.logged=false;
        }
        this.rightsValue=this.user.getRights().getRightsValue();
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages is main admin, otherwise return false
     */
    public boolean isMainAdmin(){
        return logged&&rightsValue==0;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages is administrativa, otherwise return false
     */
    public boolean isAdministrativa(){
        return logged&&rightsValue==1;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages is pedagog, otherwise return false
     */
    public boolean isPedagog(){
        return logged&&rightsValue==2;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages is main student, otherwise return false
     */
    public boolean isStudent(){
        return logged&&rightsValue==3;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages is main applicant, otherwise return false
     */
    public boolean isApplicant(){
        return logged&&rightsValue==4;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages has at least main admin rights, otherwise return false
     */
    public boolean hasMainAdminRights(){
        return logged&&rightsValue<=0;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages has at least administrativa rights, otherwise return false
     */
    public boolean hasAdministrativaRights(){
        return logged&&rightsValue<=1;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages has at least pedagog rights, otherwise return false
     */
    public boolean hasPedagogRights(){
        return logged&&rightsValue<=2;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages has at least student rights, otherwise return false
     */
    public boolean hasStudentRights(){
        return logged&&rightsValue<=3;
    }
    
    /**
     * 
     * @return true if user who is currently browsing pages has at least applicant rights, otherwise return false
     */
    public boolean hasApplicantRights(){
        return logged&&rightsValue<=4;
    }

    /**
     * 
     * @return user currently browsing websites
     */
    public LoggedUser getUser() {
        return user;
    }

    /**
     * 
     * @return whether user is logged or not
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * 
     * @return numeric value of rights of user currently browsing websites
     */
    public int getRightsValue() {
        return rightsValue;
    }
    
    /**
     * Determines whether user can access page which called this method or not and if not, redirects user to another page.
     * @param rights which are minimally needed to access page which called this method
     * @param response needed for redirecting user to other pages
     */
    public void accesedTo(Rights rights, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        if ((!logged)&&session.getAttribute("redirect")==null) {   //kvůli tomu, že stránka padá při pokusu o několik přesměrování najednou, je zde ošetření, při přesměrování se do proměnné redirect dá hodnota
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo", "Error in redirecting to notLogged.jsp. "+ex.getMessage(), ex);
            }
        } else if (user.getRights().getRightsValue()>rights.getRightsValue()&&session.getAttribute("redirect")==null) {
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo", "Error in redirecting to chyba.jsp?error=2. "+ex.getMessage(), ex);
            }
        }
    }
    
    /**
     * Determines whether user can access page which called this method or not and if not, redirects user to another page.
     * @param rights which are the only allowed to access page which called this method
     * @param response needed for redirecting user to other pages
     */
    public void accesedOnlyTo(Rights rights, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        if (user==null&&session.getAttribute("redirect")==null) {
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("notLogged.jsp");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo", "Error in redirecting to notLogged.jsp. "+ex.getMessage(), ex);
            }
        } else if (user.getRights().getRightsValue()!=rights.getRightsValue()&&session.getAttribute("redirect")==null) {
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=2");
            } catch (IOException ex) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "accesedTo", "Error in redirecting to chyba.jsp?error=2. "+ex.getMessage(), ex);
            }
        }
    }
    
    /**
     * Controls if page which called this method is accessed by redirect or directly and in case of direct access user is redirected to another page
     * @param response needed for redirecting user to other pages
     */
    public void noDirectAccess(HttpServletResponse response){
        HttpSession session = request.getSession(true);
        try {
            URI uri= new URI(request.getHeader("referer"));
            String refererURI = uri.getPath();
            if (refererURI==null) {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=3");
            }
        } catch (URISyntaxException|IOException|NullPointerException ex) {      //nullpointer exception nastává při přímém přístupu
            try {
                session.setAttribute("redirect", "true");
                response.sendRedirect("chyba.jsp?error=3");
            } catch (IOException ex1) {
                MyLogger.getLogger().logp(Level.SEVERE, this.getClass().getName(), "noDirectAccess", "Error in redirecting to chyba.jsp?error=3. "+ex1.getMessage(), ex1);
            }
        }
    }
}
