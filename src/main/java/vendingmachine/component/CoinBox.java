package vendingmachine.component;

import java.util.HashMap;

public class CoinBox {


    private HashMap<Coin, Integer> coins;

    public CoinBox(){
        coins = new HashMap<>(Coin.COIN_TYPE_NUM);
    }


    public void addCoin(Coin coin, int number){
        int totalCoins = 0;
        if(coins.containsKey(coin)){
            totalCoins = coins.get(coin);
        }
        totalCoins += number;
        coins.put(coin, totalCoins);
    }

    public void subCoin(Coin coin, int number) throws IllegalArgumentException{
        validateSubCoin(coin, number);
        int totalCoin = coins.get(coin);
        totalCoin -= number;
        coins.put(coin, totalCoin);
    }

    public int getCoinNumber(Coin coin){
        if(coins.containsKey(coin)){
            return coins.get(coin).intValue();
        }
        return 0;
    }

    public int getTotalMoney(){
        int totalMoney = 0;
        for(Coin coin : Coin.values()){
            totalMoney += coin.totalAmount(getCoinNumber(coin));
        }
        return totalMoney;
    }

    public int[] getChanges(int money){
    }

    private void validateSubCoin(Coin coin, int number) throws IllegalArgumentException{
        if(getCoinNumber(coin) < number){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[ERROR] ");
            stringBuilder.append(coin.name());
            stringBuilder.append(" 동전이 부족합니다");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
