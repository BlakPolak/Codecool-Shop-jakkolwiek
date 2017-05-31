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
}