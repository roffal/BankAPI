package bank.service;

import bank.bl.DataBaseUtil;
import bank.dao.ClientDAO;
import bank.model.Client;

import java.sql.*;

public class ClientDAOImpl extends DataBaseUtil implements ClientDAO {
    Connection connection;

    public ClientDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Client getById(Long id) throws SQLException {
        //connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT id, name, surname FROM clients WHERE id = " + String.valueOf(id);
        Client client = new Client();
        try {
            //ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet = statement.executeQuery("select id, name, surname from clients where id = 2");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("surname"));
            }

            //preparedStatement.execute();
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        }/* finally {
            //if (preparedStatement != null) {
            //    preparedStatement.close();
            //}
            //if (connection != null){
           //     connection.close();
          //      System.out.println("DB disconnected");
            }
        }*/
        return client;
    }
}
