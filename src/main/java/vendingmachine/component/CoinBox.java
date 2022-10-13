package vendingmachine.component;

import java.util.HashMap;

public class CoinBox {

    private HashMap<Coin, Integer> coins;

    public CoinBox(){
        coins = new HashMap<>(Coin.values().length);
    }


    public void addCoin(Coin coin, int number){
        int totalCoins = 0;
        if(coins.containsKey(coin)){
            totalCoins = coins.get(coin);
        }
        totalCoins += number;
        coins.put(coin, totalCoins);
    }
}
