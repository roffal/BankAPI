package bank.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Command {
    private String command;
    private LinkedList<String> arguments;

    Command(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public LinkedList<String> getArguments() {
        return arguments;
    }

    public void setArguments(LinkedList<String> arguments) {
        this.arguments = arguments;
    }

    /*public String execute(Command command){
        switch (command.command) {
            case ("CARD_ISSUE"):
                issueCard(new BigDecimal(arguments.getFirst()));
                break;
            case ("SHOW_CARDS"):
                showCards();
                break;
            case ("UPDATE_BALANCE"):
                updateBalance(new BigDecimal(arguments.getFirst()), new BigDecimal(arguments.get(1)));
                break;
            case ("CHECK_BALANCE"):
                checkBalance(new BigDecimal(arguments.getFirst()));
                break;

        }
    }*/






}
