package vendingmachine.controller;

import vendingmachine.component.Coin;
import vendingmachine.component.Product;
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
    private static final String PRODUCT_INPUT_REGEXP = "^(\\[[^,]+,[0-9]+,[0-9]+\\])(;\\[[^,]+,[0-9]+,[0-9]+\\])*$";

    private VendingMachine vendingMachine;

    public VendingMachineController(){
        this.vendingMachine = new VendingMachine();
    }

    public void startUsingVendingMachine(){

        addInitialMoney();
        printCoins();
        readProducts();

    }

    private void addInitialMoney(){
        while (true) {
            try {
                System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
                String moneyStr = Console.readLine();
                validateMoney(moneyStr);
                int money = Integer.parseInt(moneyStr);
                HashMap<Coin, Integer> coins = generateCoins(money);
                for(Coin coin : Coin.values()){
                    vendingMachine.addCoin(coin, coins.get(coin));
                }
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
    }

    private void readProducts(){
        while(true) {
            try {
                System.out.println("\n상품명과 가격, 수량을 입력해 주세요.");
                String productsInput = Console.readLine();
                validateProductInputFormat(productsInput);
                String[][] productsString = makeProducts(productsInput);
                for (String[] productInfo : productsString) {
                    addProduct(productInfo);
                }
                return;
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }

    private void inputUserMoney(){
        while(true){
            try {
                System.out.println("\n투입 금액을 입력해주세요.");
                String moneyInput = Console.readLine();
                validateMoney(moneyInput);
                vendingMachine.insertMoney(Integer.parseInt(moneyInput));
                return;
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }

    private void printUserMoney(){
        int money = vendingMachine.getCurrentUserMoney();
        System.out.println("\n투입 금액: " + money + "원");
    }

    private void buyProduct(){
        while (true){
            try {
                System.out.println("구매할 상품명을 입력해주세요");
                String productName = Console.readLine();
                vendingMachine.sellProduct(productName);
                return;
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }

    private String[][] makeProducts(String productsInput){

        String[] tempProducts = productsInput.split(";");
        String[][] products = new String[tempProducts.length][3];

        for(int i=0; i< tempProducts.length; i++){
            String product = tempProducts[i].substring(1, tempProducts[i].length()-1 );
            products[i] = product.split(",");
        }

        return products;
    }

    private void addProduct(String[] productInfo) throws IllegalArgumentException{
        String name = productInfo[0];
        int price = Integer.parseInt(productInfo[1]);
        int quantity = Integer.parseInt(productInfo[2]);

        validateNumberDividedWith10(price);

        Product product = new Product(name, price);
        vendingMachine.addProduct(product, quantity);
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
        System.out.println();
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
        return IntStream.range(start, end+1)
                .boxed()
                .collect(Collectors.toList());
    }

    private void validateMoney(String money) throws IllegalArgumentException{
        validateIsNumber(money);
        int moneyIntValue = Integer.parseInt(money);
        validateNumberDividedWith10(moneyIntValue);
    }

    private void validateIsNumber(String number) throws IllegalArgumentException{
        if( !number.matches("\\d+") ){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }

    private void validateNumberDividedWith10(int money) throws IllegalArgumentException{
        if( money%10 != 0) {
            throw new IllegalArgumentException("[ERROR] 입력 금액이 10으로 나누어 떨어지지 않습니다.");
        }
    }

    private void validateProductInputFormat(String input) throws IllegalArgumentException{
        if( !input.matches(PRODUCT_INPUT_REGEXP) ){
            throw new IllegalArgumentException("[ERROR] 상품 입력 형식을 맞춰주세요. 상품명, 가격, 수량은 쉼표로, 개별 상품은 대괄호([])로 묶어 세미콜론(;)으로 구분해주세요.");
        }
    }
}
