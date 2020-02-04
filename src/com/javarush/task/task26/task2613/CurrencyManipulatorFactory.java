package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();
    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (map.containsKey(currencyCode.toUpperCase())){
            return map.get(currencyCode.toUpperCase());
        }
        CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode.toUpperCase());
        map.put(currencyCode.toUpperCase(),currencyManipulator);
        return currencyManipulator;
    }
    public static  Collection <CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}

