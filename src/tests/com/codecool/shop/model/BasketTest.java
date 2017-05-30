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

}

