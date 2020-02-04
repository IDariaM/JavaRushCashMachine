package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res= ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

            String currencyCode = ConsoleHelper.askCurrencyCode();
            String[]values = ConsoleHelper.getValidTwoDigits(currencyCode);
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            currencyManipulator.addAmount(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),Integer.parseInt(values[0])*Integer.parseInt(values[1]),currencyCode));



    }
}
