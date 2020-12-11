import bank.dao.ClientDAOImpl;
import bank.model.Client;
import bank.net.CheckBalanceHandler;
import bank.net.IssueCardHandler;
import bank.net.ShowCardsHandler;
import bank.net.UpdateBalanceHandler;
import bank.service.Inquiry;
import bank.util.DataBaseUtil;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class TestNet {


    @BeforeEach
    public void renewDBandSetConnection() throws IOException {
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

    class JsonInquiry{
        public String login;
        public String pass;
        public String account;
        public String sum;
    }

    @Test
    public void checkHandlerShowCards() throws IOException {
        /*HttpURLConnection connection = (HttpURLConnection) new URL("localhost:8001/show_cards?login=Smith&pass=20001012").openConnection();
        connection.setRequestMethod("GET");
        InputStream is = connection.getInputStream();
        OutputStream os = connection.getOutputStream();
        Gson gson = new Gson();
        JsonInquiry jInq = new JsonInquiry();
        jInq.login = "Smith";
        jInq.pass = "20001012";
        String jsonToStr = gson.toJson(jInq);
        os.write(jsonToStr.toString().getBytes());
        os.flush();
        BufferedReader buff = new BufferedReader(new InputStreamReader(is));
        StringBuilder build = new StringBuilder();
        String line = "";
        while ((line = buff.readLine()) != null){
            build.append(line);
        }
        System.out.println(build);*/
    }

    @Test
    public void checkHandlerCheckBalance(){

    }

    @Test
    public void checkHandlerIssueCard(){

    }

    @Test
    public void checkHandletUpdateBalance(){

    }

}