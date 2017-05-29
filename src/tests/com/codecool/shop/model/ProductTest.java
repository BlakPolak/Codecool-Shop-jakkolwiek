package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testFailSetPriceLtZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(0,
                    "product",
                    -5f,
                    "PLN",
                    "description",
                    null,
                    null
            );
        });
    }

    @Test
    void testFailIfPriceIsNotAccurate() {
        Product product = new Product(null,
                null,
                12.99f,
                "PLN",
                null,
                null,
                null
        );
        assertEquals(12.99f + " " + "PLN",product.getPrice());
    }



}