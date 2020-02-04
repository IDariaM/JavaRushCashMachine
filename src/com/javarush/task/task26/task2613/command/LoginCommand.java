package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        while (true){

            String num=ConsoleHelper.readString();
            String p = ConsoleHelper.readString();
            try {

                if (num.length()!=12||p.length()!=4){
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                }

                if (validCreditCards.containsKey(num)){
                    String tempString = validCreditCards.getString(num);
                    if (tempString.equals(p)){
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),num));
                        break;
                    }

                }
                else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),num));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));


                }



            } catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }

    }
}
// В LoginCommand:
//- со старта идут before и specify.data;
//- если введены не валидные значения для номера карты или пина, то выводим подряд try.again.with.details и try.again.or.exit;
//- если нет такого номера карты или пин не подходик к номеру карты, то выводим подряд not.verified.format и try.again.or.exit;
//- в конце success.format.