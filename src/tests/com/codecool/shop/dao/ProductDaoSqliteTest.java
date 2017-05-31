package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoSqliteTest {
    private Connection connection;
    private ProductCategoryDao productCategoryDao;
    private ProductSupplierDao productSupplierDao;
    private ProductDao productDao;

    @BeforeEach
    void createDbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = mock(ProductCategoryDaoSqlite.class);
        when(productCategoryDao.getBy(anyInt())).thenReturn(null);
        productSupplierDao = mock(ProductSupplierDaoSqlite.class);
        when(productSupplierDao.getBy(anyInt())).thenReturn(null);
        productDao = new ProductDaoSqlite(
                connection,
                productCategoryDao,
                productSupplierDao
        );
    }

    @Test
    void testGetAllReturnCorrectSize() throws SQLException{
        assertEquals(1,productDao.getAll().size());
    }
}