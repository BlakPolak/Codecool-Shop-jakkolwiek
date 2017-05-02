import com.codecool.shop.controller.ProductController;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


public class Main {

    public static void main(String[] args) {

        ProductController kontroler = new ProductController();
        kontroler.listProduct();
    }


}
