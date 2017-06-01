package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import database.SqliteJDBCForTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;
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
    void createDbConnectionAndObjectsNeededInTests() throws SQLException, IOException {
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
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/template.sql");
    }

    @Test
    void testGetAllReturnCorrectSize() throws SQLException{
        assertEquals(6,productDao.getAll().size());
    }

    @Test
    void testGetBySupplierReturnCorrectListSize() throws SQLException{
        Supplier supplier = mock(Supplier.class);
        when(supplier.getId()).thenReturn(1);
        assertEquals(0,productDao.getBy(supplier).size());
    }

    @Test
    void testGetBySupplierThrowsIllegalArgumentExceptionIfSupplierHaveNullId(){
        Supplier supplier = mock(Supplier.class);
        when(supplier.getId()).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> productDao.getBy(supplier));
    }

    @Test
    void testGetByProductCategoryReturnCorrectListSize() throws SQLException{
        ProductCategory productCategory = mock(ProductCategory.class);
        when(productCategory.getId()).thenReturn(1);
        assertEquals(2,productDao.getBy(productCategory).size());
    }

    @Test
    void testGetByProductCategoryThrowsIllegalArgumentExceptionIfProductCategoryHaveNullId() throws SQLException{
        ProductCategory productCategory = mock(ProductCategory.class);
        when(productCategory.getId()).thenReturn(null);
        assertThrows(IllegalArgumentException.class,()->productDao.getBy(productCategory));
    }

    @Test
    void testGetByIdIfProductsDoesNotExist() throws SQLException{
        assertThrows(IllegalArgumentException.class,() -> productDao.getBy(13));
    }

    @Test
    void testGetByIdIfProductsExist() throws SQLException{
        Product product = new Product(
                13,
                "smartfon",
                800f,
                "PLN",
                "drogi smartfon",
                null,
                null
        );
        assertEquals(product,productDao.getBy(13));
    }

    @Test
    void testAddProductReturnCorrectId() throws SQLException{
        assertEquals("16",productDao.addProduct("a", 12.3f, "a", 2, 2).toString());
    }

    @Test
    void testAddProductThrowsIllegalArgumentExceptionIfNullArgumentPassed() throws SQLException{
        assertThrows(IllegalArgumentException.class,() -> productDao.addProduct(
                null,
                null,
                null,
                null,
                null
                ));
    }
}