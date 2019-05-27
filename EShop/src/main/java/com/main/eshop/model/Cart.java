
package com.main.eshop.model;

/**
 *
 * @author Eugenio
 */
public class Cart {
    private int id;
    private User user;

    /**
     * Constructor
     * @param id id Cart
     * @param user User
     */
    public Cart(int id, User user) {
        this.id = id;
        this.user = user;
    }

    
    // getter e setter
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
