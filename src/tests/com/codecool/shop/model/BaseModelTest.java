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
    void testForSetIdConstructorWithoutId() {
        BaseModel baseModel = new BaseModel("name", "description");
        baseModel.setId(1);
        assertEquals(1, (int) baseModel.getId());
    }

    @Test
    void testIdIntegerClass() {
        BaseModel baseModel = new BaseModel("name", "description");
        baseModel.setId(1);
        assertEquals(Integer.class, baseModel.getId().getClass());
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