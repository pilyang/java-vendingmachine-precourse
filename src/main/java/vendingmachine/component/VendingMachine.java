package vendingmachine.component;

public class VendingMachine {

    private CoinBox coinBox;
    private ProductList productList;

    public VendingMachine(){
        coinBox = new CoinBox();
        productList = new ProductList();
    }
}
