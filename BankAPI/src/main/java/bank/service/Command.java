package bank.service;

import bank.model.Card;
import bank.net.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

public class Command {

    public static Response execute(String command, LinkedList<String> arguments){
        Response response = new Response();
        switch (command) {
            case ("CARD_ISSUE"):
                response = issueCard(new BigDecimal(arguments.getFirst()));
                break;
            case ("SHOW_CARDS"):
                response = showCards();
                break;
            case ("UPDATE_BALANCE"):
                response = updateBalance(new BigDecimal(arguments.getFirst()), new BigDecimal(arguments.get(1)));
                break;
            case ("CHECK_BALANCE"):
                response = checkBalance(new BigDecimal(arguments.getFirst()));
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
            response.setMessage("Card # " + String.valueOf(card.getCardNumber()) + "issued to account # " + accountNumber);
        }
        return response;
    }


    private static Long getUniqueCardNumber(CardDAOImpl cardDAO){
        //Of course I understand that this method is not safe to get a unique card number of 16 digits,
        // but as this project has other main goal, I leave it as it is
        return cardDAO.getLast().getCardNumber() + 1;
    }

    private static Response showCards(){
        Response response = new Response();
        return response;
    }

    private static Response updateBalance(BigDecimal accountNumber, BigDecimal sum){
        Response response = new Response();
        return response;
    }

    private static Response checkBalance(BigDecimal account_number){
        Response response = new Response();
        return response;
    }






}
