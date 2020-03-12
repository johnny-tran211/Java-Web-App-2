/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class CartObj implements Serializable {

    private String customerName;
    private Map<Integer, Item> lists;

    public CartObj() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<Integer, Item> getLists() {
        return lists;
    }

    public boolean addToCart(int id, String itemName, int quantity, double price, int realQuantity) {
        double total;
        if (this.lists == null) {
            this.lists = new HashMap<>();
        }
        if (this.lists.containsKey(id)) {
            if ((this.lists.get(id).getQuantity() + quantity) > realQuantity) {
                return false;
            }
            this.lists.get(id).setQuantity(this.lists.get(id).getQuantity() + quantity);
            total = this.lists.get(id).getQuantity() * this.lists.get(id).getPrice();
            this.lists.get(id).setTotal(total);
            lists.put(id, this.lists.get(id));
        } else {
            if (quantity > realQuantity) {
                return false;
            }
            total = quantity * price;
            lists.put(id, new Item(itemName, quantity, realQuantity, price, total));
        }
        return true;
    }

    public void deleteItem(int id) {
        if (this.lists == null) {
            return;
        }
        if (this.lists.containsKey(id)) {
            this.lists.remove(id);
        }
        if (lists.isEmpty()) {
            lists = null;
        }
    }

}
