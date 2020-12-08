package bank;

import bank.bl.DataBaseUtil;
import bank.bl.DataBaseUtil.*;
import bank.dao.CardDAO;
import bank.model.Card;
import bank.service.AccountDAOImpl;
import bank.service.CardDAOImpl;
import bank.service.ClientDAOImpl;
import org.h2.tools.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection();
        //renew DB
        try {
            Statement statement = connection.createStatement();
            DataBaseUtil.createDB(statement);
        } catch (SQLException | IOException e) {
            // "DriverManager.getConnection" Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }*/
        //DeleteDbFiles.execute("~", "test", true);*/
        ClientDAOImpl clientService = new ClientDAOImpl();
        CardDAOImpl cardService = new CardDAOImpl();
        AccountDAOImpl acService = new AccountDAOImpl();
        System.out.println(clientService.getById(2l).toString());
        System.out.println(cardService.getById(1l).toString());
        System.out.println(cardService.getByNumber(4000001234567899l).toString());
        System.out.println(acService.getById(3l).toString());
        Card card = new Card();
        card.setCardNumber(4000001234568000l);
        card.setAccountId(acService.getById(7l).getId());
        cardService.add(card);
        System.out.println(cardService.getByNumber(4000001234568000l).toString());
        cardService.closeConnection();
        clientService.closeConnection();
        acService.closeConnection();
    }
}
