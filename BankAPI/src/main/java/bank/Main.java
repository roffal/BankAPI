package bank;

import bank.bl.DataBaseUtil;
import bank.bl.DataBaseUtil.*;
import bank.service.CardDAOImpl;
import bank.service.ClientDAOImpl;
import org.h2.tools.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseUtil util = new DataBaseUtil();
        Connection connection = util.getConnection();

        try {
            Statement statement = connection.createStatement();
            //DataBaseUtil.createDB(statement);
            ResultSet resultSet = statement.executeQuery("select id, name, surname from clients where id = 2");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("surname"));
            }
            //System.out.println("DB disconnected");
            //connection.close();
        } catch (SQLException e) {
            // DriverManager.getConnection Exception handling
            e.printStackTrace();
            System.out.println("SQL error!");
        } /*catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read file");
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }*/

        //DeleteDbFiles.execute("~", "test", true);
        ClientDAOImpl clientService = new ClientDAOImpl();
        System.out.println(clientService.getById(2l).toString());
    }
}
