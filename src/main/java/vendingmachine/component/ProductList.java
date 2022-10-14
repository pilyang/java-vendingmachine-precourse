package vendingmachine.component;

import java.util.HashMap;
import java.util.List;

public class ProductList {

    private HashMap<Product, Integer> products;

    public ProductList(){
        products = new HashMap<>();
    }

    public void addProduct(Product product){
        addProduct(product, 1);
    }

    public void addProduct(Product product, int quantity){
        if(products.containsKey(product)){
            int stock = products.get(product).intValue();
            products.put(product, stock + quantity);
            return;
        }

        products.put(product, quantity);
    }

    public void takeOutProduct(Product product){
        takeOutProduct(product, 1);
    }

    public void takeOutProduct(Product product, int quantity){
        int stock = products.get(product).intValue();
        products.put(product, stock-quantity);
    }
}
