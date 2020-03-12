/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.item;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class ItemDTO implements Serializable {

    private int id;
    private String itemName;
    private String status;
    private int quantity;
    private Timestamp createDate;
    private String base64Image;
    private String description;
    private double price;
    private String category;

    public ItemDTO(int id, String itemName, String status, int quantity, Timestamp createDate, String base64Image, String description, double price, String category) {
        this.id = id;
        this.itemName = itemName;
        this.status = status;
        this.quantity = quantity;
        this.createDate = createDate;
        this.base64Image = base64Image;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public ItemDTO(int id, String itemName, int quantity, String base64Image, double price) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.base64Image = base64Image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
