package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface ProductSupplierDao {

    void add(Supplier category);
    Supplier find(int id);
    void remove(int id);

    List<Supplier> getAll();

}