package com.codecool.shop.controller;

import com.codecool.shop.Application;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController extends BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();

    public String listProducts() {
        List<Product> products = productDao.getAll();
        String templatePath = "product/index";
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        return this.getModel(templatePath, model);
    }


    public void listProductsByCategory() {
        List<ProductCategory> categories = productCategoryDao.getAll();

//        Integer categoryId = UserInput.getUserInput();
//        ProductCategory category = productCategoryDao.find(categoryId);
//        List<Product> products = productDao.getBy(category);
//        view.displayProductsList(products);
    }

    public void listProductsBySupplier() {
        List<Supplier> suppliers = productSupplierDao.getAll();

//        Integer supplierId = UserInput.getUserInput();
//        Supplier supplier = productSupplierDao.find(supplierId);
//        List<Product> products = productDao.getBy(supplier);
//        view.displayProductsList(products);
    }
}