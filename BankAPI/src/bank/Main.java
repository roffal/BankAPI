package bank;

import java.sql.*;
import bank.repository.*;

public class Main {

    // DB Address => Protocol : Vendor : DBMS location (IP or ports for network connections)
    public static final String DB_URL = "jdbc:h2:/Users/iyakozlina/SBER/ProjectTest/src/bank.Main.java";
    // DB driver name
    public static final String DB_Driver = "org.h2.Driver";
    //DB tables


    //Initiate


    //Create and connect tables


    //Start
    public static void main(String[] args) {
        try {
            //load driver, get it registered by DriverManager
            Class.forName(DB_Driver);
            //connect to DB
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.println("DB connection: connected");
            //disconnect
            connection.close();
            System.out.println("DB connection: disconnected");
        } catch (ClassNotFoundException e) {
            //Class.forName() Exception handling
            e.printStackTrace();
            System.out.println("JDBC driver for DBMS not found!");
        } catch (SQLException e) {
            // DriverManager.getConnection Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }
    }
}