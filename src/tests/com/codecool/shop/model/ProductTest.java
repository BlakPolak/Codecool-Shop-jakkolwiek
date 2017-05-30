package com.codecool.shop.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;

    @BeforeEach
    void getProduct(){
        product = new Product(0,
                "product",
                -5f,
                "PLN",
                "description",
                null,
                null
        );
    }

    @Test
    void testFailSetPriceLtZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(-5f, "pln");
        });
    }

    @Test
    void testFailIfPriceIsNotAccurate() {
        product.setPrice(12.99f, "PLN");
        assertEquals(12.99f + " " + "PLN",product.getPrice());
    }



}