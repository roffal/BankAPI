package bank;

import bank.net.Inquiry;
import bank.net.Response;
import bank.service.CheckInquiry;
import bank.util.DataBaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws SQLException {
        //renewDB();

        ///TODO : rewrite to HTTP here
        Inquiry inq = new Inquiry();
        inq.setLogin("Shamen");
        inq.setPass("19780713");
        inq.setCommand("ISSUE_CARD");
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("40817810099910004318");
        inq.setArguments(arguments);
        Response response = new Response();
        
        //check Inquiry
        CheckInquiry checkInquiry = new CheckInquiry(inq);
        if (checkInquiry.isChecked = true) {
        } else {
            response.setMessage("Error: incorrect data");
        }

    }

    private static void renewDB() {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection();
        try {
            Statement statement = connection.createStatement();
            DataBaseUtil.createDB(statement);
        } catch (SQLException | IOException e) {
            // "DriverManager.getConnection" Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }
    }
}
