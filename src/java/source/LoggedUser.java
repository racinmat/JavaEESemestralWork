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
    private String logged;

    public void setLogged(String logged) {
        this.logged = logged;
    }

    public LoggedUser(String name, String lastName, Rights rights, String username, String logged) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
        this.username = username;
        this.logged = logged;
    }

    public String getLogged() {
        return logged;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Rights getRights() {
        return rights;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 29 * hash + (this.rights != null ? this.rights.hashCode() : 0);
        hash = 29 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

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
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }
}
