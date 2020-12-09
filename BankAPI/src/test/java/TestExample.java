import bank.util.DataBaseUtil;
import org.h2.tools.DeleteDbFiles;
import org.h2.tools.Server;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class TestExample {

    @BeforeEach
    private static void renewDB() {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection("test");
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS cards, clients, accounts");
            statement.execute("CREATE TABLE clients(id SERIAL NOT NULL PRIMARY KEY, name TEXT NOT NULL, login TEXT NOT NULL, pass TEXT NOT NULL)");
            statement.execute("CREATE TABLE accounts (id SERIAL NOT NULL PRIMARY KEY, account_number DECIMAL , balance DECIMAL(15,2) NOT NULL DEFAULT 0, client_id INT NOT NULL references clients(id)");
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
    public void test1() throws SQLException, InterruptedException {

    }
}
