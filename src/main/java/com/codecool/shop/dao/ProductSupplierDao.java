package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface ProductSupplierDao {

    Supplier getBy(Integer id) throws SQLException;
    List<Supplier> getAll() throws SQLException;
}
