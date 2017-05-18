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

    private String listProducts(List<Product> products) {
        List<Supplier> suppliers = productSupplierDao.getAll();
        List<ProductCategory> categories = productCategoryDao.getAll();
        String templatePath = "product/index";
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        return this.getModel(templatePath, model);
    }

        public String listProducts(Request req) {
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
        return this.listProducts(products);
    }

    public String listFoundedProducts(String query) {
        List<Product> products = this.getProductsByQuery(query);
        return this.listProducts(products);
    }

    private List<Product> getProductsByQuery(String query) {
        List<Product> products = productDao.getAll();
        List<Product> outputList = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getName().toLowerCase().contains(query.toLowerCase())) {
                outputList.add(prod);
            }
        }
        return outputList;
    }

    public String addProductForm(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<ProductCategory> categories = productCategoryDao.getAll();
        List<Supplier> suppliers = productSupplierDao.getAll();
        model.put("categories", categories);
        model.put("suppliers", suppliers);
        String templatePath = "product/add_product";
        return this.getModel(templatePath, model);
    }

    public String addProduct(Request req, Response res) {
//        Map<String, Object> model = new HashMap<>();
//        List<ProductCategory> categories = productCategoryDao.getAll();
//        List<Supplier> suppliers = productSupplierDao.getAll();
//        model.put("categories", categories);
//        model.put("suppliers", suppliers);
//        String templatePath = "product/add_product";
//        return this.getModel(templatePath, model);
        String productName = req.queryParams("product_name");
        String productDescription = req.queryParams("product_description");
        String productPrice = req.queryParams("product_price");
        String categoryName = req.queryParams("category_name");
        String categoryDescription = req.queryParams("category_description");
        String categoryDepartment = req.queryParams("category_department");
        String supplierName = req.queryParams("supplier_name");
        String supplierDescription = req.queryParams("supplier_description");
//        controller.createEvent(name, categoryName, description, startDate);
        res.redirect("/");
        return "";
    }
}
