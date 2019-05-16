
package com.main.eshop.model;

/**
 *
 * @author eugenio.ferrari
 */
public class ProductCategory {
    
    private int id;  // is 0 if is a new category to add
    private String name;
    private boolean isMainCategory;
    
    private int mainCategoryId = 0;

    /**
     * Constructor for main category
     * @param id ID
     * @param name Name
     */
    public ProductCategory(int id, String name){
        this.id = id;
        this.name = name;
        
        this.isMainCategory = true;
    }   
        
    /**
     * Constructor
     * @param id ID 
     * @param name Name
     * @param mainCategoryId Categoria principale
     */
    public ProductCategory(int id, String name, int mainCategoryId){
        this.id = id;
        this.name = name;
        this.mainCategoryId = mainCategoryId;
        
        this.isMainCategory = mainCategoryId == 0;
    }        
    
    // GETTER SETTER
    
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

    public boolean isIsMainCategory() {
        return isMainCategory;
    }

    public void setIsMainCategory(boolean isMainCategory) {
        this.isMainCategory = isMainCategory;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }        
}
