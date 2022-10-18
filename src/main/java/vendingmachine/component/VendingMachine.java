package vendingmachine.component;

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
}
