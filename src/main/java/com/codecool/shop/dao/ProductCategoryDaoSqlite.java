package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {
        try {
            PreparedStatement statement = Application.getApp().getConnection().prepareStatement("INSERT INTO categories(name, description, department) VALUES(?, ?, ?)");
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
    }
    public ResultSet lastInsertRowID() {
        try {
            PreparedStatement statement = Application.getApp().getConnection().prepareStatement("SELECT last_insert_rowid() FROM categories");
            ResultSet rs = statement.executeQuery();
            statement.close();
            return rs;
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return null;
    }




    @Override
    public ProductCategory find(int id) {
        ProductCategory category = null;

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from categories where id = " + Integer.toString(id));

            if(rs.next()) {
                category = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
                category.setId(rs.getInt("id"));
            }

        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return category;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from categories");
            while(rs.next()) {
                ProductCategory category = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department")
                );
                category.setId(rs.getInt("id"));
                categories.add(category);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return categories;
    }
}