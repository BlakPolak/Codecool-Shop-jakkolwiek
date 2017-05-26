package com.codecool.shop;

import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

class Routes {

    void run() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        enableDebugScreen();
        port(8888);

        get("/", (Request req, Response res) -> {
            return Application.getApp().getProductController().listProducts(req);
        });

        post("/basket/add", (Request req, Response res) -> {
            return Application.getApp().getBasketController().addToCartAction(req, res);
        });

        post("/basket/remove", (Request req, Response res) -> {
            return Application.getApp().getBasketController().removeFromCartAction(req, res);
        });

        get("/basket/", (Request req, Response res) -> {
            return Application.getApp().getBasketController().listProductsInCart();
        });

        post("/basket/update", (Request req, Response res) -> {
            return Application.getApp().getBasketController().updateBasket();
        });

        get("/search/", (Request req, Response res) -> {
            return Application.getApp().getProductController().listFoundedProducts(req);
        });

        post("/products/add", (Request req, Response res) -> {
            return Application.getApp().getProductController().addProductToDb(req, res);
        });
    }
}