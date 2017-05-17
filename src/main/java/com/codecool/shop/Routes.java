package com.codecool.shop;


import com.codecool.shop.controller.ProductController;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Routes {
    ProductController productController = new ProductController();
    void run() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
        get("/", (Request req, Response res) -> {
//            return new ThymeleafTemplateEngine().render(productController.listProducts());
//            ProductController pro = Application.getApp().getProductController();
//            System.out.println("");
            return new ThymeleafTemplateEngine().render(Application.getApp().getProductController().listProducts());
        });

        get("/category", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(Application.getApp().getProductController().listProductsByCategory(req, res));
        });



    }

}
