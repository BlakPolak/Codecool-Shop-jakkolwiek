package com.codecool.shop.dao;

import com.codecool.shop.Application;
import java.sql.Connection;

abstract class BaseDao {
    private Connection connection;

    BaseDao(Connection connection) {
        this.connection = connection;
    }

    Connection getConnection() {
        return this.connection;
    }
}
