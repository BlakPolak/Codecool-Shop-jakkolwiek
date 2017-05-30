package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {
    private ProductCategory productCategory;

    @BeforeEach
    void createProductCategory() {
        productCategory = new ProductCategory(
                1,
                "Name",
                "Description",
                "Department");
    }
}