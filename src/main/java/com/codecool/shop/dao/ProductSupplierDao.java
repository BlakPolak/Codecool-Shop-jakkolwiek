package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface ProductSupplierDao {

    void add(Supplier category) throws SQLException;
    Supplier find(Integer id) throws SQLException;
    void remove(Integer id) throws SQLException;
    List<Supplier> getAll() throws SQLException;
}