package vendingmachine.controller;

import vendingmachine.component.Coin;
import vendingmachine.component.VendingMachine;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

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

    private void addInitialMoney(){
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String moneyStr = Console.readLine();
        int money = Integer.parseInt(moneyStr);

        HashMap<Coin, Integer> coins = generateCoins(money);
        for(Coin coin : Coin.values()){
            vendingMachine.addCoin(coin, coins.get(coin));
        }

        printCoins();

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

    private void printCoins(){
        HashMap<Coin, Integer> coins = vendingMachine.getCurrentCoins();
        System.out.println("자판기가 보유한 동전");
        for(Coin coin : Coin.values()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(coin.korean());
            stringBuilder.append(" - ");
            stringBuilder.append(coins.get(coin));
            stringBuilder.append("개");
            System.out.println(stringBuilder.toString());
        }
    }

    private List<Integer> getNumbersInRange(int start, int end){
        return IntStream.range(start, end)
                .boxed()
                .collect(Collectors.toList());
    }
}
