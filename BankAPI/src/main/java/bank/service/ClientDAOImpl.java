package bank.service;

import bank.bl.DataBaseUtil;
import bank.dao.ClientDAO;
import bank.model.Client;

import java.sql.*;
import java.util.List;

public class ClientDAOImpl extends DataBaseUtil implements ClientDAO {
    Connection connection;

    @Override
    public Client getById(Long id) throws SQLException {
        connection = getConnection();
        String sql = "SELECT id, name, surname, birthday FROM clients WHERE id = " + String.valueOf(id);
        PreparedStatement ps = getPreparedStatement(sql);
        Client client = new Client();
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setBirthday(resultSet.getDate("birthday"));
            }
        } catch (SQLException e){
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return client;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getEntityById(Long id) {
        return null;
    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void create(Client entity) {

    }
}