package bank.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

/**
 * Kozlina Iya, 2020
 */

public class DataBaseUtil {

    private static final String pathSchema = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/schema.sql";
    private static final String pathData = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/data.sql";
    private Connection connection;

    class DB_Settings{
        public String dbURL;
        public String dbDriver;
        private String username;
        private String password;

        DB_Settings(String setCase){
            switch (setCase){
                case("prod"):
                    dbURL = "jdbc:h2:~/SBER/BankAPI/db/db";
                    dbDriver = "org.h2.Driver";
                    username = "sa";
                    password = "";
                    break;
                case("test"):
                    dbURL = "jdbc:h2:~/test";
                    dbDriver = "org.h2.Driver";
                    username = "sa";
                    password = "";
                    break;
            }

        }

    }
    public Connection getConnection(String setCase) {
        DB_Settings settings = new DB_Settings(setCase);
        if (connection == null) {
            try {
                //load driver, get it registered by DriverManager
                Class.forName(settings.dbDriver);
                //connect to DB
                connection = DriverManager.getConnection(settings.dbURL, settings.username, settings.password);
                //System.out.println("DB connected");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("DB connection ERROR");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
                //System.out.println("DB disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createDB(Statement statement) throws IOException, SQLException{
        String schema = new String(Files.readAllBytes(Paths.get(pathSchema)));
        statement.execute(schema);
        String data = new String(Files.readAllBytes(Paths.get(pathData)));
        statement.execute(data);
    }

    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps){
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}