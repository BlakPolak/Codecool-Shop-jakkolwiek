package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController extends  BaseController{
    private ProductDao productDao = new ProductDaoSqlite();
    private Basket basket = new Basket();

    public String addToCartAction(Request req, Response res) throws SQLException {
        Integer id = Integer.parseInt(req.queryParams("id"));
        Product product = productDao.getBy(id);
        basket.add(product, 1);
        res.redirect("/basket/");
        return "";
    }

    public String removeFromCartAction(Request req, Response res) {
        int id = Integer.parseInt(req.queryParams("id"));
        BasketItem item = basket.getBasketItemById(id);
        if(item.getQuantity()>1) item.setQuantity(item.getQuantity()-1);
        else basket.remove(item);
        res.redirect("/basket/");
        return "";
    }

    public String listProductsInCart() {
        List<BasketItem> products = basket.getItems();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        String templatePath = "product/basket";
        return this.getModel(templatePath, model);
    }
}