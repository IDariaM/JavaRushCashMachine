package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cur = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(cur);


        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String temp = ConsoleHelper.readString();
            try {
            int q = Integer.parseInt(temp);
            if (currencyManipulator.isAmountAvailable(q)) {
                Map<Integer, Integer> map = currencyManipulator.withdrawAmount(q);
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),q,cur));
                break;
            }
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));

            }
            catch (NumberFormatException ignored){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));}
        }

    }
}


