package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProductSupplierDaoSqlite extends BaseDao implements ProductSupplierDao {

    @Override
    public void add(Supplier supplier) {
        try {
            PreparedStatement statement = Application.getApp().getConnection().prepareStatement("INSERT INTO suppliers(name, description) VALUES(?, ?)");
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
    }
    public Integer lastInsertRowID() {
        try {
            PreparedStatement statement = Application.getApp().getConnection().prepareStatement("SELECT * FROM suppliers ORDER BY id DESC LIMIT 1");
            ResultSet rs = statement.executeQuery();
            Integer id = rs.getInt(1);
            System.out.println(id);
            return id;
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from suppliers where id = " + Integer.toString(id));

            if(rs.next()) {
                supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
            }

        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<Supplier>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM suppliers");
            while(rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
                suppliers.add(supplier);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return suppliers;
    }
}
