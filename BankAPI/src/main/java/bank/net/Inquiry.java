package bank.net;

import java.util.LinkedList;

public class Inquiry {
    private String login;
    private String pass;
    private String command;
    private LinkedList<String> arguments;

    public Inquiry(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
}
