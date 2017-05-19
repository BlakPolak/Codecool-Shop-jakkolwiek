
package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.exception.DbCreateStructuresException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.stop;

public class Application {
    private static Application app;
    private static Connection connection;
    private ProductController productController;
    private BasketController basketController;

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
        try {
            connection = SqliteJDBCConnector.connectToDb();
            if (args.length > 0 && args[0].equals("--init-db")) {
                SqliteJDBCConnector.runSql(connection, initDb);
            } else if (args.length > 0 && args[0].equals("--migrate-db")) {
                SqliteJDBCConnector.runSql(connection, migrateDb);
            }
            if (app == null)
                app = new Application();
            new Routes().run();
        } catch (SQLException | DbCreateStructuresException | IOException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.close();
                    stop();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    stop();
                }
            }
         }
    }
}
