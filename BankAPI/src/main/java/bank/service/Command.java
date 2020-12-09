package bank.service;

import bank.dao.AccountDAOImpl;
import bank.dao.CardDAOImpl;
import bank.model.Account;
import bank.model.Card;
import bank.net.Inquiry;
import bank.net.Response;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Command {

    public static Response execute(Inquiry inquiry){
        Response response = new Response();
        switch (inquiry.command) {
            case ("ISSUE_CARD"):
                response = issueCard(new BigDecimal(inquiry.arguments.getFirst()));
                break;
            case ("SHOW_CARDS"):
                response = showCards(inquiry.getLogin(), inquiry.getClientId());
                break;
            case ("UPDATE_BALANCE"):
                response = updateBalance(new BigDecimal(inquiry.arguments.getFirst()), new BigDecimal(inquiry.arguments.get(1)));
                break;
            case ("CHECK_BALANCE"):
                response = checkBalance(new BigDecimal(inquiry.arguments.getFirst()));
                break;

        }
        return response;
    }

    private static Response issueCard(BigDecimal accountNumber){
        Response response = new Response();
        Card card = new Card();
        CardDAOImpl cardDAO = new CardDAOImpl();
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        card.setAccountId(accountDAO.getByNumber(accountNumber).getId());
        card.setCardNumber(getUniqueCardNumber(cardDAO));
        cardDAO.add(card);
        if (cardDAO.getByNumber(card.getCardNumber()).getId() != null){
            response.setStatus(true);
            response.setMessage("Card # " + String.valueOf(card.getCardNumber()) + " issued to account # " + accountNumber);
        }
        cardDAO.closeConnection();
        accountDAO.closeConnection();
        return response;
    }

    private static Long getUniqueCardNumber(CardDAOImpl cardDAO){
        //Of course I understand that this method is not safe to get a unique card number of 16 digits,
        // but as this project has other main goal, I leave it as it is
        return cardDAO.getLast().getCardNumber() + 1;
    }

    private static Response showCards(String login, Long clientId){
        Response response = new Response();
        CardDAOImpl cardDAO = new CardDAOImpl();
        ArrayList<Card> cards = (ArrayList<Card>)cardDAO.getAllByClientID(clientId);
        response.setStatus(true);
        if (cards.get(0).getId() != null){
            StringBuilder build = new StringBuilder();
            for (Card card : cards){
                build.append(card.toString() + ", ");
            }
            response.setMessage(build.toString());
        } else {
            response.setMessage("You have no cards issued yet");
        }
        cardDAO.closeConnection();
        return response;
    }

    private static Response updateBalance(BigDecimal accountNumber, BigDecimal sum){
        Response response;
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        Account defaultAc = accountDAO.getByNumber(accountNumber);
        Account account = accountDAO.getByNumber(accountNumber);
        account.setBalance(account.getBalance().add(sum));
        accountDAO.update(account);
        Account check = accountDAO.getByNumber(accountNumber);
        if (check.getBalance().equals(account.getBalance())){
           response = new Response(true, "Account : " + String.valueOf(accountNumber)
                   + " updated, Balance : " + String.valueOf(account.getBalance()));
        } else {
            accountDAO.update(defaultAc);
            response = new Response(false, "Error: account balance was not updated");
        }
        accountDAO.closeConnection();
        return response;
    }

    private static Response checkBalance(BigDecimal accountNumber){
        Response response;
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        Account account = accountDAO.getByNumber(accountNumber);
        accountDAO.closeConnection();
        return new Response(true, "Account : " + String.valueOf(accountNumber)
                + ", Balance : " + String.valueOf(account.getBalance()));
    }






}
