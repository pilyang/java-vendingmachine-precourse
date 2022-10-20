package vendingmachine.component;

import java.util.Arrays;

public enum Coin {
    COIN_500(500, "500원"),
    COIN_100(100, "100원"),
    COIN_50(50, "50원"),
    COIN_10(10, "10원");

    private final int amount;
    private final String korean;

    public static final int COIN_TYPE_NUM = Coin.values().length;

    Coin(final int amount, final String korean) {
        this.amount = amount;
        this.korean = korean;
    }

    public int amount(){
        return amount;
    }

    public String korean(){
        return korean;
    }

    public int totalAmount(int number){
        return amount*number;
    }

    public static Coin findByAmount(int amount){
        return Arrays.stream(Coin.values())
                .filter(value -> value.amount == amount)
                .findAny()
                .orElse(null);
    }
}
