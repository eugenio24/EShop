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
    private String descrizione;
    private double prezzo;
    private String categoria;
    private String brand;
    private String urlImmagine;

    public Product(int id, String name, String descrizione, double prezzo, String categoria, String brand, String urlImmagine) {
        this.id = id;
        this.name = name;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categoria = categoria;
        this.brand = brand;
        this.urlImmagine = urlImmagine;
    }

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    
    
    
    
}
