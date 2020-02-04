package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.Operation;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private CommandExecutor() throws InterruptOperationException {
    }

    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<Operation, Command>(){{
        put(Operation.LOGIN, new LoginCommand());
        put(Operation.INFO, new InfoCommand());
        put(Operation.DEPOSIT, new DepositCommand());
        put(Operation.WITHDRAW, new WithdrawCommand());
        put(Operation.EXIT, new ExitCommand());

    }};

    public static final void execute (Operation operation) throws InterruptOperationException {
        allKnownCommandsMap.get(operation).execute();
    }
}
