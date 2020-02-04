package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection <CurrencyManipulator> collection = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collection.isEmpty()){ConsoleHelper.writeMessage(res.getString("no.money"));}
        for (CurrencyManipulator currencyManipulator:collection){
            if (!currencyManipulator.hasMoney()){
                ConsoleHelper.writeMessage(res.getString("no.money"));
                return;
            }
            ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());

        }



    }
}
