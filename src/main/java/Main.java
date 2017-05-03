import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SqliteJDBCConnector;
import java.sql.SQLException;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            SqliteJDBCConnector.createTable();
        }

        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        productDaoSqlite.getAll();
        ProductController kontroler = new ProductController();
        kontroler.listProduct();
    }


}
