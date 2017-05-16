
package com.codecool.shop;

import com.codecool.shop.model.Basket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Application {
    private static final Application app = new Application();
    private Connection connection;
    private Basket basket = new Basket();

    public Application() {
    Routes routes = new Routes();
        System.out.println("Initializing application...");

        try {
            this.connectToDb();
            routes.run();
        } catch (SQLException e) {
            System.out.println("Application initial ization failed...");
            e.printStackTrace();
        }

    }

    public Basket getBasket() {
        return basket;
    }

    private void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }
}