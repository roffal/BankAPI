package bank;

import bank.util.DataBaseUtil;
import bank.model.Card;
import bank.service.AccountDAOImpl;
import bank.service.CardDAOImpl;
import bank.service.ClientDAOImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection();
        //renew DB
        try {
            Statement statement = connection.createStatement();
            DataBaseUtil.createDB(statement);
        } catch (SQLException | IOException e) {
            // "DriverManager.getConnection" Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }
        //DeleteDbFiles.execute("~", "test", true);*/


        ClientDAOImpl clientService = new ClientDAOImpl();
        CardDAOImpl cardService = new CardDAOImpl();
        AccountDAOImpl acService = new AccountDAOImpl();
        System.out.println(clientService.getById(2l).toString());
        System.out.println(cardService.getById(1l).toString());
        System.out.println(cardService.getByNumber(4000001234567899l).toString());
        System.out.println(acService.getById(3l).toString());

        System.out.println("!!! test cardService.getAll");
        List<Card> cards = cardService.getAll();
        for (Card a : cards){
            System.out.println(a.toString());
        }
        System.out.println();
        System.out.println("!!!adding card  to account 7 (will be id = 8)");
        Card card = new Card();
        card.setCardNumber(4000001234568000l);
        card.setAccountId(acService.getById(7l).getId());
        cardService.add(card);
        cards = cardService.getAll();
        for (Card a : cards){
            System.out.println(a.toString());
        }

        System.out.println("!!!delete card with id = 8");
        cardService.delete(8l);
        cards = cardService.getAll();
        for (Card a : cards){
            System.out.println(a.toString());
        }

        System.out.println("!!!update number of card with id = 2");
        card.setCardNumber(4000001234568000l);
        card.setAccountId(2l);
        card.setId(2l);
        cardService.update(card);
        cards = cardService.getAll();
        for (Card a : cards){
            System.out.println(a.toString());
        }

        System.out.println("!!!print cards by client_id 4");
        cards = cardService.getAllByClientID(4l);
        for (Card a : cards){
            System.out.println(a.toString());
        }



        //System.out.println(cardService.getByNumber(4000001234568000l).toString());
        cardService.closeConnection();
        clientService.closeConnection();
        acService.closeConnection();
    }
}
