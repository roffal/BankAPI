package bank.dao;

import bank.model.Account;
import bank.model.Card;

import java.sql.SQLException;
import java.util.List;

public interface CardDAO extends DAO<Card> {

    public List<Card> getAllByClientID();

    public Card getById(Long id);

    public Card getByNumber(Long id);

    public void add(Card card);

    public void update(Card card);

    public void delete(Card card);
}
