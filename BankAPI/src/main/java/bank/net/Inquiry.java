package bank.net;

import java.util.LinkedList;
import java.util.Objects;

public class Inquiry {
    private String login;
    private String pass;
    private Long clientId;
    public String command;
    public LinkedList<String> arguments;

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inquiry inquiry = (Inquiry) o;
        return Objects.equals(login, inquiry.login) &&
                Objects.equals(pass, inquiry.pass) &&
                Objects.equals(clientId, inquiry.clientId) &&
                Objects.equals(command, inquiry.command) &&
                Objects.equals(arguments, inquiry.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, pass, clientId, command, arguments);
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", clientId=" + clientId +
                ", command='" + command + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
