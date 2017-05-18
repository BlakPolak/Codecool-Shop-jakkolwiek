package com.codecool.shop;

import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import static spark.Spark.*;

public class Routes {

    void run() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", (Request req, Response res) -> {
            return Application.getApp().getProductController().listProducts(req);
        });

        post("/add-to-basket/", (Request req, Response res) -> {
            return Application.getApp().getBasketController().addToCartAction(req, res);
        });

        get("/basket/", (Request req, Response res) -> {
            return Application.getApp().getBasketController().listProductsInBasket();
        });

        get("/search/", (Request req, Response res) -> {
            String query = req.queryParams("query");
            return Application.getApp().getProductController().listFoundedProducts(query);
        });

        get("/add_product_form/", (Request req, Response res) -> {
            return Application.getApp().getProductController().addProductForm(req, res);
        });
    }
}