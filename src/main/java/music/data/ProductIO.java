package music.data;

import java.util.ArrayList;
import java.util.List;

import music.models.Product;

public class ProductIO {

    public static final List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("8601",
                "86 (the band) - True Life Songs and Pictures", 14.95));
        products.add(new Product("pf01",
                "Paddlefoot - The first CD", 12.95));
        products.add(new Product("pf02",
                "Paddlefoot - The second CD", 14.95));
        products.add(new Product("jr01",
                "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static Product getProduct(String productCode) {
        for (Product p : products) {
            if (productCode.equals(p.getCode())) {
                return p;
            }
        }

        return null;
    }

    public static void insertProduct(Product product) {
        products.add(product);
    }

    public static void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product.getCode().equals(p.getCode())) {
                products.set(i, product);
            }
        }
    }

    public static void deleteProduct(String code) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (code.equals(p.getCode())) {
                products.remove(i);
            }
        }
    }
}
