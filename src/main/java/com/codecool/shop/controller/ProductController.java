package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import java.util.*;

public class ProductController extends BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();

    public String listProducts(Request req, Response res) {
        List<Product> products;
        if (req.queryParams("category") != null) {
            Integer categoryId = Integer.parseInt(req.queryParams("category"));
            ProductCategory category = productCategoryDao.find(categoryId);
            products = productDao.getBy(category);
        } else if (req.queryParams("supplier") != null) {
            Integer supplierId = Integer.parseInt(req.queryParams("supplier"));
            Supplier supplier = productSupplierDao.find(supplierId);
            products = productDao.getBy(supplier);
        } else {
            products = productDao.getAll();
        }
        List<Supplier> suppliers = productSupplierDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();
        String templatePath = "product/index";
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        return this.getModel(templatePath, model);
    }
}
