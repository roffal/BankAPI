package bank.dao;

import bank.model.Account;
import bank.model.Card;

import java.sql.SQLException;
import java.util.List;

public interface CardDAO extends DAO<Card> {

    public List<Card> getAllByClientID(Long id);

    public Card getByNumber(Long id);

}
