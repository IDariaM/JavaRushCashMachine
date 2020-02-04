package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"exit");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equals("y")) ConsoleHelper.writeMessage(res.getString("thank.message"));

           /* String answer=ConsoleHelper.readString();
            if (answer.toLowerCase().equals("y")){
                ConsoleHelper.writeMessage(res.getString("thank.message"));
            }*/




    }
}
