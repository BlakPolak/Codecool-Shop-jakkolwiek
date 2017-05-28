package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(Integer id, String name, float defaultPrice, String currencyString, String description,
                   ProductCategory productCategory, Supplier supplier) {
        super(id, name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    private void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    private void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    private void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}