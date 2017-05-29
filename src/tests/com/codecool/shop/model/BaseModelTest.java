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
    void testForSetIdConstructorWithId() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        assertEquals(1, (int) baseModel.getId());
    }

    @Test
    void testIdIntegerClass() {
        BaseModel baseModel = new BaseModel("name", "description");
        baseModel.setId(1);
        assertEquals(Integer.class, baseModel.getId().getClass());
    }

    @Test
    void testSetAndGetName() {
        BaseModel baseModel = new BaseModel("name", "description");
        baseModel.setName("NewName");
        assertEquals("NewName", baseModel.getName());
    }

    @Test
    void testSetAndGetDescription() {
        BaseModel baseModel = new BaseModel("name", "description");
        baseModel.setDescription("NewDescription");
        assertEquals("NewDescription", baseModel.getDescription());
    }
}