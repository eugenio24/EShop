
package com.main.eshop.model;

import com.main.eshop.util.enums.RegistrationType;
import java.sql.Timestamp;

/**
 *
 * @author Eugenio
 */
public class User {
    
    private int id;
    private String email;
    private String name;
    private String surname;
    private RegistrationType registrationType;
    private Timestamp registrationDate;
    private boolean isAdmin;
    
    // opzionali in base alla registrazione;
    private String username = null;
    private String externalId = null;

    /**
     * Constructor
     * @param id id
     * @param email Email
     * @param name Name
     * @param surname Surname
     * @param registrationDate Registration Date 
     * @param identificator Username or externalId (depends on registration type)
     * @param registrationType Registration Type
     * @param isAdmin is admin user
     */
    public User(int id, String email, String name, String surname, Timestamp registrationDate, String identificator, RegistrationType registrationType, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.registrationType = registrationType;
        this.registrationDate = registrationDate;
        this.isAdmin = isAdmin;
        
        if(registrationType == RegistrationType.NATIVE)
            this.username = identificator;
        else
            this.externalId = identificator;
    }
    
    // GETTER AND SETTER
    
    public int getId() {    
        return id;
    }

    public void setId(int id) {    
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    /**
     * ToString
     * @return 
     */
    @Override
    public String toString() {
        return "User{" + "email=" + email + ", name=" + name + ", surname=" + surname + ", registrationType=" + registrationType + ", registrationDate=" + registrationDate + ", isAdmin=" + isAdmin + ", username=" + username + ", externalId=" + externalId + '}';
    }
    
}
