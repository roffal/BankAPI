package bank.service;

import bank.dao.AccountDAOImpl;
import bank.dao.CardDAOImpl;
import bank.model.Account;
import bank.model.Card;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Command {

    public static Response execute(Inquiry inquiry, String setCase){
        Response response = new Response();
        switch (inquiry.command) {
            case ("ISSUE_CARD"):
                response = issueCard(new BigDecimal(inquiry.arguments.getFirst()), setCase);
                break;
            case ("SHOW_CARDS"):
                response = showCards(inquiry.getLogin(), inquiry.getClientId(), setCase);
                break;
            case ("UPDATE_BALANCE"):
                response = updateBalance(new BigDecimal(inquiry.arguments.getFirst()),
                        new BigDecimal(inquiry.arguments.get(1)), setCase);
                break;
            case ("CHECK_BALANCE"):
                response = checkBalance(new BigDecimal(inquiry.arguments.getFirst()), setCase);
                break;

        }
        return response;
    }

    private static Response issueCard(BigDecimal accountNumber, String setCase){
        Response response = new Response();
        Card card = new Card();
        CardDAOImpl cardDAO = new CardDAOImpl(setCase);
        AccountDAOImpl accountDAO = new AccountDAOImpl(setCase);
        card.setAccountId(accountDAO.getByNumber(accountNumber).getId());
        card.setCardNumber(getUniqueCardNumber(cardDAO));
        cardDAO.add(card);
        if (cardDAO.getByNumber(card.getCardNumber()).getId() != null){
            response.status = 200;
            response.message = "Card # " + String.valueOf(card.getCardNumber()) + " issued to account # " + accountNumber;
        }
        cardDAO.closeConnection();
        accountDAO.closeConnection();
        return response;
    }

    private static Long getUniqueCardNumber(CardDAOImpl cardDAO){
        //Of course I understand that this method is not safest way to get a unique card number of 16 digits,
        // but as this project has other main goal, I leave it as it is
        return (cardDAO.getLast().getCardNumber() + 1) % 9_999_999_999_999_999l;
    }

    private static Response showCards(String login, Long clientId, String setCase){
        Response response = new Response();
        CardDAOImpl cardDAO = new CardDAOImpl(setCase);
        ArrayList<Card> cards = (ArrayList<Card>)cardDAO.getAllByClientID(clientId);
        response.status = 200;
        if (cards.size() > 0){
            response.message = cards.toString();
            Gson gson = new Gson();
            try {
                response.gson = gson.toJson(cards);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        } else {
            response.message = "You have no cards issued yet";
        }
        cardDAO.closeConnection();
        return response;
    }

    private static Response updateBalance(BigDecimal accountNumber, BigDecimal sum, String setCase){
        Response response;
        AccountDAOImpl accountDAO = new AccountDAOImpl(setCase);
        Account defaultAc = accountDAO.getByNumber(accountNumber);
        Account account = accountDAO.getByNumber(accountNumber);
        account.setBalance(account.getBalance().add(sum));
        accountDAO.update(account);
        Account check = accountDAO.getByNumber(accountNumber);
        if (check.getBalance().equals(account.getBalance())){
           response = new Response(200, "Account : " + String.valueOf(accountNumber)
                   + " updated, Balance : " + String.valueOf(account.getBalance()));
        } else {
            accountDAO.update(defaultAc);
            response = new Response(504, "Error: account balance was not updated");
        }
        accountDAO.closeConnection();
        return response;
    }

    private static Response checkBalance(BigDecimal accountNumber, String setCase){
        AccountDAOImpl accountDAO = new AccountDAOImpl(setCase);
        Account account = accountDAO.getByNumber(accountNumber);
        accountDAO.closeConnection();
        Response response = new Response(200, "Account : " + String.valueOf(accountNumber)
                + ", Balance : " + String.valueOf(account.getBalance()));
        try {
            Gson gson = new Gson();
            response.gson = gson.toJson(account);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return response;
    }

}
