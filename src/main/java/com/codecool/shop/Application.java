
package com.codecool.shop;

import com.codecool.shop.controller.MainMenuController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static final Application app = new Application();
    private Connection connection;

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

    private void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    private void dispatchMainMenuController() {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.mainMenuAction();
    }
}