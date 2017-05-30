package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(Product product, Integer quantity) {
        if (quantity < 1) throw new IllegalArgumentException();
        if (product.equals(null)) throw new IllegalArgumentException();
        boolean productExists = false;
        for(BasketItem item: this.getItems()) {
            if(item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
            }
        }

        if(!productExists) {
            BasketItem item = new BasketItem(product, quantity);
            item.setId(product.getId());
            this.getItems().add(item);
        }
    }

    public void remove(BasketItem item) {
        if (!items.contains(item)) throw new IllegalArgumentException();
        items.remove(item);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public BasketItem getBasketItemByProductId(Integer id) {
        if (id == null) throw new IllegalArgumentException();
        for(BasketItem basketItem : this.getItems()) {
            if(basketItem.getId().equals(id)) {
                basketItem.setId(id);
                return basketItem;
            }
        }
        return null;
    }
}