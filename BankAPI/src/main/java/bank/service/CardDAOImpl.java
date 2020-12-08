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
import java.util.List;

public class CardDAOImpl extends DataBaseUtil implements CardDAO {
    Connection connection = getConnection();
    private Long number;

    @Override
    public List<Card> getAllByClientID() {
        return null;
    }

    @Override
    public Card getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, card_number, account_id WHERE id =" + String.valueOf(id);
        Card card = new Card();
        try {
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            card.setId(resultSet.getLong("ID"));
            card.setCardNumber(resultSet.getLong("CARD_NUMBER"));
            card.setAccountId(resultSet.getLong("ACCOUNT_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return card;
    }

    @Override
    public Card getByNumber(Long number) throws SQLException {
        this.number = number;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, card_number, account_id WHERE card_number = ?";
        Card card = new Card();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);

            ResultSet resultSet = preparedStatement.executeQuery();

            card.setId(resultSet.getLong("ID"));
            card.setCardNumber(resultSet.getLong("CARD_NUMBER"));
            card.setAccountId(resultSet.getLong("ACCOUNT_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return card;
    }

    @Override
    public void add(Card card) {

    }

    @Override
    public List<Card> getAll() {
        return null;
    }

    @Override
    public Card getEntityById(Long id) {
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
