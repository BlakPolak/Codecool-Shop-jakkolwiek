package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    Product getBy(Integer id) throws SQLException;
    List<Product> getAll() throws SQLException;
    List<Product> getBy(Supplier supplier) throws SQLException;
    List<Product> getBy(ProductCategory productCategory) throws SQLException;
    Long addProduct(String name, Float defaultPrice, String description, Integer categoryId, Integer supplierId) throws SQLException;

}