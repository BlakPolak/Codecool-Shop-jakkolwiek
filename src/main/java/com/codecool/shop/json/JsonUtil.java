package com.codecool.shop.json;


import com.google.gson.Gson;

import java.util.Map;

public class JsonUtil {
    public static Map<String, String> parse(String object) {
        return new Gson().fromJson(object, Map.class);
    }
    public static <T> String objectToJson(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
