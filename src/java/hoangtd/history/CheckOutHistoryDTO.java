/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.history;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class CheckOutHistoryDTO implements Serializable{
    private int id;
    private String email;
    private Timestamp checkOutTime;
    private String paymentMethod;
    private double total;

    public CheckOutHistoryDTO(int id, String email, Timestamp checkOutTime, String paymentMethod, double total) {
        this.id = id;
        this.email = email;
        this.checkOutTime = checkOutTime;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
