package vendingmachine.component;

import java.util.HashMap;

public class CoinBox {

    private HashMap<Coin, Integer> coins;

    public CoinBox(){
        coins = new HashMap<>(Coin.values().length);
    }

}
