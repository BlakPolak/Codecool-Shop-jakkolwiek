package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import java.sql.SQLException;
import java.util.*;

public class ProductController extends BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ProductSupplierDao getProductSupplierDao() {
        return productSupplierDao;
    }

    private String listProducts(List<Product> products) throws SQLException{
        List<Supplier> suppliers = productSupplierDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();
        String templatePath = "product/index";
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        return this.getModel(templatePath, model);
    }

    public String listProducts(Request req) throws SQLException {
        List<Product> products;
        if (req.queryParams("category") != null) {
            Integer categoryId = Integer.parseInt(req.queryParams("category"));
            ProductCategory category = productCategoryDao.getBy(categoryId);
            products = productDao.getBy(category);
        } else if (req.queryParams("supplier") != null) {
            Integer supplierId = Integer.parseInt(req.queryParams("supplier"));
            Supplier supplier = productSupplierDao.getBy(supplierId);
            products = productDao.getBy(supplier);
        } else {
            products = productDao.getAll();
        }
        return this.listProducts(products);
    }

    public String listFoundedProducts(Request req) throws SQLException{
        String query = req.queryParams("query");
        List<Product> products = this.getProductsByQuery(query);
        return this.listProducts(products);
    }

    private List<Product> getProductsByQuery(String query) throws SQLException{
        List<Product> products = productDao.getAll();
        List<Product> outputList = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getName().toLowerCase().contains(query.toLowerCase())) {
                outputList.add(prod);
            }
        }
        return outputList;
    }
}
