package bank.service;

import bank.util.DataBaseUtil;
import bank.dao.AccountDAO;
import bank.model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl extends DataBaseUtil implements AccountDAO {
    Connection connection = getConnection();

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account getById(Long id) {
            String sql = "SELECT id, account_number, balance, client_id FROM accounts WHERE id = " + String.valueOf(id);
        return getAccount(sql);
    }

    @Override
    public Account getByNumber(BigDecimal number) {
        String sql = "SELECT id, account_number, balance, client_id FROM accounts WHERE account_number = " + String.valueOf(number);
        return getAccount(sql);
    }

    private Account getAccount(String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        Account account = new Account();
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                account.setId(resultSet.getLong("id"));
                account.setAccountNumber(resultSet.getBigDecimal("account_number"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setClientId(resultSet.getLong("client_id"));
            }
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return account;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void add(Account entity) {

    }

    @Override
    public List<Account> getAllByClientId(Long id) {
        return null;
    }
}
