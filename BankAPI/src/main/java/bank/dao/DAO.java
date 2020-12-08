package bank.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    public List<E> getAll();

    public E getById(Long id);

    public void update(E entity);

    public void delete(Long id);

    public void add(E entity);

}
