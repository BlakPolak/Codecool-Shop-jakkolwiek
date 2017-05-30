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

    @BeforeEach
    public void getBasket() {
        basket = new Basket();
    }

    @Test
    public void testAddBasketItemIfNotInBasket() {
        BasketItem basketItem = new BasketItem(this.product, 1);
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem);
        basket.add(this.product, 1);
        assertEquals(basketItems.get(0).getProduct(),
                basket.getItems().get(0).getProduct());
        assertEquals(basketItems.get(0).getQuantity(),
                basket.getItems().get(0).getQuantity());
    }

    @Test
    public void testAddBasketItemWithQuantityLT0() {
        assertThrows(IllegalArgumentException.class, ()-> {
            basket.add(this.product, -1);
        });
    }

    @Test
    public void testAddNullToBasket() {
        assertThrows(IllegalArgumentException.class, ()-> {
            basket.add(null, 1);
        });
    }

    @Test
    public void testAddBasketItemIfItemInBasket() {
        BasketItem basketItem = new BasketItem(this.product, 1);
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem);
        basket.add(this.product, 1);
        basket.add(this.product, 1);
        assertEquals(1, basket.getItems().size());
    }

    @Test
    public void testRemoveBasketItemIfItemInBasket() {
        basket.add(this.product, 2);
        BasketItem basketItem = basket.getItems().get(0);
        basket.remove(basketItem);
        assertEquals(0, basket.getItems().size());
    }

    @Test
    public void testRemoveBasketItemIfItemNotInBasket() {
        BasketItem basketItem = new BasketItem(this.product, 1);
        assertThrows(IllegalArgumentException.class, ()-> {
            basket.remove(basketItem);
        });
    }

    @Test
    public void testFindBasketItemByIdIfItemInBasket() {
        basket.add(this.product, 2);
        assertEquals(1, (int) basket.getBasketItemByProductId(1).getProduct().getId());
    }

    @Test
    public void testFindBasketItemByIdIfNotInBasket() {
        basket.add(this.product, 2);
        assertEquals(null, basket.getBasketItemByProductId(2));
    }

    @Test
    public void testFindBasketItemIfBasketEmpty() {
        assertEquals(null, basket.getBasketItemByProductId(2));
    }

    @Test
    public void testFindBasketItemIfIdIsNull() {
        assertThrows(IllegalArgumentException.class, ()-> {
            basket.getBasketItemByProductId(null);
        });
    }
}

