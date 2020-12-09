package bank.dao;

import bank.model.Card;
import bank.util.DataBaseUtil;
import bank.dao.ClientDAO;
import bank.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl extends DataBaseUtil implements ClientDAO {
    private Connection connection = getConnection();

    @Override
    public Client getById(Long id){
        String sql = "SELECT id, name, login, pass FROM clients WHERE id = " + String.valueOf(id);
        return getClient(sql);
    }

    private Client getClient(String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        Client client = new Client();
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setLogin(resultSet.getString("login"));
                client.setPass(resultSet.getString("pass"));
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
        List<Client> clients = new ArrayList<Client>();
        String sql = "SELECT id, name, login, pass FROM clients";
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setLogin(resultSet.getString("login"));
                client.setPass(resultSet.getString("pass"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        closePrepareStatement(ps);
        return clients;
    }

    @Override
    public void update(Client entity) {
        String sql = "UPDATE clients SET name = ?, login = ?, pass = ? WHERE ID = " + String.valueOf(entity.getId());
        updateDB(entity, sql);
    }

    @Override
    public void add(Client entity) {
        String sql = "INSERT INTO clients (name, login, pass) VALUES (?, ?, ?))";
        updateDB(entity, sql);
    }

    private void updateDB(Client entity, String sql) {
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLogin());
            ps.setString(3, entity.getPass());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM clients WHERE id =" + String.valueOf(id);
        PreparedStatement ps = getPreparedStatement(sql);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closePrepareStatement(ps);
    }

    @Override
    public Client getByAccountID(Long accountId) {
        String sql = "SELECT id, name, login, pass FROM clients " +
                "WHERE id = (SELECT client_id FROM accounts WHERE id = " + String.valueOf(accountId) +")";
        return getClient(sql);
    }

    @Override
    public Client getByLogin(String login) {
        String sql = "SELECT id, name, login, pass FROM clients WHERE login = '" + login +"'";
        return getClient(sql);
    }
}
