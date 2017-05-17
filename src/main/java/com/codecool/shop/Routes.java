package com.codecool.shop;

import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import static spark.Spark.*;

public class Routes {

    void run() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", (Request req, Response res) -> {
            return Application.getApp().getProductController().listProducts();
        });
        get("/category", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(Application.getApp().getProductController().listProductsByCategory(req, res));
        });

        get("/supplier", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.listProductsBySupplier(req, res));
        });
    }
}