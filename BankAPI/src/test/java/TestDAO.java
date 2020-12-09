import bank.dao.AccountDAOImpl;
import bank.dao.CardDAOImpl;
import bank.dao.ClientDAOImpl;
import bank.model.Account;
import bank.model.Card;
import bank.model.Client;
import bank.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {


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

    //AccountDao Tests

    @Test
    public void checkAccountDAOGetById(){
        Account testAccount = new Account();
        testAccount.setId(2l);
        testAccount.setAccountNumber(new BigDecimal("40817810099910004313"));
        testAccount.setBalance(new BigDecimal("0.00"));
        testAccount.setClientId(2l);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account check = accountDAO.getById(2l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

    @Test
    public void checkAccountDAOGetByNumber(){
        Account testAccount = new Account();
        testAccount.setId(2l);
        testAccount.setAccountNumber(new BigDecimal("40817810099910004313"));
        testAccount.setBalance(new BigDecimal("0.00"));
        testAccount.setClientId(2l);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account check = accountDAO.getByNumber(new BigDecimal("40817810099910004313"));
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

    @Test
    public void checkAccountDAOGetByIdNull(){
        Account testAccount = new Account();
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account check = accountDAO.getById(10l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

    @Test
    public void checkAccountDAOGetByNumberNull(){
        Account testAccount = new Account();
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account check = accountDAO.getByNumber(new BigDecimal("408178100999"));
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

    @Test
    public void checkAccountDAOGetAll(){
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        ArrayList<Account> testAccounts = new ArrayList<>();
        Account testAccount1 = accountDAO.getById(1l);
        Account testAccount2 = accountDAO.getById(2l);
        testAccounts.add(testAccount1);
        testAccounts.add(testAccount2);
        ArrayList<Account> check = (ArrayList<Account>)accountDAO.getAll();
        accountDAO.closeConnection();
        Assert.assertEquals(testAccounts, check);
    }

    @Test
    public void checkAccountDAOGetAllByClientId(){
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        ArrayList<Account> testAccounts = new ArrayList<>();
        Account testAccount1 = accountDAO.getById(1l);
        testAccounts.add(testAccount1);
        ArrayList<Account> check = (ArrayList<Account>)accountDAO.getAllByClientId(1l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccounts, check);
    }

    @Test
    public void checkAccountDAOGetAllByClientIdNull(){
        ArrayList<Account> testAccounts = new ArrayList<>();
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        ArrayList<Account> check = (ArrayList<Account>)accountDAO.getAllByClientId(10l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccounts, check);
    }

    @Test
    public void checkAccountDAOUpdate(){
        Account testAccount = new Account();
        testAccount.setAccountNumber(new BigDecimal("40817810099910004314"));
        testAccount.setBalance(new BigDecimal("0.00"));
        testAccount.setClientId(2l);
        testAccount.setId(2l);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        accountDAO.update(testAccount);
        Account check = accountDAO.getById(2l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

    @Test
    public void checkAccountDAOAdd(){
        Account testAccount = new Account();
        testAccount.setAccountNumber(new BigDecimal("40817810099910004315"));
        testAccount.setBalance(new BigDecimal("0.00"));
        testAccount.setClientId(2l);
        testAccount.setId(3l);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        accountDAO.add(testAccount);
        Account check = accountDAO.getByNumber(new BigDecimal("40817810099910004315"));
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }

 /*   @Test
    public void checkAccountDAODelete() {
        Account testAccount = new Account();
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        accountDAO.delete(2l);
        Account check = accountDAO.getById(2l);
        accountDAO.closeConnection();
        Assert.assertEquals(testAccount, check);
    }*/

    //CardDao Tests

    @Test
    public void checkCardDAOGetById(){
        Card testCard = new Card();
        testCard.setId(1l);
        testCard.setCardNumber(4000001234567899l);
        testCard.setAccountId(1l);
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        Card check = cardDAO.getById(1l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAOGetByNumber(){
        Card testCard = new Card();
        testCard.setId(1l);
        testCard.setCardNumber(4000001234567899l);
        testCard.setAccountId(1l);
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        Card check = cardDAO.getByNumber(4000001234567899l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAOGetByIdNull(){
        Card testCard = new Card();
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        Card check = cardDAO.getById(15l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAOGetByNumberNull(){
        Card testCard = new Card();
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        Card check = cardDAO.getByNumber(4000001234567l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAOGetAll(){
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        ArrayList<Card> testCards = new ArrayList<>();
        Card testCard1 = cardDAO.getById(1l);
        Card testCard2 = cardDAO.getById(2l);
        testCards.add(testCard1);
        testCards.add(testCard2);
        ArrayList<Card> check = (ArrayList<Card>)cardDAO.getAll();
        cardDAO.closeConnection();
        Assert.assertEquals(testCards, check);
    }

    @Test
    public void checkCardDAOGetAllByClientId(){
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        ArrayList<Card> testCards = new ArrayList<>();
        Card testCard1 = cardDAO.getById(1l);
        testCards.add(testCard1);
        ArrayList<Card> check = (ArrayList<Card>)cardDAO.getAllByClientID(1l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCards, check);
    }

    @Test
    public void checkCardDAOGetAllByClientIdNull(){
        ArrayList<Card> testCards = new ArrayList<>();
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        ArrayList<Card> check = (ArrayList<Card>)cardDAO.getAllByClientID(0l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCards, check);
    }

    @Test
    public void checkCardDAOUpdate(){
        Card testCard = new Card();
        testCard.setCardNumber(4000001234568000l);
        testCard.setAccountId(2l);
        testCard.setId(2l);
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        cardDAO.update(testCard);
        Card check = cardDAO.getById(2l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAOAdd(){
        Card testCard = new Card();
        testCard.setCardNumber(4000001234568000l);
        testCard.setAccountId(2l);
        testCard.setId(3l);
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        cardDAO.add(testCard);
        Card check = cardDAO.getByNumber(4000001234568000l);

        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    @Test
    public void checkCardDAODelete(){
        Card testCard = new Card();
        CardDAOImpl cardDAO = new CardDAOImpl("test");
        cardDAO.delete(2l);
        Card check = cardDAO.getById(2l);
        cardDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }

    //ClientDao Tests

    @Test
    public void checkClientDAOGetById(){
        Client testClient = new Client();
        testClient.setId(1l);
        testClient.setName("Michael");
        testClient.setLogin("Smith");
        testClient.setPass("20001012");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client check = clientDAO.getById(1l);
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

    @Test
    public void checkClientDAOGetByLogin(){
        Client testClient = new Client();
        testClient.setId(1l);
        testClient.setName("Michael");
        testClient.setLogin("Smith");
        testClient.setPass("20001012");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client check = clientDAO.getByLogin("Smith");
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

    @Test
    public void checkClientDAOGetByIdNull(){
        Client testClient = new Client();
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client check = clientDAO.getById(15l);
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

    @Test
    public void checkClientDAOGetByLoginNull(){
        Client testClient = new Client();
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        Client check = clientDAO.getByLogin("Ciumak");
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

    @Test
    public void checkClientDAOGetAll(){
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        ArrayList<Client> testClients = new ArrayList<>();
        Client testClient1 = clientDAO.getById(1l);
        Client testClient2 = clientDAO.getById(2l);
        testClients.add(testClient1);
        testClients.add(testClient2);
        ArrayList<Client> check = (ArrayList<Client>)clientDAO.getAll();
        clientDAO.closeConnection();
        Assert.assertEquals(testClients, check);
    }

    @Test
    public void checkClientDAOUpdate(){
        Client testClient = new Client();
        testClient.setId(1l);
        testClient.setName("Michaela");
        testClient.setLogin("Smith");
        testClient.setPass("20001012");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        clientDAO.update(testClient);
        Client check = clientDAO.getById(1l);
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

    @Test
    public void checkClientDAOAdd(){
        Client testClient = new Client();
        testClient.setId(3l);
        testClient.setName("Michaela");
        testClient.setLogin("Smith");
        testClient.setPass("20001012");
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        clientDAO.add(testClient);
        Client check = clientDAO.getById(3l);
        clientDAO.closeConnection();
        Assert.assertEquals(testClient, check);
    }

 /*   @Test
    public void checkClientDAODelete() {
        Client testClient = new Client();
        ClientDAOImpl clientDAO = new ClientDAOImpl("test");
        clientDAO.delete(2l);
        Client check = clientDAO.getById(2l);
        clientDAO.closeConnection();
        Assert.assertEquals(testCard, check);
    }*/

}
