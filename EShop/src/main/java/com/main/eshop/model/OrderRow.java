
package com.main.eshop.model;

/**
 *
 * @author Eugenio
 */
public class OrderRow {
    private int id;
    private OrderHead orderHead;
    private Product product;
    private int quantity;
    private double price;

    public OrderRow(int id, OrderHead orderHead, Product product, int quantity, double price) {
        this.id = id;
        this.orderHead = orderHead;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderHead getOrderHead() {
        return orderHead;
    }

    public void setOrderHead(OrderHead orderHead) {
        this.orderHead = orderHead;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
