/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.model;

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
    private String urlImage;

    /**
     * Constructor
     * @param id ID 
     * @param name Name
     * @param desc Descrizione
     * @param price Prezzo
     * @param category ProductCategory
     * @param brand Brand
     * @param urlImage UrlImage 
     */
    public Product(int id, String name, String desc, double price, ProductCategory category, Brand brand, String urlImage) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.urlImage = urlImage;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
