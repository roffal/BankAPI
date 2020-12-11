package bank.dao;

import bank.model.Card;
import bank.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAOImpl extends DataBaseUtil implements CardDAO {
    private Connection connection;

    public CardDAOImpl(String setCase){
        this.connection = getConnection(setCase);
    }

    @Override
    public Card getById(Long id){
        String sql = "SELECT id, card_number, account_id from cards WHERE id =" + id;
        return getCard(sql);
    }

    @Override
    public Card getByNumber(Long number){
        String sql = "SELECT id, card_number, account_id from cards WHERE card_number = " + number;
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
        updateDB(card, sql);
    }

    private void updateDB(Card card, String sql) {
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
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT id, card_number, account_id FROM cards";
        return getCards(cards, sql);
    }

    @Override
    public List<Card> getAllByClientID(Long id) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT cards.id, cards.card_number, cards.account_id FROM cards, accounts WHERE cards.account_id =" +
                " accounts.id and accounts.client_id = " + id;
        return getCards(cards, sql);
    }

    private List<Card> getCards(List<Card> cards, String sql) {
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
        return cards;
    }


    @Override
    public void update(Card card) {
        String sql = "UPDATE cards SET card_number = ?, account_id = ? WHERE ID = " + card.getId();
        updateDB(card, sql);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM cards WHERE id =" + id;
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }

    public Card getLast() {
        String sql = "SELECT id, card_number, account_id from cards WHERE card_number = (SELECT MAX(card_number) FROM cards)";
        return getCard(sql);
    }


}
