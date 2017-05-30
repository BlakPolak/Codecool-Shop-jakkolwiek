package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BasketTest {

    private Product product;
    private Basket basket;

    @BeforeEach
    public void getProduct() {
        product = new Product(1, "a", 0f,"PLN",
                null, null, null);
    }

    @Test
    public void testAddBasketItemIfNotInBasket() {
        BasketItem basketItem = new BasketItem(this.product, 1);
        Basket basket = new Basket();
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem);
        basket.add(this.product, 1);
        assertEquals(basketItems.get(0).getProduct(),
                basket.getItems().get(0).getProduct());
        assertEquals(basketItems.get(0).getQuantity(),
                basket.getItems().get(0).getQuantity());
    }

    @Test
    public void testAddBasketItemIfItemInBasket() {
        BasketItem basketItem = new BasketItem(this.product, 1);
        Basket basket = new Basket();
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem);
        basket.add(this.product, 1);
        basket.add(this.product, 1);
        assertEquals(1, basket.getItems().size());
    }

    @Test
    public void testRemoveBasketItemIfItemInBasket() {
        Basket basket = new Basket();
        basket.add(this.product, 2);
        BasketItem basketItem = basket.getItems().get(0);
        basket.remove(basketItem);
        assertEquals(0, basket.getItems().size());
    }

    @Test
    public void testRemoveBasketItemIfItemNotInBasket() {
        Basket basket = new Basket();
        BasketItem basketItem = new BasketItem(this.product, 1);
        assertThrows(IllegalArgumentException.class, ()-> {
            basket.remove(basketItem);
        });
    }

    @Test
    public void testFindBasketItemByIdIfItemInBasket() {
        Basket basket = new Basket();
        basket.add(this.product, 2);
        assertEquals(1, (int) basket.getBasketItemById(1).getProduct().getId());
    }

    @Test
    public void testFindBasketItemByIdIfNotInBasket() {
        Basket basket = new Basket();
        basket.add(this.product, 2);
        assertEquals(null, basket.getBasketItemById(2));
    }

    @Test
    public void testFindBasketItemIfBasketEmpty() {
        Basket basket = new Basket();
        assertEquals(null, basket.getBasketItemById(2));
    }
}

