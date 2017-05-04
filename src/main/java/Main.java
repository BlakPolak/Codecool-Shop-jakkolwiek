import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;



public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--create-tables")) {
            SqliteJDBCConnector.createTable();
        }
        ProductController productController = new ProductController();
        productController.listProduct();

    }

}
