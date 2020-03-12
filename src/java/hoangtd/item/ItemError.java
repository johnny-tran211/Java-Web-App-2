/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.item;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class ItemError implements Serializable{
    private String productNameLengthError;
    private String productNameDuplicateError;
    private String quantityLengthError;
    private String quantityValidationError;
    private String fileError;
    private String fileLengthError;
    private String descriptionError;
    private String priceLengthError;
    private String priceValidationError;
    private String categoryError;

    public ItemError() {
    }

    public String getProductNameDuplicateError() {
        return productNameDuplicateError;
    }

    public void setProductNameDuplicateError(String productNameDuplicateError) {
        this.productNameDuplicateError = productNameDuplicateError;
    }

    public String getFileLengthError() {
        return fileLengthError;
    }

    public void setFileLengthError(String fileLengthError) {
        this.fileLengthError = fileLengthError;
    }

    public String getPriceLengthError() {
        return priceLengthError;
    }

    public void setPriceLengthError(String priceLengthError) {
        this.priceLengthError = priceLengthError;
    }

    public String getPriceValidationError() {
        return priceValidationError;
    }

    public void setPriceValidationError(String priceValidationError) {
        this.priceValidationError = priceValidationError;
    }

    public String getProductNameLengthError() {
        return productNameLengthError;
    }

    public void setProductNameLengthError(String productNameLengthError) {
        this.productNameLengthError = productNameLengthError;
    }

    public String getQuantityLengthError() {
        return quantityLengthError;
    }

    public void setQuantityLengthError(String quantityLengthError) {
        this.quantityLengthError = quantityLengthError;
    }

    public String getQuantityValidationError() {
        return quantityValidationError;
    }

    public void setQuantityValidationError(String quantityValidationError) {
        this.quantityValidationError = quantityValidationError;
    }



    public String getFileError() {
        return fileError;
    }

    public void setFileError(String fileError) {
        this.fileError = fileError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }



    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }
    
}
