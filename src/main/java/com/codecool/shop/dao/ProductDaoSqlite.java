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
    public void add(Product product) {

    }

    @Override
    public Product find(Integer id) throws SQLException {
        Product product;
        PreparedStatement statement = getConnection().prepareStatement("select * from products where id=(?)");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        product = new Product(
                rs.getString("name"),
                rs.getFloat("price"),
                "PLN",
                rs.getString("description"),
                Application.getApp().getProductController().getProductCategoryDao().find(rs.getInt("category_id")),
                Application.getApp().getProductController().getProductSupplierDao().find(rs.getInt("supplier_id"))
            );
        product.setId(id);
        return product;
    }

    @Override
    public void remove(Integer id) {

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
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        List<Product> products;
        PreparedStatement statement = this.getConnection().
                prepareStatement("SELECT * FROM products WHERE category_id = ?");
        statement.setInt(1, productCategory.getId());
        products = this.getProducts(statement);
        return products;
    }

    private List<Product> getProducts(PreparedStatement statement) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
        ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
             Product product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    productCategoryDao.find(rs.getInt("category_id")),
                    productSupplierDao.find(rs.getInt("supplier_id"))
            );
            product.setId(rs.getInt("id"));
            products.add(product);
        }

        return products;
    }

}