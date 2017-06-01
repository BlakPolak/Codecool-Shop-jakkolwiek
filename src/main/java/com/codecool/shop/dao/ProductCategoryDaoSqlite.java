package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao {

    public ProductCategoryDaoSqlite(Connection connection) {
        super(connection);
    }

    @Override
    public ProductCategory getBy(Integer id) throws SQLException {
        ProductCategory productCategory = null;
        PreparedStatement statement = getConnection().prepareStatement("select * from categories where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            productCategory = new ProductCategory(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("department"));
        }
        return productCategory;
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> categories = new ArrayList<>();
        PreparedStatement statement = getConnection().prepareStatement("select * from categories");
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            ProductCategory category = new ProductCategory(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("department")
            );
            categories.add(category);
        }
        return categories;
    }
}