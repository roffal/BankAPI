import bank.dao.AccountDAOImpl;
import bank.dao.CardDAOImpl;
import bank.dao.ClientDAOImpl;
import bank.model.Account;
import bank.model.Card;
import bank.model.Client;
import bank.service.CheckInquiry;
import bank.service.Command;
import bank.service.Inquiry;
import bank.service.Response;
import bank.util.DataBaseUtil;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestService {


    @BeforeEach
    public void renewDB() {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection("test");
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS cards, clients, accounts");
            statement.execute("CREATE TABLE clients(id SERIAL NOT NULL PRIMARY KEY, name TEXT NOT NULL, login TEXT NOT NULL, pass TEXT NOT NULL)");
            statement.execute("CREATE TABLE accounts (id SERIAL NOT NULL PRIMARY KEY, account_number DECIMAL , balance DECIMAL(15,2) NOT NULL DEFAULT 0, client_id INT NOT NULL references clients(id))");
            statement.execute("CREATE TABLE cards (id SERIAL NOT NULL PRIMARY KEY, card_number BIGINT, account_id INT NOT NULL references accounts(id))");
            statement.execute("INSERT INTO clients(name, login, pass) VALUES ('Michael', 'Smith', '20001012'), ('Will', 'Parry', '19901119')");
            statement.execute("INSERT INTO accounts(account_number, client_id) VALUES (40817810099910004312, (SELECT id FROM clients where id = 1)), (40817810099910004313, (SELECT id FROM clients where id = 2))");
            statement.execute("INSERT INTO cards(card_number, account_id) VALUES (4000001234567899, (SELECT id FROM accounts where id = 1)), (4000001234567900, (SELECT id FROM accounts where id = 2))");
        } catch (SQLException e) {
            // "DriverManager.getConnection" Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }
    }

    @Test
    public void checkUser(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();

        boolean test = true;
        boolean check = (inq != null && client.getId() != null && (client.getPass().equals(inq.getPass())));
        Assert.assertEquals(test, check);
    }

    @Test
    public void checkUserNull(){
        Inquiry inq = new Inquiry();
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);

        boolean test = false;
        boolean check = (inq != null && client.getId() != null && (client.getPass().equals(inq.getPass())));
        Assert.assertEquals(test, check);
    }

    @Test
    public void checkCheckAccountAccess(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = true;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckAccountAccessWrongAcNb(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("12345678923456789023456789345678934567893456789345678");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckUserWrongPass(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001013");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckAccountAccessNoArguments(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001013");
        inq.setCommand("ISSUE_CARD");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkAccountAccessWrongArgNb(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001013");
        inq.setCommand("CHECK_BALANCE");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckUpdateBalance(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        arguments.add("20.15");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");

        boolean test = true;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckUpdateBalanceWrongAcNb(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004313");
        arguments.add("20.15");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckUpdateBalanceNegativeSum(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004313");
        arguments.add("-1.00");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }


    @Test
    public void checkUpdateBalanceNoArguments(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCheckUserWrongArgNb(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client client = clientDAO.getById(1l);
        clientDAO.closeConnection();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        boolean test = false;
        Assert.assertEquals(test, checkInquiry.isChecked);
    }

    @Test
    public void checkCommandIssueCard(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("ISSUE_CARD");
        inq.setClientId(1l);
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        Response test = new Response(200, "Card # 4000001234567901 issued to account # 40817810099910004312");
        Response response = new Response();

        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            response = Command.execute(inq, "test");
        } else {
            response.message = "Error: incorrect data";
        }
        Assert.assertEquals(test, response);
    }

    @Test
    public void checkCommandShowCards(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("ISSUE_CARD");
        inq.setClientId(1l);
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            Command.execute(inq, "test");
        }

        inq.setCommand("SHOW_CARDS");
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        ArrayList<Card> cards = (ArrayList<Card>)cardDAO.getAllByClientID(1l);
        cardDAO.closeConnection();
        Response test = new Response();
        test.status = 200;
        if (cards.size() > 0){
            test.message = cards.toString();
            Gson gson = new Gson();
            test.gson = gson.toJson(cards);
        }
        Response response = new Response();

        checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            response = Command.execute(inq, "test");
        } else {
            response.message = "Error: incorrect data";
        }
        Assert.assertEquals(test, response);
        Assert.assertEquals(test.gson, response.gson);
    }

    @Test
    public void checkCommandShowCardsNoCards(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("SHOW_CARDS");
        inq.setClientId(1l);
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);

        CardDAOImpl cardDAO = new CardDAOImpl("test");
        cardDAO.delete(1l);
        cardDAO.closeConnection();
        Response test = new Response(200, "You have no cards issued yet");

        Response response = new Response();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            response = Command.execute(inq, "test");
        } else {
            response.message = "Error: incorrect data";
        }
        Assert.assertEquals(test, response);
        Assert.assertEquals(test.gson, response.gson);
    }

    @Test
    public void checkCommandCheckBalance(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("CHECK_BALANCE");
        inq.setClientId(1l);
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        inq.setArguments(arguments);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test'");
        Account account = accountDAO.getByNumber(new BigDecimal("40817810099910004312"));
        accountDAO.closeConnection();
        Response test = new Response(200, "Account : 40817810099910004312, Balance : 0.00");
        try {
            Gson gson = new Gson();
            test.gson = gson.toJson(account);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        Response response = new Response();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            response = Command.execute(inq, "test");
        } else {
            response.message = "Error: incorrect data";
        }
        Assert.assertEquals(test, response);
        Assert.assertEquals(test.gson, response.gson);
    }

    @Test
    public void checkCommandUpdateBalance(){
        Inquiry inq = new Inquiry();
        inq.setLogin("Smith");
        inq.setPass("20001012");
        inq.setCommand("UPDATE_BALANCE");
        inq.setClientId(1l);
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004312");
        arguments.add("20.15");
        inq.setArguments(arguments);

        Response test = new Response(200, "Account : 40817810099910004312 updated, Balance : 20.15");

        Response response = new Response();
        CheckInquiry checkInquiry = new CheckInquiry(inq, "test");
        if (checkInquiry.isChecked) {
            response = Command.execute(inq, "test");
        } else {
            response.message = "Error: incorrect data";
        }
        Assert.assertEquals(test, response);

        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account account = accountDAO.getByNumber(new BigDecimal("40817810099910004312"));
        accountDAO.closeConnection();
        Assert.assertEquals(account.getBalance(), new BigDecimal("20.15"));
    }

}
