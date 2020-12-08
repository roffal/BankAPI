package bank.service;

import bank.model.Account;
import bank.model.Client;
import bank.net.Inquiry;

import java.math.BigDecimal;

public class CheckInquiry {
    Client client;
    Inquiry inquiry;
    public boolean isChecked = false;

    public CheckInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
        this.client = new ClientDAOImpl().getByLogin(inquiry.getLogin());
        if (checkUser(client, inquiry) && checkCommand(client, inquiry))
            this.isChecked = true;
    }

    private boolean checkUser(Client client, Inquiry inquiry){
        if (client.getId() != null && (client.getPass().equals(inquiry.getPass())))
            return true;
        return false;
    }

    private boolean checkCommand(Client client, Inquiry inquiry){
        boolean checkResult = false;
        switch (inquiry.getCommand()){
            case ("ISSUE_CARD"):
            case ("CHECK_BALANCE"):
                checkResult = checkAccountAccess(client, inquiry);
                break;
            case ("SHOW_CARDS"):
                checkResult = true;
                break;
            case ("UPDATE_BALANCE"):
                checkResult = checkUpdateBalance(client, inquiry);
                break;
        }
        return checkResult;
    }

    private boolean checkAccountAccess(Client client, Inquiry inquiry){
            AccountDAOImpl accountDAO = new AccountDAOImpl();
            Account account = accountDAO.getByNumber(new BigDecimal(inquiry.getArguments().getFirst()));
            if (account.getId() != null && client.getId().equals(account.getClientId()))
                return true;
        return false;
    }

    private boolean checkUpdateBalance(Client client, Inquiry inquiry){
        BigDecimal ingSum = new BigDecimal(inquiry.getArguments().get(1));
        int checkSum = ingSum.compareTo(BigDecimal.ZERO);
        if (checkSum < 0)
            return false;
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        Account account = accountDAO.getByNumber(new BigDecimal(inquiry.getArguments().getFirst()));
        return account.getId() != null && client.getId().equals(account.getClientId());
    }
}
