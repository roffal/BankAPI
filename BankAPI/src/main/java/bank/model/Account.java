package bank.model;

import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


public class Account {
    private Long id;
    private BigDecimal accountNumber;
    private BigDecimal balance;
    private Long clientId;
    private Set<Card> cards;

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

    public Long getClient() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(clientId, account.clientId) &&
                Objects.equals(cards, account.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, clientId, cards);
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