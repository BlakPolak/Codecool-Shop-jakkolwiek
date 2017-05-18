package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(Product product, Integer quantity) {
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

    public void remove(BasketItem item, Integer quantity) {
        item.setQuantity(quantity);
        items.remove(item);
    }


    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer count = 0;
        for(BasketItem item: this.getItems()) {
            count += item.getQuantity();
        }
        return count;
    }

    public BasketItem getBasketItemById(Integer id) {
        for(BasketItem basketItem : this.getItems()) {
            if(basketItem.getId() == id) {
                basketItem.setId(id);
                return basketItem;
            }
        }
        return null;
    }
}