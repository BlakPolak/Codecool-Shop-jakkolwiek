package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoSqlite implements ProductDao {

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Supplier supplier = new Supplier("Supplier", "Description");
        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement =connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from products");

            while (result.next()) {
                Product product = new Product (
                    result.getString("name"),
                    result.getFloat("price"),
                    "PLN",
                    result.getString("description"),
                    category,
                    supplier
                );
                products.add(product);
            }
        }
        catch (SQLException e) {
            System.out.println("Connection fail");
            System.out.println(e.getMessage());

        }

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}