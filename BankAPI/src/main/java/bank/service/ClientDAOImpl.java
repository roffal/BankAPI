package bank.service;

import bank.bl.DataBaseUtil;
import bank.dao.ClientDAO;
import bank.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAOImpl extends DataBaseUtil implements ClientDAO {
    Connection connection = getConnection();

    @Override
    public Client getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name, surname, birthday FROM clients WHERE id = ?";
        Client client = new Client();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            client.setId(resultSet.getLong("ID"));
            client.setName(resultSet.getString("NAME"));
            client.setSurname(resultSet.getString("SURNAME"));
            client.setBirthday(resultSet.getDate("BIRTHDAY"));

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return client;
    }
}
