package com.codecool.shop.view;

import com.codecool.shop.model.Product;

import java.util.List;

/**
 * Created by mar on 02.05.17.
 */
public class ProductView {

    public static void displayView(List<Product> products) {
        for(Product p: products) {
            System.out.println(p.getName());
        }
    }
}
