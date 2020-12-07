package bank.dao;

import bank.model.Account;
import bank.model.Card;

import java.util.List;

public interface CardDAO {

    public List<Card> getAllByClientID();

    public Card getById(Long id);

    public void add(Card card);

    public void update(Card card);

    public void delete(Card card);
}
