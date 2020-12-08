package bank.dao;

import bank.model.Account;

import java.util.List;

public interface AccountDAO extends DAO<Account>{

    public List<Account> getAll();

    public Account getById(Long id);

//not needed in this project for now
    //public void add(Account account);

    //public void delete(Account account);

    public void update(Account account);

}
