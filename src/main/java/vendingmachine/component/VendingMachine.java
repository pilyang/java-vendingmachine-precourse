package vendingmachine.component;

import java.util.HashMap;

public class VendingMachine {

    private CoinBox coinBox;
    private ProductList productList;
    private int insertedMoney;

    public VendingMachine(){
        insertedMoney = 0;
        coinBox = new CoinBox();
        productList = new ProductList();
    }

    public void insertMoney(int money){
        this.insertedMoney += money;
    }

    public void addCoin(Coin coin, int number){
        coinBox.addCoin(coin, number);
    }

    public void addProduct(Product product, int quantity){
        productList.addProduct(product, quantity);
    }

    public void sellProduct(String productName) throws IllegalArgumentException{
        Product product = productList.findByName(productName);
        validateProductPrice(product);
        validateProductStock(product);
        useMoney(product.getPrice());
        productList.takeOutProduct(product);
    }

    public HashMap<Coin, Integer> returnChanges(){
        return coinBox.getChanges(insertedMoney);
    }

    public int getMinimumProductPrice(){
        Product[] products = productList.getProductArray();
        int minimumPrice = products[0].getPrice();
        for(int i=1; i<products.length; i++){
            if(products[i].getPrice() < minimumPrice){
                minimumPrice = products[i].getPrice();
            }
        }
        return minimumPrice;
    }

    public boolean isAvailable(){
        return true;
    }

    private boolean hasEnoughMoney(){
        if(getMinimumProductPrice() > insertedMoney){
            return false;
        }
        return true;
    }

    private void useMoney(int price){
        this.insertedMoney -= price;
    }

    public void validateProductPrice(Product product) throws IllegalArgumentException{
        if(product.getPrice() > insertedMoney){
            throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다.");
        }
    }

    public void validateProductStock(Product product) throws IllegalArgumentException{
        if(productList.getProductStock(product) <= 0) {
            throw new IllegalArgumentException("[ERROR] 상품의 제고가 부족합니다.");
        }
    }

}
