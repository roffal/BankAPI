package bank.model;

import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Account {
    private Long id;
    private BigDecimal accountNumber;
    private BigDecimal balance;
    private Long clientId;

    public Account(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(clientId, account.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, clientId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", clientId=" + clientId +
                '}';
    }
}