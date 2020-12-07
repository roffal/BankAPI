package bank.model;

import java.util.Objects;

public class Card {
    private Long id;
    private Long cardNumber;
    private Account account;
    //private short pin;

    public Card(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /*public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(account, card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, account);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", account=" + account +
                '}';
    }
}
