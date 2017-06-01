package com.codecool.shop.model;

public class Product extends BaseModel {

    private Float defaultPrice;
    private String defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(Integer id, String name, Float defaultPrice, String currencyString, String description,
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

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency;
    }

    public void setPrice(Float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = currency;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Product product = (Product) other;
        if (defaultPrice != null ? !defaultPrice.equals(product.defaultPrice) : product.defaultPrice != null)
            return false;
        if (defaultCurrency != null ? !defaultCurrency.equals(product.defaultCurrency) : product.defaultCurrency != null)
            return false;
        if (productCategory != null ? !productCategory.equals(product.productCategory) : product.productCategory != null)
            return false;
        return supplier != null ? supplier.equals(product.supplier) : product.supplier == null;
    }
}
