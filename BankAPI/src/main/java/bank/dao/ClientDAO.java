package bank.dao;

import bank.model.Account;
import bank.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO extends DAO<Client> {

    public Client getById(Long id);

//not needed in this project for now
    //public void add(Account account);

    //public void update(Account account);

    //public void delete(Account account);
}
