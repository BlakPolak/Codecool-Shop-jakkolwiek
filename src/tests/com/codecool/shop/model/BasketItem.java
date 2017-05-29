package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketItemTest {

    private Product product;
    private BasketItem basketItem;

    @BeforeEach
    public void createBasketItem() {
        product = new Product(1, "Nice product", 10, "PLN", "description", null, null );
        basketItem = new BasketItem(product, 1);
    }

    @Test
    public void testIsQuantityLTzero() {

        this.basketItem.setQuantity(-10);
        assertFalse(this.basketItem.getQuantity()<0);
    }

    @Test
    public void testSetAndGetProduct() {

        product = new Product(2, "Nice product2", 110, "PLN", "description", null, null );
        this.basketItem.setProduct(product);
        assertEquals(this.basketItem.getProduct(), product);
    }

    @Test
    public void testSetAndGetQuantity() {

        this.basketItem.setQuantity(10);
        Integer qty = 10;
        assertEquals(qty, this.basketItem.getQuantity());
    }

}