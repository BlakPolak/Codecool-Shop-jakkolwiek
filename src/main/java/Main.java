import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBSConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--create-table")) {
            try {
                SqliteJDBSConnection.createTable();
            } catch (SQLException e) {
                System.out.println("Fail");
                System.out.println(e);
            }
        }
        ProductController kontroler = new ProductController();
        kontroler.listProduct();
    }


}
