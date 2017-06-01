package com.codecool.shop.controller;


import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.Map;

abstract class BaseController {

    String getModel(String templatePath, Map map) {
        ModelAndView model = new ModelAndView(map, templatePath);
        return new ThymeleafTemplateEngine().render(model);
    }
}
