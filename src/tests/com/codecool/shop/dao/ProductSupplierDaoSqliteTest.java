package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductSupplierDaoSqliteTest {
    private Connection connection;
    private ProductSupplierDao productSupplierDao;

    @BeforeEach
    void setConnectionToDBAndCreateDaoSqlite() throws SQLException {
        SqliteJDBCConnector.setConnection("jdbc:sqlite:src/tests/test.db");
        connection = SqliteJDBCConnector.getConnection();
        productSupplierDao = new ProductSupplierDaoSqlite(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void testListHaveCorrectSizeForGetAllSuppliers() throws SQLException {
        List<Supplier> supplierList = productSupplierDao.getAll();
        assertEquals(3, supplierList.size());
    }

    @Test
    void testGetOneSupplierById_2() throws SQLException {
        Supplier supplier = productSupplierDao.getBy(2);
        Supplier correctSupplier = new Supplier(2, "Media markt", "niemiecki koncern");
        assertEquals(correctSupplier.getName(), supplier.getName());
    }
}