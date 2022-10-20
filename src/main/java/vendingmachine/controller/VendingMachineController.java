package vendingmachine.controller;

import vendingmachine.component.Coin;
import vendingmachine.component.VendingMachine;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VendingMachineController {

    private static final Coin MIN_UNIT_COIN = Coin.COIN_10;

    private VendingMachine vendingMachine;

    public VendingMachineController(){
        this.vendingMachine = new VendingMachine();
    }

    public void startUsingVendingMachine(){

    }


    private HashMap<Coin, Integer> generateCoins(int money){

        HashMap<Coin, Integer> coins = new HashMap<>();
        List<Integer> possibleCoinNumList;
        int coinNum = 0;

        for(Coin coin : Coin.values()){
            possibleCoinNumList = getNumbersInRange(0, money/coin.amount());
            coinNum = Randoms.pickNumberInList(possibleCoinNumList);
            coins.put(coin, coinNum);
            money -= coin.totalAmount(coinNum);
        }

        if(money != 0){
            coinNum = money/ MIN_UNIT_COIN.amount();
            coins.put(MIN_UNIT_COIN, coins.get(MIN_UNIT_COIN).intValue()+coinNum);
        }

        return coins;
    }

    private List<Integer> getNumbersInRange(int start, int end){
        return IntStream.range(start, end)
                .boxed()
                .collect(Collectors.toList());
    }
}
