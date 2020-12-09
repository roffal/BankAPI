import bank.dao.AccountDAOImpl;
import bank.model.Account;
import bank.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestExample {


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
    public void checkAccountDAOGetById(){
        Account testAccount = new Account();
        testAccount.setId(2l);
        testAccount.setAccountNumber(new BigDecimal("40817810099910004313"));
        testAccount.setBalance(new BigDecimal("0.00"));
        testAccount.setClientId(2l);
        AccountDAOImpl accountDAO = new AccountDAOImpl("test");
        Account check = accountDAO.getById(2l);
        Assert.assertEquals(testAccount, check);
    }
}
