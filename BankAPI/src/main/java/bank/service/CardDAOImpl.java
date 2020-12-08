package bank.service;

import bank.bl.DataBaseUtil;
import bank.dao.CardDAO;
import bank.model.Account;
import bank.model.Card;
import bank.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardDAOImpl extends DataBaseUtil implements CardDAO {
    private Connection connection = getConnection();

    @Override
    public List<Card> getAllByClientID() {
        return null;
    }

    @Override
    public Card getById(Long id){
        String sql = "SELECT id, card_number, account_id from cards WHERE id =" + String.valueOf(id);
        return getCard(sql);
    }

    @Override
    public Card getByNumber(Long number){
        String sql = "SELECT id, card_number, account_id from cards WHERE card_number = " + String.valueOf(number);
        return getCard(sql);
    }

    private Card getCard(String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        Card card = new Card();
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                card.setId(resultSet.getLong("ID"));
                card.setCardNumber(resultSet.getLong("CARD_NUMBER"));
                card.setAccountId(resultSet.getLong("ACCOUNT_ID"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return card;
    }

    @Override
    public void add(Card card) {
        String sql = "INSERT INTO cards (card_number, account_id) VALUES(?, ?)";
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.setLong(1, card.getCardNumber());
            ps.setLong(2, card.getAccountId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<Card>();
        String sql = "SELECT id, card_number, account_id FROM cards";
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getLong("ID"));
                card.setCardNumber(resultSet.getLong("CARD_NUMBER"));
                card.setAccountId(resultSet.getLong("ACCOUNT_ID"));
                cards.add(card);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return null;
    }

    @Override
    public void update(Card card) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void create(Card entity) {

    }

    @Override
    public void delete(Card card) {

    }
}
