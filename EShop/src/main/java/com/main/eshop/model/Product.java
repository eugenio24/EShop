
package com.main.eshop.model;

import java.util.ArrayList;

/**
 *
 * @author gianni.benigni
 */
public class Product {
    
    private int id;
    private String name;
    private String desc;
    private double price;    
    private ProductCategory category;
    private Brand brand;
    private ArrayList<String> images;

    /**
     * Constructor
     * @param id ID 
     * @param name Name
     * @param desc Descrizione
     * @param price Prezzo
     * @param category ProductCategory
     * @param brand Brand
     * @param images images 
     */
    public Product(int id, String name, String desc, double price, ProductCategory category, Brand brand, ArrayList<String> images) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.images = images;
    }
    
    /**
     * Metodo che ritorna la stinga con tutte le immagini
     * @return Stringa da inserire sul db
     */
    public String listProductImages(){
        String str = "";
        
        str = images.stream().map((image) -> image.split("/")).map((imgArray) -> imgArray[imgArray.length-1]+";").reduce(str, String::concat);        
        if(str != ""){
            str = str.substring(0, str.length()-2);            
        }
        
        return str;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

}
