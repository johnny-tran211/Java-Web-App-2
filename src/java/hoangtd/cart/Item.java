/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.cart;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class Item implements Serializable{
    private String itemName;
    private int quantity;
    private int realQuantity;
    private double price;
    private double total;

    public Item(String itemName, int quantity, int realQuantity, double price, double total) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.realQuantity = realQuantity;
        this.price = price;
        this.total = total;
    }

    public int getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(int realQuantity) {
        this.realQuantity = realQuantity;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
