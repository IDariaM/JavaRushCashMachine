package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    //код валюты, например, USD. Состоит из трех букв
    private String currencyCode;
    //Map<номинал, количество>
    private Map<Integer, Integer> denominations = new HashMap<>();

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            total = total + (entry.getKey() * entry.getValue());
        }
        return total;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    //который вернет карту HashMap<номинал, количество>
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        if (expectedAmount==0){}
        Map<Integer, Integer> toWithdraw = new HashMap<>();
        if (expectedAmount==0){return toWithdraw;}
        try {
            if (expectedAmount == getTotalAmount()) {
                toWithdraw.putAll(denominations);
                denominations.clear();
                toWithdraw.entrySet().removeIf(e -> e.getValue() == 0);

                return toWithdraw;
            }
            if (denominations.containsKey(expectedAmount)) {
                toWithdraw.put(expectedAmount, 1);
                denominations.replace(expectedAmount, denominations.get(expectedAmount) - 1);
                denominations.entrySet().removeIf(e -> e.getValue() == 0);
                toWithdraw.entrySet().removeIf(e -> e.getValue() == 0);

                return toWithdraw;
            }


            toWithdraw = foundTheBest(expectedAmount);
            if (toWithdraw.size() > 0) {
                toWithdraw.entrySet().removeIf(e -> e.getValue() == 0);

                return toWithdraw;
            }
        } catch (ConcurrentModificationException e) {

        }
        toWithdraw.entrySet().removeIf(e -> e.getValue() == 0);

        return toWithdraw;
    }

    /*public Map<Integer, Integer> ifTwoBanknots(int expectedAmount) {
        Map<Integer, Integer> map = new HashMap<>();
        try {
            if (denominations.containsKey(expectedAmount * 2)) {
                denominations.replace(expectedAmount, denominations.get(expectedAmount) - 2);
                denominations.entrySet().removeIf(e -> e.getValue() == 0);

            }
        } catch (ConcurrentModificationException e) {

        }
        return map;
    }*/

    public Map<Integer, Integer> foundTheBest(int expectedAmount) throws NotEnoughMoneyException {
        int amount = expectedAmount;
        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        Map<Integer, Integer> resMap = new HashMap<>();
        map.putAll(denominations);
        try {


                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    System.out.println(amount);
                    if (amount<=0){break;}
                    if (denominations.isEmpty()){throw new NotEnoughMoneyException();}
                    if (entry.getValue() * entry.getKey() == amount) {

                        resMap.put(entry.getKey(), entry.getValue());
                        denominations.remove(entry.getKey());
                        return resMap;
                    } else if (entry.getValue() * entry.getKey() < amount) {

                        resMap.put(entry.getKey(), entry.getValue());
                        denominations.remove(entry.getKey());
                        amount = amount - entry.getKey() * entry.getValue();
                    } else {
                        int k=amount/entry.getKey();

                        if (k < entry.getValue()) {

                            resMap.put(entry.getKey(), amount / entry.getKey());
                            denominations.replace(entry.getKey(), denominations.get(entry.getKey()) - amount / entry.getKey());
                            amount = amount - entry.getKey() * k;

                        }

                    }
                }
                if (amount>0){throw new NotEnoughMoneyException();}

        } catch (ConcurrentModificationException e) {

        }


        return resMap;
    }
}

//


