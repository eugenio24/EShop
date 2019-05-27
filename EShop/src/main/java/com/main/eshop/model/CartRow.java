
package com.main.eshop.model;

/**
 *
 * @author Eugenio
 */
public class CartRow {
    private int id;
    private Cart cart;
    private Product product;
    private int quantity;

    /**
     * Constructor
     * @param id id 
     * @param cart Cart
     * @param product Product
     * @param quantity Quantity of the product
     */
    public CartRow(int id, Cart cart, Product product, int quantity) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    
    // getter e setter
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
