/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.updateHistory;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class UpdateHistoryDTO implements Serializable{
    private String email;
    private String itemName;
    private Timestamp updateDate;
    private String status;

    public UpdateHistoryDTO(String email, String itemName, Timestamp updateDate, String status) {
        this.email = email;
        this.itemName = itemName;
        this.updateDate = updateDate;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
