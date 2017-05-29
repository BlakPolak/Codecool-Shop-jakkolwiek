package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseModelTest {
    @Test
    void testGetIdBeforeSetId() {
        BaseModel baseModel = new BaseModel("name", "description");
        assertEquals(null, baseModel.getId());
    }

    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

}