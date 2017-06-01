package com.codecool.shop.dao;

import com.codecool.shop.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProductCategoryDaoSqliteTest {

    private ProductCategoryDaoSqlite productCategoryDao;
    private ProductCategoryDaoSqlite productCategoryDaoEmptyDB;
    private ProductCategoryDaoSqlite productCategoryDaoWrongDB;


    @Test
    public void testGetAllProductCategoriesFromDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = new ProductCategoryDaoSqlite(connection);
        assertEquals(3, productCategoryDao.getAll().size());
    }

    @Test
    public void testGetProductCategoriesByIdFromDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = new ProductCategoryDaoSqlite(connection);
        assertTrue("smartfony".equals(productCategoryDao.getBy(1).getName()));
    }
}