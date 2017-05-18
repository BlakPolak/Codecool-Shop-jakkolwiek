package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSqlite extends BaseDao implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(Integer id) throws SQLException {
        ProductCategory category = null;
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from categories where id = " + Integer.toString(id));
        if (rs.next()) {
            category = new ProductCategory(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("department")
            );
            category.setId(rs.getInt("id"));
        }
        return category;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> categories = new ArrayList<>();
        Statement statement = getConnection().createStatement();
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
        return categories;
    }
}