package com.codecool.shop;

import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Routes {

    void run() {
        staticFileLocation("/public");
        port(8888);

        get("/", (Request req, Response res) -> {
            return Application.getApp().getProductController().listProducts();
        });
    }
}