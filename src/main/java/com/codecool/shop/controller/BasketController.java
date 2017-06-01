package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.json.JsonUtil;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController extends  BaseController{
    private ProductDao productDao;
    private Basket basket;

    public BasketController(ProductDao productDao) {
        this.productDao = productDao;
        this.basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public String addToCartAction(Request req, Response res) throws SQLException {
        Map<String, String> idProductToAdd = JsonUtil.parse(req.body());
        Product product = this.getProductDao().getBy(Integer.parseInt(idProductToAdd.get("prodId")));
        this.getBasket().add(product, 1);
        return JsonUtil.objectToJson(this.getBasket());
    }

    public String updateBasket() {
        return JsonUtil.objectToJson(this.getBasket());
    }

    public String removeFromCartAction(Request req, Response res) {
        Map<String, String> idProductToRemove = JsonUtil.parse(req.body());
        Integer basketItemId = Integer.parseInt(idProductToRemove.get("basketItemId"));
        BasketItem item = basket.getBasketItemByProductId(basketItemId);
        if(item.getQuantity()>1) {
            item.setQuantity(item.getQuantity()-1);
        } else {
            basket.remove(item);
        }
        return JsonUtil.objectToJson(this.getBasket());
    }

    public String listProductsInCart() {
        List<BasketItem> products = basket.getItems();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        String templatePath = "product/basket";
        return this.getModel(templatePath, model);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BasketController that = (BasketController) other;
        if (productDao != null ? !productDao.equals(that.productDao) : that.productDao != null) return false;
        return basket != null ? basket.equals(that.basket) : that.basket == null;
    }
}
