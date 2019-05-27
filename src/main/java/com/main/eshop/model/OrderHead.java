
package com.main.eshop.model;

import java.sql.Timestamp;

/**
 *
 * @author Eugenio
 */
public class OrderHead {
    
    private int orderNumber;
    private User user;
    private Timestamp orderDate;
    private double totalPrice;
    private String shippingAddress;

    public OrderHead(int orderNumber, User user, Timestamp orderDate, double totalPrice, String shippingAddress) {
        this.orderNumber = orderNumber;
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    
    
}
