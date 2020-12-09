package bank.dao;

import bank.util.DataBaseUtil;
import bank.dao.AccountDAO;
import bank.model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends DataBaseUtil implements AccountDAO {
    Connection connection;

    public AccountDAOImpl(String setCase){
        this.connection = getConnection(setCase);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<Account>();
        String sql = "SELECT id, account_number, balance, client_id FROM accounts";
        return getAccounts(accounts, sql);
    }

    @Override
    public List<Account> getAllByClientId(Long id) {
        List<Account> accounts = new ArrayList<Account>();
        String sql = "SELECT id, account_number, balance, client_id" +
                " FROM accounts WHERE client_id =" + String.valueOf(id);
        return getAccounts(accounts, sql);
    }

    private List<Account> getAccounts(List<Account> accounts, String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("ID"));
                account.setAccountNumber(resultSet.getBigDecimal("ACCOUNT_NUMBER"));
                account.setBalance(resultSet.getBigDecimal("BALANCE"));
                account.setClientId(resultSet.getLong("CLIENT_ID"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return accounts;
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
        String sql = "UPDATE accounts SET account_number = ?, balance = ?, " +
                "client_id = ? WHERE ID = " + String.valueOf(account.getId());
        updateDB(account, sql);
    }

    @Override
    public void add(Account account) {
        String sql = "INSERT INTO accounts (account_number, balance, client_id) VALUES(?, ?, ?)";
        updateDB(account, sql);
    }

    private void updateDB(Account account, String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.setBigDecimal(1, account.getAccountNumber());
            ps.setBigDecimal(2, account.getBalance());
            ps.setLong(3, account.getClientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM accounts WHERE id =" + String.valueOf(id);
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }
}
