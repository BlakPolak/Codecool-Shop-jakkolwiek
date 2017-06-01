package com.codecool.shop.dao;

import com.codecool.shop.Application;
import org.junit.jupiter.api.AfterEach;
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
    private Connection connection;


    @Test
    public void testGetAllProductCategoriesFromDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = new ProductCategoryDaoSqlite(connection);
        assertEquals(3, productCategoryDao.getAll().size());
    }

    @Test
    public void testGetProductCategoriesByIdFromDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = new ProductCategoryDaoSqlite(connection);
        assertTrue("smartfony".equals(productCategoryDao.getBy(1).getName()));
    }

    @Test
    public void testGetAllProductCategoriesFromDBEmptyReturnEmptyList() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/empty.db");
        productCategoryDaoEmptyDB = new ProductCategoryDaoSqlite(connection);
        assertEquals(0, productCategoryDaoEmptyDB.getAll().size());
    }

    @Test
    public void testGetByIdProductCategoriesFromDBEmptyReturnNull() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/empty.db");
        productCategoryDaoEmptyDB = new ProductCategoryDaoSqlite(connection);
        assertEquals(0, productCategoryDaoEmptyDB.getAll().size());
    }

    @Test
    public void testGetByIdProductCategoriesFromDBWrongThrowsSQLEx() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/wrong.db");
        ProductCategoryDaoSqlite productCategoryDaoWrongDB =
                new ProductCategoryDaoSqlite(connection);
        assertThrows(SQLException.class, ()-> {
            productCategoryDaoWrongDB.getBy(1);
        });
    }

    @Test
    public void testGetAllProductCategoriesFromDBWrongThrowsSQLEx() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/wrong.db");
        ProductCategoryDaoSqlite productCategoryDaoWrongDB =
                new ProductCategoryDaoSqlite(connection);
        assertThrows(SQLException.class, ()-> {
            productCategoryDaoWrongDB.getAll();
        });
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        connection.close();
    }
}