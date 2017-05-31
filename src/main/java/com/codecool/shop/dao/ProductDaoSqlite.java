package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoSqlite extends BaseDao implements ProductDao {
    ProductCategoryDao productCategoryDao;
    ProductSupplierDao productSupplierDao;

    public ProductDaoSqlite(Connection connection,
                            ProductCategoryDao productCategoryDao,
                            ProductSupplierDao productSupplierDao) {
        super(connection);
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDao;
    }

    public ProductDaoSqlite(Connection connection) {
        super(connection);
    }

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
                    productCategoryDao.getBy(rs.getInt("category_id")),
                    productSupplierDao.getBy(rs.getInt("supplier_id"))
            ));
        }
        return products;
    }

    @Override
    public Long addProduct(String name, Float defaultPrice, String description, Integer categoryId, Integer supplierId) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(
                "insert into products (name, description, price, category_id, supplier_id) values (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.setFloat(3, defaultPrice);
        statement.setString(2, description);
        statement.setInt(4, categoryId);
        statement.setInt(5, supplierId);
        Integer affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            return null;
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating product failed, no ID obtained.");
            }
        }
    }
}