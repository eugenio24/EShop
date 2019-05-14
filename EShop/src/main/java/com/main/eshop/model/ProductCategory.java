
package com.main.eshop.model;

/**
 *
 * @author eugenio.ferrari
 */
public class ProductCategory {
    
    private String name;
    private boolean isMainCategory;
    
    private ProductCategory mainCategory = null;
    
    /**
     * Constructor, per categorie con categoria principale    
     * @param name Name     
     * @param mainCategory MainCategory
     */
    public ProductCategory(String name, ProductCategory mainCategory){
        this.name = name;
        this.isMainCategory = true;
        this.mainCategory = mainCategory;
    }
    
    /**
     * Constructor, per categorie principali
     * @param name Name 
     */
    public ProductCategory(String name){
        this.name = name;
        this.isMainCategory = true;
    }
    
    // GETTER SETTER

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

    public ProductCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(ProductCategory mainCategory) {
        this.mainCategory = mainCategory;
    }
    
}
