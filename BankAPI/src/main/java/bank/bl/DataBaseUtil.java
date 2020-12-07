package bank.bl;

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

public class DataBaseUtil {


    public static final String DB_URL = "jdbc:h2:~/SBER/BankAPI/db/db";
    public static final String DB_Driver = "org.h2.Driver";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String pathSchema = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/schema.sql";
    private static final String pathData = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/data.sql";

    public Connection getConnection() {
        Connection connection = null;
        try {
            //load driver, get it registered by DriverManager
            Class.forName(DB_Driver);
            //connect to DB
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("DB connected");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("DB connection ERROR");
            e.printStackTrace();
        }
        return connection;
    }

    public static void createDB(Statement statement) throws IOException, SQLException{
        String schema = new String(Files.readAllBytes(Paths.get(pathSchema)));
        statement.execute(schema);
        String data = new String(Files.readAllBytes(Paths.get(pathData)));
        statement.execute(data);
    }
}