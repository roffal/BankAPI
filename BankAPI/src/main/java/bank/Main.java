package bank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

import bank.repository.*;
import org.h2.tools.DeleteDbFiles;
import org.h2.tools.Server;

/**
 * Kozlina Iya, 2020
 */

public class Main {

    // DB Address => Protocol : Vendor : DBMS location (IP or ports for network connections)
    public static final String DB_URL = "jdbc:h2:~/SBER/BankAPI/db/db";
    // DB driver name
    public static final String DB_Driver = "org.h2.Driver";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    //DB tables


    //Initiate


    //Create and connect tables


    //Start
    public static void main(String[] args) throws SQLException {
        try {
            //load driver, get it registered by DriverManager
            Class.forName(DB_Driver);
            //connect to DB
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("DB connection: connected");
            Statement statement = connection.createStatement();
            String pathSchema = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/schema.sql";
            String schema = new String(Files.readAllBytes(Paths.get(pathSchema)));
            statement.execute(schema);
            String pathData = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/data.sql";
            String data = new String(Files.readAllBytes(Paths.get(pathData)));
            statement.execute(data);
            ResultSet resultSet = statement.executeQuery("select id, name, surname from clients ");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("surname"));
            }
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //DeleteDbFiles.execute("~", "test", true);
    }
}