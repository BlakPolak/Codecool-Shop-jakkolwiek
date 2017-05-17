package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.model.Basket;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Routes {
    ProductController productController = new ProductController();
    BasketController basketController = new BasketController();
    void run() {
        staticFileLocation("/public");
        port(8888);
        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.listProducts());
        });

        post("/add-to-basket/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(basketController.addToCartAction(req,res));
        });

        get("/basket/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(basketController.listProductsInBasket());
        });
    }

}
