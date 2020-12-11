package bank.dao;

import bank.model.Client;

public interface ClientDAO extends DAO<Client> {

    Client getByAccountID(Long accountId);

    Client getByLogin(String login);

}
