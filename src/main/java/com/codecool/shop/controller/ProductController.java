package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();

    public ModelAndView listProducts() {
        List<Product> products = productDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = productSupplierDao.getAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        return new ModelAndView(model, "product/index");
    }

    public void listProductsByCategory() {

    }

    public ModelAndView listProductsBySupplier(Request req, Response res) {
        Integer supplierId = Integer.parseInt(req.queryParams("supplier"));
        List<Supplier> suppliers = productSupplierDao.getAll();
        Supplier supplier = productSupplierDao.find(supplierId);
        List<Product> products = productDao.getBy(supplier);
        List<ProductCategory> categories = productCategoryDao.getAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        return new ModelAndView(model, "product/index");

    }

}
