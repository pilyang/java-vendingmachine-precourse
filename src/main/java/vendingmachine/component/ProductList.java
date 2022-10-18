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

    public Product findByName(String name) throws IllegalArgumentException{
        return products.keySet().stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] " + name + "이라는 상품이 존재하지 않습니다."));
    }
}
