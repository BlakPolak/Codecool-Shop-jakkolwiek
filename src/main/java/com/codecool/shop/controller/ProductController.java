package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;


import java.util.ArrayList;
import java.util.List;


public class ProductController {
   private ProductDao productDao = new ProductDaoSqlite();
   private ProductView view = new ProductView();

    public void listProduct(){
        List<Product> products = productDao.getAll();
        view.displayView(products);


    }





}
