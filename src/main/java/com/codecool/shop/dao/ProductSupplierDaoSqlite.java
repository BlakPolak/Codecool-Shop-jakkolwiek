package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSupplierDaoSqlite extends BaseDao implements ProductSupplierDao {

    public ProductSupplierDaoSqlite(Connection connection) {
        super(connection);
    }

    @Override
    public Supplier getBy(Integer id) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement("select * from suppliers where id = ?");
        statement.setInt(1, id);
        return this.getSuppliers(statement).get(0);
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement("select distinct * from suppliers");
        return getSuppliers(statement);
    }

    private List<Supplier> getSuppliers(PreparedStatement statement) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            suppliers.add(new Supplier(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
            ));
        }
        return suppliers;
    }
}

