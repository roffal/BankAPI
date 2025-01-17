package bank.dao;

import bank.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO extends DAO<Account>{

    public Account getByNumber(BigDecimal id);

    List<Account> getAllByClientId(Long id);
}
