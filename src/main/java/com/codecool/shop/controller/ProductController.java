package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();

    public ModelAndView listProducts() {
        List<ProductCategory> category = productCategoryDao.getAll();
        List<Product> products = productDao.getAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", category);
        return new ModelAndView(model, "product/index");
    }

    public ModelAndView listProductsByCategory(Request req, Response res) {
        String id = req.queryParams("id");
        ProductCategory category= productCategoryDao.find(Integer.parseInt(id));
        List<Product> products = productDao.getBy(category);
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        return new ModelAndView(model, "product/index");
    }
//
//    public void listProductsBySupplier() {
//        List<Supplier> suppliers = productSupplierDao.getAll();
//
//        Integer supplierId = UserInput.getUserInput();
//        Supplier supplier = productSupplierDao.find(supplierId);
//        List<Product> products = productDao.getBy(supplier);
//        view.displayProductsList(products);
//    }
}