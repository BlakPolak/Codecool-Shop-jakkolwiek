package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.ProductSupplierDao;
import com.codecool.shop.dao.ProductSupplierDaoSqlite;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.exception.DbCreateStructuresException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import static spark.Spark.stop;

public class Application {
    private static Application app;
    private ProductController productController;
    private BasketController basketController;

    private Application(Connection connection) {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite(connection);
        ProductSupplierDao productSupplierDao = new ProductSupplierDaoSqlite(connection);
        ProductDao productDao = new ProductDaoSqlite(connection, productCategoryDao, productSupplierDao);
        this.productController = new ProductController(productDao, productSupplierDao, productCategoryDao);
        this.basketController = new BasketController(productDao);
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
            SqliteJDBCConnector.setConnection("jdbc:sqlite:src/main/resources/database.db");
            if (args.length > 0 && args[0].equals("--init-db")) {
                SqliteJDBCConnector.runSql(SqliteJDBCConnector.getConnection(), initDb);
            } else if (args.length > 0 && args[0].equals("--migrate-db")) {
                SqliteJDBCConnector.runSql(SqliteJDBCConnector.getConnection(), migrateDb);
            }
            if (app == null)
                app = new Application(SqliteJDBCConnector.getConnection());
            new Routes().run();
        } catch (SQLException | DbCreateStructuresException | IOException e) {
            e.printStackTrace();
            if (SqliteJDBCConnector.getConnection() != null) {
                try {
                    SqliteJDBCConnector.getConnection().close();
                    stop();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    stop();
                }
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Thread.sleep(200);
                SqliteJDBCConnector.getConnection().close();
                System.out.println("Shouting down ...");
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }));
    }
}
