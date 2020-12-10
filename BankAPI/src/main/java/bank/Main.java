package bank;

import bank.net.CheckBalanceHandler;
import bank.net.IssueCardHandler;
import bank.net.ShowCardsHandler;
import bank.net.UpdateBalanceHandler;
import bank.util.DataBaseUtil;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws IOException {
        //DataBaseUtil.renewDB();
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

        server.createContext("/show_cards", new ShowCardsHandler());
        server.createContext("/issue_card", new IssueCardHandler());
        server.createContext("/check_balance", new CheckBalanceHandler());
        server.createContext("/update_balance", new UpdateBalanceHandler());
        server.start();



        /*Inquiry inq = new Inquiry();
        inq.setLogin("Shamen");
        inq.setPass("19780713");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004318");
        inq.setArguments(arguments);
        Response response = new Response();

        //check Inquiry
        CheckInquiry checkInquiry = new CheckInquiry(inq);
        if (checkInquiry.isChecked) {
            response = Command.execute(inq);
        } else {
            response.setMessage("Error: incorrect data");
        }
        System.out.println(response.toString());

        inq.setLogin("Colter");
        inq.setPass("19880926");
        inq.setCommand("SHOW_CARDS");
        arguments = new LinkedList<>();
        arguments.add("40817810099910004317");
        inq.setArguments(arguments);
        Response response1 = new Response();

        //check Inquiry
        CheckInquiry checkInquiry1 = new CheckInquiry(inq);
        if (checkInquiry1.isChecked) {
            response1 = Command.execute(inq);
        } else {
            response1.setMessage("Error: incorrect data");
        }
        System.out.println(response1.toString());

        inq.setLogin("Shamen");
        inq.setPass("19780713");
        inq.setCommand("CHECK_BALANCE");
        arguments = new LinkedList<>();
        arguments.add("40817810099910004318");
        inq.setArguments(arguments);
        Response response3 = new Response();

        //check Inquiry
        CheckInquiry checkInquiry3 = new CheckInquiry(inq);
        if (checkInquiry3.isChecked) {
            response3 = Command.execute(inq);
        } else {
            response3.setMessage("Error: incorrect data");
        }
        System.out.println(response3.toString());



        inq.setLogin("Shamen");
        inq.setPass("19780713");
        inq.setCommand("UPDATE_BALANCE");
        arguments = new LinkedList<>();
        arguments.add("408178100999100043");
        arguments.add("20.15");
        inq.setArguments(arguments);
        Response response2 = new Response();

        //check Inquiry
        CheckInquiry checkInquiry2 = new CheckInquiry(inq);
        if (checkInquiry2.isChecked) {
            response2 = Command.execute(inq);
        } else {
            response2.setMessage("Error: incorrect data");
        }
        System.out.println(response2.toString());*/


    }
}
