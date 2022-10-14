package vendingmachine.component;

public class Product {

    private final String name;
    private int price;

    public Product(String name) {
        this.name = name;
    }



    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }
}
