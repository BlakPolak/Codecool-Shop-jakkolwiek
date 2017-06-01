package com.codecool.shop.dao;

import com.codecool.shop.Application;
import database.SqliteJDBCForTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/tests/test.db");
        productCategoryDao = new ProductCategoryDaoSqlite(connection);
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetAllProductCategoriesFromDB() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryFull.sql");
        assertEquals(3, productCategoryDao.getAll().size());
    }

    @Test
    public void testGetProductCategoriesByIdFromDB() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryFull.sql");
        assertTrue("smartfony".equals(productCategoryDao.getBy(1).getName()));
    }

    @Test
    public void testGetAllProductCategoriesFromDBEmptyReturnEmptyList() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryEmpty.sql");
        assertEquals(0, productCategoryDao.getAll().size());
    }

    @Test
    public void testGetByIdProductCategoriesFromDBEmptyReturnNull() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryEmpty.sql");
        assertEquals(0, productCategoryDao.getAll().size());
    }

    @Test
    public void testGetByIdProductCategoriesFromDBWrongThrowsSQLEx() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryWrong.sql");
        assertThrows(SQLException.class, ()-> {
            productCategoryDao.getBy(1);
        });
    }

    @Test
    public void testGetAllProductCategoriesFromDBWrongThrowsSQLEx() throws SQLException, IOException {
        SqliteJDBCForTests.run(connection, "src/tests/database/sqlQueries/categoryWrong.sql");
        assertThrows(SQLException.class, ()-> {
            productCategoryDao.getAll();
        });
    }
}
