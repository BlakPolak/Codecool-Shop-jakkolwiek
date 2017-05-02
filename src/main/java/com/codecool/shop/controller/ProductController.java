package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


import java.util.ArrayList;
import java.util.List;


public class ProductController {

    public void listProduct(){
        ProductDao productDao = new ProductDaoSqlite();
        List<Product> products = productDao.getAll();

        for(Product p:products) {
            System.out.println(p.getName());
        }

    }





}
