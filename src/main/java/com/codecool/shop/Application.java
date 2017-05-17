
package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.Basket;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    private static Application app;
    private static Connection connection;
    private ProductController productController;
    private BasketController basketController;
    private Basket basket = new Basket();

    private Application() {
        this.productController = new ProductController();
        this.basketController = new BasketController();
    }

    public Connection getConnection() {
        return connection;
    }

    public static Application getApp() {
        return app;
    }

    public ProductController getProductController() {
        return this.productController;

    }

    public BasketController getBasketController() {
        return this.basketController;
    }

    public static void start(String[] args) {
        final String initDb = "src/main/resources/init.sql";
        final String migrateDb = "src/main/resources/migrate.sql";
        System.out.println("Initializing application...");
        try {
            connection = SqliteJDBCConnector.connectToDb();
            if (args.length > 0 && args[0].equals("--init-db")) {
                SqliteJDBCConnector.runSql(connection, initDb);
            } else if (args.length > 0 && args[0].equals("--migrate-db")) {
                SqliteJDBCConnector.runSql(connection, migrateDb);
            }
        } catch (SQLException e) {
            System.out.println("Application initialization failed...");
            e.printStackTrace();
        }
        if (app == null)
            app = new Application();
        Routes routes = new Routes();
        routes.run();
    }
}