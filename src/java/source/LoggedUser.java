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
public class LoggedUser {
    private final String name;
    private final String lastName;
    private final Rights rights;
    private final String username;
    private final String logged;

    /**
     * Creates new instance of Logged User, used as session variable holding more variables in itself.
     * @param name Name of logged user
     * @param lastName Last name of logged user
     * @param rights Rights of logged user
     * @param username username of logged user
     * @param logged determines whether user is logged succesfully or not
     */
    public LoggedUser(String name, String lastName, Rights rights, String username, String logged) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
        this.username = username;
        this.logged = logged;
    }

    /**
     * 
     * @return 
     */
    public String getLogged() {
        return logged;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @return 
     */
    public Rights getRights() {
        return rights;
    }

    /**
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @return hash of this object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 29 * hash + (this.rights != null ? this.rights.hashCode() : 0);
        hash = 29 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param obj object you want to compare with this
     * @return true if objects are same, otherwise return false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoggedUser other = (LoggedUser) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if (this.rights != other.rights) {
            return false;
        }
        return !((this.username == null) ? (other.username != null) : !this.username.equals(other.username));
    }
    
    /**
     * Used for turning UserFailedInLogin to NotLoggedUser
     * @return this, returns new NotLoggedUser when this is instance of UserFailingInLogin
     */
    public LoggedUser clear(){
        return this;
    }
}
