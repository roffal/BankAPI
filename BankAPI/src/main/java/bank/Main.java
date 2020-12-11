package bank;

import bank.net.CheckBalanceHandler;
import bank.net.IssueCardHandler;
import bank.net.ShowCardsHandler;
import bank.net.UpdateBalanceHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        //DataBaseUtil.renewDB();
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

        server.createContext("/show_cards", new ShowCardsHandler());
        server.createContext("/issue_card", new IssueCardHandler());
        server.createContext("/check_balance", new CheckBalanceHandler());
        server.createContext("/update_balance", new UpdateBalanceHandler());
        server.start();
    }
}
