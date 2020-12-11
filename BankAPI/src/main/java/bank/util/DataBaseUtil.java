package bank.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * Kozlina Iya, 2020
 */

public class DataBaseUtil {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private static final String pathSchema = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/schema.sql";
    private static final String pathData = "/Users/iyakozlina/SBER/BankAPI/src/main/resources/data.sql";
    private Connection connection;

    class DB_Settings{
        public String dbURL;
        public String dbDriver;
        private String username;
        private String password;

        DB_Settings(String setCase){
            if ("prod".equals(setCase)) {
                dbURL = "jdbc:h2:~/SBER/BankAPI/db/db";
            } else {
                dbURL = "jdbc:h2:~/test";
            }
            dbDriver = "org.h2.Driver";
            username = "sa";
            password = "";
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

    //methods to renew DB

    public static void createDB(Statement statement) throws IOException, SQLException{
        String schema = new String(Files.readAllBytes(Paths.get(pathSchema)));
        statement.execute(schema);
        String data = new String(Files.readAllBytes(Paths.get(pathData)));
        statement.execute(data);
    }

    public static void renewDB() {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection("prod");
        try {
            Statement statement = connection.createStatement();
            DataBaseUtil.createDB(statement);
        } catch (SQLException | IOException e) {
            // "DriverManager.getConnection" Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        }
        util.closeConnection();
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