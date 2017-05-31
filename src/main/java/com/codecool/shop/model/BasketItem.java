package com.codecool.shop.model;

public class BasketItem extends BaseModel {
    private Product product;
    private Integer quantity;

    BasketItem(Product product, Integer quantity) {
        super("", "");
        this.setProduct(product);
        this.setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity > 0) this.quantity = quantity;
    }
}