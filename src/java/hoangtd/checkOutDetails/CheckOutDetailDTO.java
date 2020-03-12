/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.checkOutDetails;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class CheckOutDetailDTO implements Serializable{
    private String itemName;
    private int quantity;
    private double price;
    private double total;

    public CheckOutDetailDTO(String itemName, int quantity, double price, double total) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
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
