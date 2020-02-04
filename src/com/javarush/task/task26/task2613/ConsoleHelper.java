package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common");
   private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String string = null;
        try {
            string = bis.readLine();
            if (string.toLowerCase().equals("exit")){
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {

        }
        return string;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String line = "";
        while ((line = readString()).length()!=3){
            writeMessage(res.getString("invalid.data"));

        }

        return line.toUpperCase();
    }

    //ввести два целых положительных числа. Первое число - номинал, второе - количество банкнот.
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] values;
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String temp="";
        while (!(temp=readString()).matches("\\d+\\s\\d+")){
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        values = temp.split(" ");
        

        return values;

    }

    public static Operation askOperation() throws InterruptOperationException{
        Operation operation;
        writeMessage(res.getString("operation"));
        String answer;
        while (true) {
            try {
                answer = readString();
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(answer));
                if (operation!=null){return operation;}

            }
            catch (Exception e){
                writeMessage(res.getString("invalid.data"));
            }
        }

    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }



}
