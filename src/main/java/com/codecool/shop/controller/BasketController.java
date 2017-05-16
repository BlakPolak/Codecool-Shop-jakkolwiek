package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;

import java.util.List;

public class BasketController {
    ProductDao productDao = new ProductDaoSqlite();
    Basket basket = new Basket();

    public void addToCartAction() {
        List<Product> products = this.productDao.getAll();

//        System.out.print("Select product by giving id: ");
//        Integer productId = UserInput.getUserInput();
//        Product product = productDao.find(productId);
//        this.basket.add(product, 1);
    }
}