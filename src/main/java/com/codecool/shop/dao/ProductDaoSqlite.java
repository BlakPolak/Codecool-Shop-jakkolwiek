package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoSqlite extends BaseDao implements ProductDao {
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        Product product = null;
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();
        try {
            Connection connection = SqliteJDBCConnector.connection();
            PreparedStatement statement = connection.prepareStatement("select * from products where id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    productCategoryDao.find(product.setId(rs.getInt("category_id")));
                    productSupplierDao.find(product.setId(rs.getInt("supplier_id")))
                );
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * FROM products");
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement statement = this.getConnection().
                    prepareStatement("SELECT * FROM products WHERE supplier_id = ?");
            statement.setInt(1, supplier.getId());
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = this.getConnection().
                    prepareStatement("SELECT * FROM products WHERE category_id = ?");
            statement.setInt(1, productCategory.getId());
            products = this.getProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Supplier supplier = new Supplier();
        ProductCategory category = new ProductCategory();
        ProductSupplierDaoSqlite supplierDao = new ProductSupplierDaoSqlite();

        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            supplierDao.find(rs.getInt("supplier_id"));
            Product product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    category,
                    supplier
            );
            product.setId(rs.getInt("id"));
            products.add(product);
        }

        return products;
    }
}