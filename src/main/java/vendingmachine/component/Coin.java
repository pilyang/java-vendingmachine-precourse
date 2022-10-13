package vendingmachine.component;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int amount(){
        return amount;
    }

    public static Coin findByAmount(int amount){
        return Arrays.stream(Coin.values())
                .filter(value -> value.amount == amount)
                .findAny()
                .orElse(null);
    }
}
