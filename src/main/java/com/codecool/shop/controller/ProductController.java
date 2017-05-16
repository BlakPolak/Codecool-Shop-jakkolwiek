package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite();
    private ProductView view = new ProductView();

    public ModelAndView listProducts() {
        List<Product> products = productDao.getAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        return new ModelAndView(model, "product/index");
    }

    public void listProductsByCategory() {
        List<ProductCategory> categories = productCategoryDao.getAll();
        view.displayCategoriesList(categories);

        Integer categoryId = UserInput.getUserInput();
        ProductCategory category = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(category);
        view.displayProductsList(products);
    }

    public void listProductsBySupplier() {
        List<Supplier> suppliers = productSupplierDao.getAll();
        view.displaySuppliersList(suppliers);

        Integer supplierId = UserInput.getUserInput();
        Supplier supplier = productSupplierDao.find(supplierId);
        List<Product> products = productDao.getBy(supplier);
        view.displayProductsList(products);
    }
}