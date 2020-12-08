package bank.dao;

import bank.model.Account;
import bank.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO extends DAO<Client> {

    public Client getByAccountID(Long accountId);

    public Client getByLogin(String login);

}
