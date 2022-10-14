package vendingmachine.component;

public class Product {

    private static final int MINIMUM_PRICE = 100;
    private static final Coin MINIMUM_UNIT_PRICE_COIN = Coin.COIN_10;

    private final String name;
    private int price;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, int price) throws IllegalArgumentException{
        this.name = name;
        setPrice(price);
    }


    public void setPrice(int price) throws IllegalArgumentException{
        validatePrice(price);
        this.price = price;
    }

    public void validatePrice(int price){
        if(price < MINIMUM_PRICE){
            throw new IllegalArgumentException("[ERROR] 상품의 가격은 " + MINIMUM_PRICE + "원 이상 이여야 합니다.");
        }
        if(price%MINIMUM_UNIT_PRICE_COIN.amount() != 0){
            String message = "[ERROR] 상품의 가격은 " + MINIMUM_UNIT_PRICE_COIN.amount() + "원으로 나누어 떨어져야 합니다.";
            throw new IllegalArgumentException(message);
        }
    }


    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }
}
