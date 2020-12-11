package bank.service;

import bank.dao.AccountDAOImpl;
import bank.dao.ClientDAOImpl;
import bank.model.Account;
import bank.model.Client;

import java.math.BigDecimal;

public class CheckInquiry {
    Client client;
    Inquiry inquiry;
    public boolean isChecked = false;

    public CheckInquiry(Inquiry inquiry, String setCase){
        this.inquiry = inquiry;
        ClientDAOImpl clientDAO = new ClientDAOImpl(setCase);
        this.client = clientDAO.getByLogin(inquiry.getLogin());
        clientDAO.closeConnection();
        if (checkUser(client, inquiry) && checkCommand(client, inquiry))
            this.isChecked = true;
        inquiry.setClientId(client.getId());
    }

    private boolean checkUser(Client client, Inquiry inquiry){
        if (inquiry != null && client.getId() != null && (client.getPass().equals(inquiry.getPass())))
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
        boolean checkResult = false;
        if (inquiry.getArguments() != null && inquiry.getArguments().size() == 1) {
            AccountDAOImpl accountDAO = new AccountDAOImpl("prod");
            Account account = accountDAO.getByNumber(new BigDecimal(inquiry.getArguments().getFirst()));
            if (account.getId() != null && client.getId().equals(account.getClientId()))
                checkResult = true;
            accountDAO.closeConnection();
        }
        return checkResult;
    }

    private boolean checkUpdateBalance(Client client, Inquiry inquiry){
        if (inquiry.getArguments() != null && inquiry.getArguments().size() == 2) {
            BigDecimal ingSum = new BigDecimal(inquiry.getArguments().get(1));
            int checkSum = ingSum.compareTo(BigDecimal.ZERO);
            if (checkSum < 0)
                return false;
            AccountDAOImpl accountDAO = new AccountDAOImpl("prod");
            Account account = accountDAO.getByNumber(new BigDecimal(inquiry.getArguments().getFirst()));
            accountDAO.closeConnection();
            return account.getId() != null && client.getId().equals(account.getClientId());
        }
        return false;
    }
}

