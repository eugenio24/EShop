
package com.main.eshop.model;

/**
 *
 * @author Eugenio
 */
public class Brand {
    private int id; // is 0 if is a new category to add
    private String name;

    /**
     * Constructor     
     * @param id Id 
     * @param name Name of the Brand
     */
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // GETTER E SETTER
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {    
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}