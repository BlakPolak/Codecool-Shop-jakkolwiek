package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDao {

    ProductCategory getBy(Integer id) throws SQLException;
    List<ProductCategory> getAll() throws SQLException;

}
