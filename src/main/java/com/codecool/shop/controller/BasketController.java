package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;

import java.util.List;

public class BasketController {
    ProductDao productDao = new ProductDaoSqlite();
    Basket basket = new Basket();
}