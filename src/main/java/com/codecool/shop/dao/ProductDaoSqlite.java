package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite extends BaseDao implements ProductDao {

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products;
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM products");
        products = this.getProducts(statement);
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        List<Product> products;
        PreparedStatement statement = this.getConnection().
                prepareStatement("SELECT * FROM products WHERE supplier_id = ?");
        statement.setInt(1, supplier.getId());
        products = this.getProducts(statement);
        return products;
    }

    @Override
    public Product getBy(Integer id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("select * from products where id=?");
        statement.setInt(1, id);
        return this.getProducts(statement).get(0);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        List<Product> products;
        PreparedStatement statement = this.getConnection().
                prepareStatement("SELECT * FROM products WHERE category_id = ?");
        statement.setInt(1, productCategory.getId());
        products = this.getProducts(statement);
        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    Application.getApp().getProductController().getProductCategoryDao().getBy(rs.getInt("category_id")),
                    Application.getApp().getProductController().getProductSupplierDao().getBy(rs.getInt("supplier_id"))
            ));
        }
        return products;
    }
}