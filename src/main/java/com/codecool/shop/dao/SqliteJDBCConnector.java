package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mar on 02.05.17.
 */
public class SqliteJDBCConnector {
    public static Connection connection;

    public static Connection connection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            System.out.println("Connection fail");
            System.out.println(e.getMessage());
        }
        return connection;
    }
    public static void  createTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            Statement statement =connection.createStatement();
            statement.executeQuery(
                    "CREATE TABLE IF NOT EXISTS products\n" +
                            "(\n" +
                            "   id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                            "   name VARCHAR,\n" +
                            "   description TEXT,\n" +
                            "   price DOUBLE DEFAULT 0.00\n" +
                            ")");
        } catch(SQLException e) {
            System.out.println("Connection fail");
            System.out.println(e.getMessage());
        }
    }
}

