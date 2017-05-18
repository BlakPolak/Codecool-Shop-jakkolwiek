package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductSupplierDaoSqlite extends BaseDao implements ProductSupplierDao {

    @Override
    public void add(Supplier category) throws SQLException {

    }

    @Override
    public Supplier find(Integer id) throws SQLException {
        Supplier supplier = null;
        Statement statement = this.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from suppliers where id = " + Integer.toString(id));
        if(rs.next()) {
            supplier = new Supplier(
                    rs.getString("name"),
                    rs.getString("description")
            );
            supplier.setId(rs.getInt("id"));
        }
        return supplier;
    }

    @Override
    public void remove(Integer id) throws SQLException {

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        Statement statement = this.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM suppliers");
        while(rs.next()) {
            Supplier supplier = new Supplier(
                    rs.getString("name"),
                    rs.getString("description")
            );
            supplier.setId(rs.getInt("id"));
            suppliers.add(supplier);
        }
        return suppliers;
    }
}
