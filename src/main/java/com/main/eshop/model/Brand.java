
package com.main.eshop.model;

/**
 *
 * @author Eugenio
 */
public class Brand {
    
    private String name;

    /**
     * Constructor
     * @param name Name of the Brand
     */
    public Brand(String name) {
        this.name = name;
    }

    // GETTER E SETTER
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}
