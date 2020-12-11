package bank.model;

import java.util.List;
import java.util.Objects;

public class Client {
    private Long id;
    private String name;
    private String login;
    private String pass;
    private List<Account> accounts;
    private List<Card> cards;

    public Client(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(login, client.login) &&
                Objects.equals(pass, client.pass) &&
                Objects.equals(accounts, client.accounts) &&
                Objects.equals(cards, client.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, pass, accounts, cards);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + login + '\'' +
                ", birthday=" + pass +
                '}';
    }
}