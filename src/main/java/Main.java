import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.view.ProductView;


public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--create-tables")) {
            SqliteJDBCConnector.createTable();
        }

        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        ProductView.displayView(productDaoSqlite.getAll());

    }

}
