package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.*;
import com.sun.org.apache.regexp.internal.RE;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController extends  BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private Basket basket = new Basket();

    public String addToCartAction(Request req, Response res) {
        int id = Integer.parseInt(req.queryParams("id"));
        List<Product> products = productDao.getById(id);
        basket.add(products.get(0), 1);
        res.redirect("/basket/");
        return "";
    }

    public String listProductsInBasket() {
        List<BasketItem> products = basket.getItems();
        System.out.println(products.get(0).getProduct());
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        String templatePath = "product/basket";
        return this.getModel(templatePath, model);

    }

}