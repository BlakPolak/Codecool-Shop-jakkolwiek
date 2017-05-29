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



}