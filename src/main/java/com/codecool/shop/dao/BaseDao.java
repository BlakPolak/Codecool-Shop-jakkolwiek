package com.codecool.shop.dao;

import com.codecool.shop.Application;
import java.sql.Connection;

abstract class BaseDao {

    public Connection getConnection() {
        return Application.getApp().getConnection();
    }
}