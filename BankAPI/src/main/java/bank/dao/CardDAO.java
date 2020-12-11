package bank.dao;

import bank.model.Card;

import java.util.List;

public interface CardDAO extends DAO<Card> {

    List<Card> getAllByClientID(Long id);

    Card getByNumber(Long id);

}
