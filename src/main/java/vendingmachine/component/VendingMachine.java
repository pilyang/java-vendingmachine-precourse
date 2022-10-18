package vendingmachine.component;

public class VendingMachine {

    private CoinBox coinBox;
    private ProductList productList;

    public VendingMachine(){
        coinBox = new CoinBox();
        productList = new ProductList();
    }

    public void addCoin(Coin coin, int number){
        coinBox.addCoin(coin, number);
    }
}
