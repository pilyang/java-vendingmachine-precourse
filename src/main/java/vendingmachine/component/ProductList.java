package vendingmachine.component;

import java.util.HashMap;
import java.util.Set;

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

    public void takeOutProduct(Product product, int quantity) throws IllegalArgumentException{
        validateProduct(product);
        int stock = products.get(product).intValue();
        validateProductQuantity(stock, quantity);
        products.put(product, stock-quantity);
    }

    public Product findByName(String name) throws IllegalArgumentException{
        return products.keySet().stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] " + name + "이라는 상품이 존재하지 않습니다."));
    }

    public int getProductStock(Product product){
        validateProduct(product);
        return products.get(product).intValue();
    }

    public int getMinimumProductPrice(){
        Product[] productArray = products.keySet().stream()
                .filter(p -> getProductStock(p)>0 )
                .toArray(Product[]::new);

        int minimumPrice = productArray[0].getPrice();
        for(int i=1; i<productArray.length; i++){
            if( productArray[i].getPrice() < minimumPrice ){
                minimumPrice = productArray[i].getPrice();
            }
        }
        return minimumPrice;
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public void validateProduct(Product product) throws IllegalArgumentException{
        if(!products.containsKey(product)){
            throw new IllegalArgumentException("[ERROR] 상품이 존재하지 않습니다.");
        }
    }

    public void validateProductQuantity(int stock, int target) throws IllegalArgumentException{
        if(stock < target){
            throw new IllegalArgumentException("[ERROR] 상품의 수량이 충분하지 않습니다.");
        }
    }
}
